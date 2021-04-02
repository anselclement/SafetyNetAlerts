package com.safetynet.safetynetalerts.unitaire.service;

import com.safetynet.safetynetalerts.dao.MedicalrecordDAO;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.MedicalrecordRepository;
import com.safetynet.safetynetalerts.service.MedicalrecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
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
public class MedicalrecordServiceTests {

    @Mock
    private MedicalrecordRepository medicalrecordRepository;

    @InjectMocks
    private MedicalrecordService medicalrecordService;

    @InjectMocks
    private MedicalrecordDAO medicalrecordDAO;

    /*@Test
    public void saveMedicalrecordTest(){
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        final Medicalrecord medicalrecord = new Medicalrecord(1L, "medicalrecordfirstname", "medicalrecordlastname", Date.valueOf("2021-03-26"), 17, medications, allergies);
        medicalrecord.setBirthdate(Date.valueOf("2021-03-26"));
        given(medicalrecordRepository.save(medicalrecord)).willAnswer(invocation -> invocation.getArgument(0));

        Medicalrecord saveMedicalrecord = medicalrecordService.saveMedicalrecord(medicalrecord);

        assertThat(saveMedicalrecord).isNotNull();

        verify(medicalrecordRepository).save(any(Medicalrecord.class));
    }*/

    @Test
    public void getPersonTest(){
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        final Medicalrecord medicalrecord = new Medicalrecord(1L, "medicalrecordfirstname", "medicalrecordlastname", Date.valueOf("2021-03-26"), 17, medications, allergies);

        given(medicalrecordRepository.findById(medicalrecord.getId())).willReturn(Optional.of(medicalrecord));

        final Optional<Medicalrecord> expected = medicalrecordService.getMedicalrecord(medicalrecord.getId());

        assertThat(expected).isNotNull();
    }

    @Test
    public void getPersonsTest(){
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        List<Medicalrecord> listMedicalrecord = new ArrayList<>();
        listMedicalrecord.add(new Medicalrecord(1L, "medicalrecord1firstname", "medicalrecord1lastname", Date.valueOf("2021-03-26"), 17, medications, allergies));
        listMedicalrecord.add(new Medicalrecord(2L, "medicalrecord2firstname", "medicalrecord2lastname", Date.valueOf("2021-03-26"), 17, medications, allergies));
        listMedicalrecord.add(new Medicalrecord(3L, "medicalrecord3firstname", "medicalrecord3lastname", Date.valueOf("2021-03-26"), 17, medications, allergies));


        given(medicalrecordRepository.findAll()).willReturn(listMedicalrecord);

        Iterable<Medicalrecord> expected = medicalrecordService.getMedicalrecords();

        assertEquals(expected, listMedicalrecord);
    }

    /*@Test
    public void savePersonsTest(){
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        List<Medicalrecord> listMedicalrecord = new ArrayList<>();
        listMedicalrecord.add(new Medicalrecord(1L, "medicalrecord1firstname", "medicalrecord1lastname", Date.valueOf("2021-03-26"), 17, medications, allergies));
        listMedicalrecord.add(new Medicalrecord(2L, "medicalrecord2firstname", "medicalrecord2lastname", Date.valueOf("2021-03-26"), 17, medications, allergies));
        listMedicalrecord.add(new Medicalrecord(3L, "medicalrecord3firstname", "medicalrecord3lastname", Date.valueOf("2021-03-26"), 17, medications, allergies));

        given(medicalrecordRepository.saveAll(listMedicalrecord)).willAnswer(invocation -> invocation.getArgument(0));

        Iterable<Medicalrecord> expected = medicalrecordService.save(listMedicalrecord);

        assertThat(expected).isNotNull();

        verify(medicalrecordRepository).saveAll(expected);
    }*/

    @Test
    public void deleteByLastNameAndFirstNameTest(){
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        final Medicalrecord medicalrecord = new Medicalrecord(1L, "medicalrecordfirstname", "medicalrecordlastname", Date.valueOf("2021-03-26"), 17, medications, allergies);

        medicalrecordService.deleteByLastNameAndFirstName(medicalrecord.getLastName(), medicalrecord.getFirstName());
        medicalrecordService.deleteByLastNameAndFirstName(medicalrecord.getLastName(), medicalrecord.getFirstName());

        verify(medicalrecordRepository, times(2)).deleteByLastNameAndFirstName(medicalrecord.getLastName(), medicalrecord.getFirstName());
    }
}
