package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Optional<Person> getPerson(final Long id){
        return personRepository.findById(id);
    }

    public Iterable<Person> getPersons() {
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
