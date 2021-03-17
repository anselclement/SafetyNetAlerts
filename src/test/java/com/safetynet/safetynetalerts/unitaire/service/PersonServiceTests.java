package com.safetynet.safetynetalerts.unitaire.service;


import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import com.safetynet.safetynetalerts.service.PersonService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class PersonServiceTests {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;


    @Test
    public void savePersonTest(){
        final Person person = new Person(1L, "personfirstname", "personlastname", "personaddress", "personcity", "personzip", "personphone", "personemail");

        given(personRepository.save(person)).willAnswer(invocation -> invocation.getArgument(0));

        Person savePerson = personService.savePerson(person);

        assertThat(savePerson).isNotNull();

        verify(personRepository).save(any(Person.class));
    }

    @Test
    public void getPersonTest(){
        final Person person = new Person(1L, "personfirstname", "personlastname", "personaddress", "personcity", "personzip", "personphone", "personemail");

        given(personRepository.findById(person.getId())).willReturn(Optional.of(person));

        final Optional<Person> expected = personService.getPerson(person.getId());

        assertThat(expected).isNotNull();
    }

    @Test
    public void getPersonsTest(){
        List<Person> listPerson = new ArrayList<>();
        listPerson.add(new Person(1L, "person1firstname", "person1lastname", "person1address", "person1city", "person1zip", "person1phone", "person1email"));
        listPerson.add(new Person(2L, "person2firstname", "person2lastname", "person2address", "person2city", "person2zip", "person2phone", "person2email"));
        listPerson.add(new Person(3L, "person3firstname", "person3lastname", "person3address", "person3city", "person3zip", "person3phone", "person3email"));

        given(personRepository.findAll()).willReturn(listPerson);

        Iterable<Person> expected = personService.getPersons();

        assertEquals(expected, listPerson);
    }

    @Test
    public void savePersonsTest(){
        List<Person> listPerson = new ArrayList<>();
        listPerson.add(new Person(1L, "person1firstname", "person1lastname", "person1address", "person1city", "person1zip", "person1phone", "person1email"));
        listPerson.add(new Person(2L, "person2firstname", "person2lastname", "person2address", "person2city", "person2zip", "person2phone", "person2email"));
        listPerson.add(new Person(3L, "person3firstname", "person3lastname", "person3address", "person3city", "person3zip", "person3phone", "person3email"));

        given(personRepository.saveAll(listPerson)).willAnswer(invocation -> invocation.getArgument(0));

        Iterable<Person> expected = personService.save(listPerson);

        assertThat(expected).isNotNull();

        verify(personRepository).saveAll(expected);
    }

    @Test
    public void deleteByLastNameAndFirstNameTest(){
        final Person person = new Person(1L, "personfirstname", "personlastname", "personaddress", "personcity", "personzip", "personphone", "personemail");

        personService.deleteByLastNameAndFirstName(person.getLastName(), person.getFirstName());
        personService.deleteByLastNameAndFirstName(person.getLastName(), person.getFirstName());

        verify(personRepository, times(2)).deleteByLastNameAndFirstName(person.getLastName(), person.getFirstName());
    }
}
