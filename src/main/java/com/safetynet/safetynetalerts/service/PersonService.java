package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.controller.HomeController;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPerson(final Long id){
        return personRepository.findById(id);
    }

    public Iterable<Person> getPersons() {
        logger.info("Récupération de la liste entière de personnes");
        return personRepository.findAll();
    }

    public void deleteByLastNameAndFirstName(String lastName, String firstName){
        logger.info("Récupération de la personne à supprimer " + lastName + " " + firstName);
        personRepository.deleteByLastNameAndFirstName(lastName, firstName);
    }

    public Person savePerson(Person person){
        return  personRepository.save(person);
    }

    public Iterable<Person> save(List<Person> persons){
        return personRepository.saveAll(persons);
    }

}
