package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.service.MedicalrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MedicalrecordController {

    @Autowired
    private MedicalrecordService medicalrecordService;

    @GetMapping("/medicalrecord")
    public Iterable<Medicalrecord> getMedicalrecords(Model model) {

        Iterable<Medicalrecord> ListMedicalrecords = medicalrecordService.getMedicalrecords();

        model.addAttribute("medicalrecords",ListMedicalrecords);

        return ListMedicalrecords;
    }
}
