package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dao.FirestationDAO;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.HomesInfo;
import com.safetynet.safetynetalerts.service.FirestationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
public class FirestationController {

    private static final Logger logger = LoggerFactory.getLogger(FirestationController.class);

    @Autowired
    private FirestationService firestationService;

    @Autowired
    private FirestationDAO firestationDAO;

    @GetMapping("/firestation")
    public Iterable<Firestation> getFirestations() {
        Iterable<Firestation> listFireStations = firestationService.getFirestations();
        logger.info("Récupération de la liste de caserne");
        return listFireStations;
    }

    @PostMapping("/firestation")
    public void saveFirestation(@RequestBody Firestation firestation){
        firestationService.saveFirestation(firestation);
        logger.info("Sauvegarde d'une caserne en base de données");
    }

    @PutMapping("/firestation/{id}")
    public Firestation updateFirestation(@PathVariable("id") final Long id, @RequestBody Firestation firestation){
        logger.info("Mise à jour d'une caserne");
        return firestationService.saveFirestation(firestation);
    }

    @DeleteMapping("/firestation/{id}")
    public void deleteFirestation(@PathVariable("id") final Long id){
        logger.info("Récupération de la caserne à supprimer " + id);
        firestationService.deleteFirestation(id);
    }

    @GetMapping("/firestations")
    public HashMap listPersonsCoveredByFirestation(@RequestParam(value = "stationNumber") String station){
        HashMap listPersonsCoveredByFirestation = firestationDAO.getPersonsCoveredByFirestation(station);
        logger.info("Récupération des personnes couvertes par le numéro de caserne " + station);
        return listPersonsCoveredByFirestation;
    }

    @GetMapping("/phoneAlert")
    public List listPersonsPhoneNumberCoveredByFirestationNumber(@RequestParam(value = "firestation") String station){
        List listPersonsPhoneNumberCoveredByFirestationNumber = firestationDAO.getPersonsPhoneNumberCoveredByFirestationNumber(station);
        logger.info("Récupération des numéros de téléphone des personnes couvertes par la caserne numéro " + station);
        return listPersonsPhoneNumberCoveredByFirestationNumber;
    }

    @GetMapping("/fire")
    public List listPersonsCoveredByFirestationAddress(@RequestParam(value = "address") String address){
        List listPersonsAtThisAddressWithStationNumber = firestationDAO.getPersonsCoveredByFirestationAddress(address);
        logger.info("Récupération des personnes vivants à l'adresse " + address + " ainsi que le numéro de la caserne les desservant");
        return listPersonsAtThisAddressWithStationNumber;
    }

    @GetMapping("/flood/stations")
    public List personsHomesCoveredByFirestationNumber(@RequestParam(value = "station") String station){
        List<HomesInfo> listPersonsHomesCoveredByFirestationNumber = firestationDAO.getPersonsHomesCoveredByFirestationNumber(station);
        logger.info("Récupération des personnes couvertes par le numéro de caserne " + station);
        return listPersonsHomesCoveredByFirestationNumber;
    }
}
