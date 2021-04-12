package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dao.MedicalrecordDAO;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.service.MedicalrecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class MedicalrecordController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private MedicalrecordService medicalrecordService;

    @Autowired
    private MedicalrecordDAO medicalrecordDAO;

    @GetMapping("/medicalrecord")
    public Iterable<Medicalrecord> getMedicalrecords() {
        Iterable<Medicalrecord> ListMedicalrecords = medicalrecordService.getMedicalrecords();
        logger.info("Récupération de la liste des dossiers médicaux");
        return ListMedicalrecords;
    }

    @PostMapping("/medicalrecord")
    public void saveMedicalrecord(@RequestBody Medicalrecord medicalrecord){
        medicalrecordService.saveMedicalrecord(medicalrecord);
        logger.info("Sauvegarde du dossier médical en base de données");
    }

    @PutMapping("/medicalrecord/{id}")
    public Medicalrecord updateMedicalrecord(@PathVariable("id") final Long id, @RequestBody Medicalrecord medicalrecord){
        logger.info("Mise à jour d'un dossier médical");
        return medicalrecordService.saveMedicalrecord(medicalrecord);
    }

    @DeleteMapping("/medicalrecord")
    public void deleteByLastnameAndFirstname(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName){
        medicalrecordService.deleteByLastNameAndFirstName(lastName, firstName);
        logger.info("Récupération du dossier médical de la personne à supprimer " + lastName + " " + firstName);
    }

    @GetMapping("/personInfo")
    public HashMap personInfosByLastNameAndFirstName(@RequestParam(value = "firstName") String lastName, @RequestParam(value = "lastName") String firstName){
        HashMap personInfosByLastNameAndFirstName = medicalrecordDAO.getPersonsInfoByLastNameAndFirstName(firstName, lastName);
        logger.info("Récupération du nom, de l'adresse, du mail, de l'age et des antécédents de la personne grâce à son nom et prénom");
        return personInfosByLastNameAndFirstName;
    }
}
