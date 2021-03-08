package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private static Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPerson(final Long id){
        return personRepository.findById(id);
    }

    public Iterable<Person> getPersons() {
        logger.info("Récupération de la liste entière de personnes");
        return personRepository.findAll();
    }

    public void deletePerson(final Long id){
        personRepository.deleteById(id);
    }

    public Person savePerson(Person person){
        return  personRepository.save(person);
    }

    public Iterable<Person> save(List<Person> persons){
        return personRepository.saveAll(persons);
    }
}
