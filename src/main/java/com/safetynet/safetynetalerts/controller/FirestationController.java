package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dao.FirestationDAO;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Controller
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

    @Autowired
    private FirestationDAO firestationDAO;

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
    public ModelAndView deleteFirestation(@PathVariable("id") final Long id){
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

    @GetMapping("/firestations{stationNumber}")
    public String listPersonsCoveredByFirestation(@RequestParam(value = "stationNumber") String station, Model model){
        HashMap listPersonsCoveredByFirestation = firestationDAO.getListPersonsCoveredByFirestation(station);
        model.addAttribute("listPersonsCoveredByFirestation", listPersonsCoveredByFirestation);
        return "/personsCoveredByFirestation";
    }

    @GetMapping("/phoneAlert{firestation}")
    public String listPersonsPhoneNumberCoveredByFirestationAddress(@RequestParam(value = "firestation") String station, Model model){
        List listPersonsPhoneNumberCoveredByFirestationAddress = firestationDAO.getPersonsPhoneNumberCoveredByFirestationAddress(station);
        model.addAttribute("listPersonsPhoneNumberCoveredByFirestationAddress", listPersonsPhoneNumberCoveredByFirestationAddress);
        return "/personsPhoneCoveredByFirestation";
    }
}
