package com.safetynet.safetynetalerts.service;


import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.repository.MedicalrecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalrecordService {

    private static Logger logger = LoggerFactory.getLogger(MedicalrecordRepository.class);

    @Autowired
    private MedicalrecordRepository medicalrecordRepository;

    public Optional<Medicalrecord> getMedicalrecord(final Long id){
        return medicalrecordRepository.findById(id);
    }

    public Iterable<Medicalrecord> getMedicalrecords() {
        logger.info("Récupération de la liste entière des dossiers médicaux");
        return medicalrecordRepository.findAll();
    }

    public void deleteByLastNameAndFirstName(String lastName, String firstName){
        medicalrecordRepository.deleteByLastNameAndFirstName(lastName, firstName);
    }

    public Medicalrecord saveMedicalrecord(Medicalrecord medicalrecord){
        return  medicalrecordRepository.save(medicalrecord);
    }

    public Iterable<Medicalrecord> save(List<Medicalrecord> medicalrecords){
        return medicalrecordRepository.saveAll(medicalrecords);
    }
}
