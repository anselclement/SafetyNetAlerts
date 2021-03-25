package com.safetynet.safetynetalerts.unitaire.dao;

import com.safetynet.safetynetalerts.dao.FirestationDAO;
import com.safetynet.safetynetalerts.model.Firestation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
