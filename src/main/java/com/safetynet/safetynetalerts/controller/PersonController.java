package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dao.PersonDAO;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void addPerson(@RequestBody Person person){
        personService.savePerson(person);
        logger.info("Sauvegarde d'une nouvelle personne en base de données");
    }

    @PutMapping("/person/{id}")
    public Person updatePerson(@PathVariable("id") Long id, @RequestBody Person person){
        logger.info("Mise à jour d'une personne");
        return personService.savePerson(person);
    }

    @DeleteMapping("/person")
    public void deleteByLastnameAndFirstname(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName){
        personService.deleteByLastNameAndFirstName(lastName, firstName);
        logger.info("Récupération de la personne à supprimer " + lastName + " " + firstName);
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
