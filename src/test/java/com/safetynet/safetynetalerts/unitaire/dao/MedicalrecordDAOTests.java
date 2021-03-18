package com.safetynet.safetynetalerts.unitaire.dao;

import com.safetynet.safetynetalerts.config.JSONReader;
import com.safetynet.safetynetalerts.dao.MedicalrecordDAO;
import com.safetynet.safetynetalerts.model.DataContainer;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MedicalrecordDAOTests {

    @InjectMocks
    private MedicalrecordDAO medicalrecordDAO;

    @Test
    public void getMedicalrecordsTest(){

        List<Medicalrecord> expectedListMedicalrecordDAO = medicalrecordDAO.getMedicalrecords();

        assertThat(expectedListMedicalrecordDAO).isNotNull();
    }
}
