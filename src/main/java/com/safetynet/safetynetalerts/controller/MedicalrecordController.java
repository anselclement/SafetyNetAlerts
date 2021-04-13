package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dao.MedicalrecordDAO;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.service.MedicalrecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Void> saveMedicalrecord(@RequestBody Medicalrecord medicalrecord){
        Medicalrecord medicalrecordAdded = medicalrecordService.saveMedicalrecord(medicalrecord);

        if(medicalrecordAdded == null){
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(medicalrecordAdded.getId())
                .toUri();

        logger.info("Sauvegarde du dossier médical en base de données");

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/medicalrecord/{id}")
    public ResponseEntity<Void> updateMedicalrecord(@PathVariable("id") final Long id, @RequestBody Medicalrecord medicalrecord){
        Medicalrecord medicalRecordUpdate = medicalrecordService.saveMedicalrecord(medicalrecord);

        if(medicalRecordUpdate == null){
            return ResponseEntity.noContent().build();
        }

        logger.info("Mise à jour d'un dossier médical");

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/medicalrecord")
    public ResponseEntity<Void> deleteByLastnameAndFirstname(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName){
        try{
            medicalrecordService.deleteByLastNameAndFirstName(lastName, firstName);
            logger.info("Récupération du dossier médical de la personne à supprimer " + lastName + " " + firstName);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            logger.error("Le dossier médical à supprimer n'existe pas");
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/personInfo")
    public HashMap personInfosByLastNameAndFirstName(@RequestParam(value = "firstName") String lastName, @RequestParam(value = "lastName") String firstName){
        HashMap personInfosByLastNameAndFirstName = medicalrecordDAO.getPersonsInfoByLastNameAndFirstName(firstName, lastName);
        logger.info("Récupération du nom, de l'adresse, du mail, de l'age et des antécédents de la personne grâce à son nom et prénom");
        return personInfosByLastNameAndFirstName;
    }
}
