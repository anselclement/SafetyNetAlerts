package com.safetynet.safetynetalerts.service;


import com.safetynet.safetynetalerts.dao.MedicalrecordDAO;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.repository.MedicalrecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalrecordService {

    private static Logger logger = LoggerFactory.getLogger(MedicalrecordService.class);

    @Autowired
    private MedicalrecordRepository medicalrecordRepository;

    @Autowired
    private MedicalrecordDAO medicalrecordDAO;

    public Optional<Medicalrecord> getMedicalrecord(final Long id){
        logger.info("Récupération du dossier médical grâce à son id " + id);
        return medicalrecordRepository.findById(id);
    }

    public Iterable<Medicalrecord> getMedicalrecords() {
        logger.info("Récupération de la liste entière des dossiers médicaux");
        return medicalrecordRepository.findAll();
    }

    public void deleteByLastNameAndFirstName(String lastName, String firstName){
        logger.info("Suppression du dossier médical grâce à son nom " + lastName + " et son prénom " + firstName);
        medicalrecordRepository.deleteByLastNameAndFirstName(lastName, firstName);
    }

    public Medicalrecord saveMedicalrecord(Medicalrecord medicalrecord){
        LocalDate currentDate = LocalDate.now();
        medicalrecord.setAge(medicalrecordDAO.calculateAge(medicalrecord.getBirthdate().toLocalDate(), currentDate));
        logger.info("Sauvegarde du dossier médical ainsi que du calcul de l'âge");
        return  medicalrecordRepository.save(medicalrecord);
    }

    public Iterable<Medicalrecord> save(List<Medicalrecord> medicalrecords){
        LocalDate currentDate = LocalDate.now();
        for(int i = 0; i < medicalrecords.size(); i++){
            medicalrecords.get(i).setAge(medicalrecordDAO.calculateAge(medicalrecords.get(i).getBirthdate().toLocalDate(), currentDate));
        }
        logger.info("Sauvegarde de tous les dossiers médicaux ainsi que du calcul de l'âge");
        return medicalrecordRepository.saveAll(medicalrecords);
    }
}
