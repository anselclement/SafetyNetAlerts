package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.MedicalrecordRepository;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private static Logger logger = LoggerFactory.getLogger(MedicalrecordRepository.class);

    @Autowired
    private PersonRepository personRepository;

    public Iterable<Person> getPersons() {
        logger.info("Récupération de la liste entière de personnes");
        return personRepository.findAll();
    }

    public Optional<Person> getPerson(final Long id){
        logger.info("Récupération de la personne grâce à son id " + id);
        return personRepository.findById(id);
    }

    public void deleteByLastNameAndFirstName(String lastName, String firstName){
        logger.info("Suppression de la personne à supprimer " + lastName + " " + firstName);
        personRepository.deleteByLastNameAndFirstName(lastName, firstName);
    }

    public Person savePerson(Person person){
        logger.info("Sauvegarde de la personne");
        return  personRepository.save(person);
    }

    public Iterable<Person> save(List<Person> persons){
        logger.info("Sauvegarde de toutes les personnes");
        return personRepository.saveAll(persons);
    }

}
