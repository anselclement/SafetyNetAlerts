package com.safetynet.safetynetalerts.unitaire.dao;

import com.safetynet.safetynetalerts.dao.FirestationDAO;
import com.safetynet.safetynetalerts.model.Firestation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class FirestationDAOTests {

    @InjectMocks
    private FirestationDAO firestationDAO;


    @Test
    public void getFirestationsTest(){
        List<Firestation> expectedListFirestationDAO = firestationDAO.getFireStations();

        assertThat(expectedListFirestationDAO).isNotNull();
    }

    @Test
    public void getPersonsCoveredByFirestationTest(){
        HashMap expectedPersonsCoveredByFirestation = firestationDAO.getPersonsCoveredByFirestation("4");

        assertThat(expectedPersonsCoveredByFirestation).isNotNull();
    }

    @Test
    public void getPersonsPhoneNumberCoveredByFirestationNumberTest(){
        List expectedPersonsPhoneNumberCoveredByFirestationNumber = firestationDAO.getPersonsPhoneNumberCoveredByFirestationNumber("4");

        assertThat(expectedPersonsPhoneNumberCoveredByFirestationNumber).isNotNull();
    }

    @Test
    public void getPersonsCoveredByFirestationAddress(){
        List expectedPersonsCoveredByFirestationAddress = firestationDAO.getPersonsCoveredByFirestationAddress("1509 Culver St");

        assertThat(expectedPersonsCoveredByFirestationAddress).isNotNull();
    }

    @Test
    public void getPersonsHomesCoveredByFirestationNumberTest(){
        List expectedPersonsHomesCoveredByFirestationNumber = firestationDAO.getPersonsHomesCoveredByFirestationNumber("4");

        assertThat(expectedPersonsHomesCoveredByFirestationNumber).isNotNull();
    }
}
