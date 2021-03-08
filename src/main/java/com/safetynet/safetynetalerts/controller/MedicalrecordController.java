package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.service.MedicalrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalrecordController {

    @Autowired
    MedicalrecordService medicalrecordService;

    @GetMapping("/medicalrecords")
    public Iterable<Medicalrecord> getMedicalrecords() {
        return medicalrecordService.getMedicalrecords();
    }
}
