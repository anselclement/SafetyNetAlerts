package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dao.FirestationDAO;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.FirestationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(FirestationController.class);

    @Autowired
    private FirestationService firestationService;

    @Autowired
    private FirestationDAO firestationDAO;

    @GetMapping("/firestation")
    public Iterable<Firestation> getFirestations(Model model) {
        Iterable<Firestation> listFireStations = firestationService.getFirestations();
        model.addAttribute("firestations",listFireStations);
        logger.info("Récupération de la liste de caserne");
        return listFireStations;
    }

    @GetMapping("/createFirestation")
    public String createFirestation(Model model){
        Firestation firestation = new Firestation();
        model.addAttribute("firestation", firestation);
        logger.info("Récupération du formulaire permettant de créer une caserne");
        return "/form/formNewFirestation";
    }

    @GetMapping("/deleteFirestation/{id}")
    public ModelAndView deleteFirestation(@PathVariable("id") final Long id){
        firestationService.deleteFirestation(id);
        logger.info("Récupération de la caserne à supprimer " + id);
        return new ModelAndView("redirect:/firestation");
    }

    @PostMapping("/saveFirestation")
    public ModelAndView saveFirestation(@ModelAttribute Firestation firestation){
        firestationService.saveFirestation(firestation);
        logger.info("Sauvegarde des changements sur la caserne en base de données");
        return new ModelAndView("redirect:/firestation");
    }

    @GetMapping("/updateFirestation/{id}")
    public String updateFirestation(@PathVariable("id") final Long id, Model model){
        Optional<Firestation> firestation = firestationService.getFirestation(id);
        model.addAttribute("firestation", firestation);
        logger.info("Récupération du formulaire permettant la mise à jour d'une caserne");
        return "/form/formUpdateFirestation";
    }

    @GetMapping("/firestations{stationNumber}")
    public String listPersonsCoveredByFirestation(@RequestParam(value = "stationNumber") String station, Model model){
        HashMap listPersonsCoveredByFirestation = firestationDAO.getListPersonsCoveredByFirestation(station);
        model.addAttribute("listPersonsCoveredByFirestation", listPersonsCoveredByFirestation);
        logger.info("Récupération des personnes couvertes par le numéro de caserne " + station);
        return "/personsCoveredByFirestation";
    }

    @GetMapping("/phoneAlert{firestation}")
    public String listPersonsPhoneNumberCoveredByFirestationAddress(@RequestParam(value = "firestation") String station, Model model){
        List listPersonsPhoneNumberCoveredByFirestationAddress = firestationDAO.getPersonsPhoneNumberCoveredByFirestationAddress(station);
        model.addAttribute("listPersonsPhoneNumberCoveredByFirestationAddress", listPersonsPhoneNumberCoveredByFirestationAddress);
        logger.info("Récupération des numéros de téléphone des personnes couvertes par la caserne numéro " + station);
        return "/personsPhoneCoveredByFirestation";
    }

    @GetMapping("/fire{address}")
    public String listPersonsAtThisAddressWithStationNumber(@RequestParam(value = "address") String address, Model model){
        HashMap listPersonsAtThisAddressWithStationNumber = firestationDAO.getPersonsAtThisAddressWithStationNumber(address);
        model.addAttribute("listPersonsAtThisAddressWithStationNumber", listPersonsAtThisAddressWithStationNumber);
        logger.info("Récupération des personnes vivants à l'adresse " + address + " ainsi que le numéro de la caserne les desservant");
        return "/personsAddressWithStationNumber";
    }
}
