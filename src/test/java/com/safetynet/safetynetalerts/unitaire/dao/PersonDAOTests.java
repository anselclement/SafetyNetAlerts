package com.safetynet.safetynetalerts.unitaire.dao;

import com.safetynet.safetynetalerts.dao.PersonDAO;

import com.safetynet.safetynetalerts.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PersonDAOTests {

    @InjectMocks
    private PersonDAO personDAO;

    @Test
    public void getPersonsTest(){
        List<Person> expectedListPersonDAO = personDAO.getPersons();

        assertThat(expectedListPersonDAO).isNotNull();
    }

    @Test
    public void getPersonsEmailByCityTest(){
        List expectedListPersonsEmailByCityTest = personDAO.getPersonsEmailByCity("Culver");

        assertThat(expectedListPersonsEmailByCityTest).isNotNull();
    }

    @Test
    public void getChildListAtGivenAddressWithMembersOfFamilyTest(){
        HashMap expectedChildListAtGivenAddressWithMembersOfFamilyTest = personDAO.getChildAtGivenAddressWithMembersOfFamily("1509 Culver St");

        assertThat(expectedChildListAtGivenAddressWithMembersOfFamilyTest).isNotNull();
    }
}
