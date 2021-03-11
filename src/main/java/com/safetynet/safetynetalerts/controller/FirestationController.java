package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


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

    @GetMapping("/createFirestation")
    public String createFirestation(Model model){
        Firestation firestation = new Firestation();
        model.addAttribute("firestation", firestation);
        return "/form/formNewFirestation";
    }

    @GetMapping("/deleteFirestation/{id}")
    public ModelAndView deletePerson(@PathVariable("id") final Long id){
        firestationService.deleteFirestation(id);
        return new ModelAndView("redirect:/firestation");
    }

    @PostMapping("/saveFirestation")
    public ModelAndView saveFirestation(@ModelAttribute Firestation firestation){
        firestationService.saveFirestation(firestation);
        return new ModelAndView("redirect:/firestation");
    }

    @GetMapping("/updateFirestation/{id}")
    public String updateFirestation(@PathVariable("id") final Long id, Model model){
        Optional<Firestation> firestation = firestationService.getFirestation(id);
        model.addAttribute("firestation", firestation);
        return "/form/formUpdateFirestation";
    }


}
