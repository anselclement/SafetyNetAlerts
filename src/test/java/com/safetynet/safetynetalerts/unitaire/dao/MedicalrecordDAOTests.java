package com.safetynet.safetynetalerts.unitaire.dao;

import com.safetynet.safetynetalerts.dao.MedicalrecordDAO;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MedicalrecordDAOTests {

    @InjectMocks
    private MedicalrecordDAO medicalrecordDAO;

    @Test
    public void getMedicalrecordsTest(){
        List<Medicalrecord> expectedListMedicalrecordDAO = medicalrecordDAO.getMedicalrecords();

        assertThat(expectedListMedicalrecordDAO).isNotNull();
    }

    @Test
    public void getPersonsInfoByLastNameAndFirstNameTest(){
        HashMap expectedPersonsInfoByLastNameAndFirstNameTest = medicalrecordDAO.getPersonsInfoByLastNameAndFirstName("Boyd", "John");

        assertThat(expectedPersonsInfoByLastNameAndFirstNameTest).isNotNull();
    }
}
