package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.FirestationService;
import com.safetynet.safetynetalerts.service.MedicalrecordService;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @Autowired
    private PersonService personService;

    @Autowired
    private FirestationService firestationService;

    @Autowired
    private MedicalrecordService medicalrecordService;

    @GetMapping("/")
    public String home(Model model){

        Iterable<Person> listPersons = personService.getPersons();
        Iterable<Firestation> listFireStations = firestationService.getFirestations();
        Iterable<Medicalrecord> listMedicalrecords = medicalrecordService.getMedicalrecords();

        model.addAttribute("persons", listPersons);
        model.addAttribute("firestations", listFireStations);
        model.addAttribute("medicalrecords", listMedicalrecords);

        return "home";
    }
}
