package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.service.MedicalrecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class MedicalrecordController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private MedicalrecordService medicalrecordService;

    @GetMapping("/medicalrecord")
    public Iterable<Medicalrecord> getMedicalrecords(Model model) {
        Iterable<Medicalrecord> ListMedicalrecords = medicalrecordService.getMedicalrecords();
        model.addAttribute("medicalrecords",ListMedicalrecords);
        logger.info("Récupération de la liste des dossiers médicaux");
        return ListMedicalrecords;
    }

    @GetMapping("/createMedicalrecord")
    public String createMedicalrecord(Model model){
        Medicalrecord medicalrecord = new Medicalrecord();
        model.addAttribute("medicalrecord", medicalrecord);
        logger.info("Récupération du formulaire permettant de créer un dossier médical");
        return "/form/formNewMedicalRecord";
    }

    @PostMapping("/saveMedicalrecord")
    public ModelAndView saveMedicalrecord(@ModelAttribute Medicalrecord medicalrecord){
        medicalrecordService.saveMedicalrecord(medicalrecord);
        logger.info("Sauvegarde des changements sur le dossier médical en base de données");
        return new ModelAndView("redirect:/medicalrecord");
    }

    @GetMapping("/updateMedicalrecord/{id}")
    public String updateMedicalrecord(@PathVariable("id") final Long id, Model model){
        Optional<Medicalrecord> medicalrecord = medicalrecordService.getMedicalrecord(id);
        model.addAttribute("medicalrecord", medicalrecord);
        logger.info("Récupération du formulaire permettant la mise à jour d'un dossier médical");
        return "/form/formUpdateMedicalRecord";
    }

    @GetMapping("/deleteMedicalrecord/{lastName}/{firstName}")
    public ModelAndView deleteByLastnameAndFirstname(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName){
        medicalrecordService.deleteByLastNameAndFirstName(lastName, firstName);
        logger.info("Récupération du dossier médical de la personne à supprimer" + lastName + " " + firstName);
        return new ModelAndView("redirect:/medicalrecord");
    }
}
