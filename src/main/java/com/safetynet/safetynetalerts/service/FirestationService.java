package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.repository.FirestationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FirestationService {

    private static Logger logger = LoggerFactory.getLogger(FirestationService.class);

    @Autowired
    private FirestationRepository firestationRepository;

    public Optional<Firestation> getFirestation(final Long id){
        logger.info("Récupération de la caserne grâce à son id " + id);
        return firestationRepository.findById(id);
    }

    public Iterable<Firestation> getFirestations() {
        logger.info("Récupération de la liste entière des casernes");
        return firestationRepository.findAll();
    }

    public void deleteFirestation(final Long id){
        logger.info("Suppression de la caserne grâce à son id " + id);
        firestationRepository.deleteById(id);
    }

    public Firestation saveFirestation(Firestation firestation){
        logger.info("Sauvegarde la caserne");
        return  firestationRepository.save(firestation);
    }

    public Iterable<Firestation> save(List<Firestation> firestations){
        logger.info("Sauvegarde de toutes les casernes");
        return firestationRepository.saveAll(firestations);
    }
}
