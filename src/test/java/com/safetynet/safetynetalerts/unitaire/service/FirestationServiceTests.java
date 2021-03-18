package com.safetynet.safetynetalerts.unitaire.service;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.repository.FirestationRepository;
import com.safetynet.safetynetalerts.service.FirestationService;
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
public class FirestationServiceTests {

    @Mock
    private FirestationRepository firestationRepository;

    @InjectMocks
    private FirestationService firestationService;

    @Test
    public void saveFirestationTest(){
        final Firestation firestation = new Firestation(1L, "firestationaddress", "firestationnumber");

        given(firestationRepository.save(firestation)).willAnswer(invocation -> invocation.getArgument(0));

        Firestation saveFirestation = firestationService.saveFirestation(firestation);

        assertThat(saveFirestation).isNotNull();

        verify(firestationRepository).save(any(Firestation.class));
    }

    @Test
    public void getFirestationTest(){
        final Firestation firestation = new Firestation(1L, "firestationaddress", "firestationnumber");

        given(firestationRepository.findById(firestation.getId())).willReturn(Optional.of(firestation));

        final Optional<Firestation> expected = firestationService.getFirestation(firestation.getId());

        assertThat(expected).isNotNull();
    }

    @Test
    public void getFirestationsTest(){
        List<Firestation> listFirestation = new ArrayList<>();
        listFirestation.add(new Firestation(1L, "firestation1address", "firestation1number"));
        listFirestation.add(new Firestation(2L, "firestation2address", "firestation2number"));
        listFirestation.add(new Firestation(3L, "firestation3address", "firestation3number"));


        given(firestationRepository.findAll()).willReturn(listFirestation);

        Iterable<Firestation> expected = firestationService.getFirestations();

        assertEquals(expected, listFirestation);
    }

    @Test
    public void saveFirestationsTest(){
        List<Firestation> listFirestation = new ArrayList<>();
        listFirestation.add(new Firestation(1L, "firestation1address", "firestation1number"));
        listFirestation.add(new Firestation(2L, "firestation2address", "firestation2number"));
        listFirestation.add(new Firestation(3L, "firestation3address", "firestation3number"));

        given(firestationRepository.saveAll(listFirestation)).willAnswer(invocation -> invocation.getArgument(0));

        Iterable<Firestation> expected = firestationService.save(listFirestation);

        assertThat(expected).isNotNull();

        verify(firestationRepository).saveAll(expected);
    }

    @Test
    public void deleteFirestationTest(){
        final Firestation firestation = new Firestation(1L, "firestationaddress", "firestationnumber");

        firestationService.deleteFirestation(firestation.getId());
        firestationService.deleteFirestation(firestation.getId());

        verify(firestationRepository, times(2)).deleteById(firestation.getId());
    }
}
