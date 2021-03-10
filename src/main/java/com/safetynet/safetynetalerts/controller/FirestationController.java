package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

    @GetMapping("/firestation")
    public Iterable<Firestation> getFirestations(Model model) {

        Iterable<Firestation> listFireStations = firestationService.getFirestations();

        model.addAttribute("firestations",listFireStations);

        return listFireStations;
    }
}
