package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dao.PersonDAO;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;

import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

@RestController
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonDAO personDAO;

    @GetMapping("/person")
    public Iterable<Person> getPersons() {
        Iterable<Person> listPersons = personService.getPersons();
        logger.info("Récupération de la liste de personne");
        return listPersons;
    }

    @PostMapping("/person")
    public ResponseEntity<Void> addPerson(@RequestBody Person person){
        Person personAdded = personService.savePerson(person);

        if(personAdded == null){
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(personAdded.getId())
                .toUri();

        logger.info("Sauvegarde d'une nouvelle personne en base de données");

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable("id") Long id, @RequestBody Person person){
        Person personUpdate = personService.savePerson(person);

        if(personUpdate == null){
            return ResponseEntity.noContent().build();
        }

        logger.info("Mise à jour d'une personne");

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/person")
    public ResponseEntity<Void> deleteByLastnameAndFirstname(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName){
        try{
            personService.deleteByLastNameAndFirstName(lastName, firstName);
            logger.info("Récupération de la personne à supprimer " + lastName + " " + firstName);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            logger.error("La personne à supprimer n'existe pas");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/communityEmail")
    public List listPersonsEmailByCity(@RequestParam(value = "city") String city){
        List listPersonsEmailByCity = personDAO.getPersonsEmailByCity(city);
        logger.info("Récupération de la liste des e-mails de la ville " + city);
        return listPersonsEmailByCity;
    }

    @GetMapping("/childAlert")
    public HashMap ChildAtGivenAddressWithMembersOfFamily(@RequestParam(value = "address") String address){
        HashMap ChildListAtGivenAddressWithMembersOfFamily = personDAO.getChildAtGivenAddressWithMembersOfFamily(address);
        logger.info("Récupération des enfants vivant à l'adresse " + address);
        return ChildListAtGivenAddressWithMembersOfFamily;
    }
}
