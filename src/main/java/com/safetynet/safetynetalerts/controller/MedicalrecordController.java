package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.MedicalrecordService;
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
public class MedicalrecordController {

    @Autowired
    private MedicalrecordService medicalrecordService;

    @GetMapping("/medicalrecord")
    public Iterable<Medicalrecord> getMedicalrecords(Model model) {

        Iterable<Medicalrecord> ListMedicalrecords = medicalrecordService.getMedicalrecords();

        model.addAttribute("medicalrecords",ListMedicalrecords);

        return ListMedicalrecords;
    }

    @GetMapping("/createMedicalrecord")
    public String createMedicalrecord(Model model){
        Medicalrecord medicalrecord = new Medicalrecord();
        model.addAttribute("medicalrecord", medicalrecord);
        return "/form/formNewMedicalRecord";
    }

    @PostMapping("/saveMedicalrecord")
    public ModelAndView saveMedicalrecord(@ModelAttribute Medicalrecord medicalrecord){
        medicalrecordService.saveMedicalrecord(medicalrecord);
        return new ModelAndView("redirect:/medicalrecord");
    }

    @GetMapping("/updateMedicalrecord/{id}")
    public String updateMedicalrecord(@PathVariable("id") final Long id, Model model){
        Optional<Medicalrecord> medicalrecord = medicalrecordService.getMedicalrecord(id);
        model.addAttribute("medicalrecord", medicalrecord);
        return "/form/formUpdateMedicalRecord";
    }

    @GetMapping("/deleteMedicalrecord/{id}")
    public ModelAndView deleteMedicalrecord(@PathVariable("id") final Long id){
        medicalrecordService.deleteMedicalrecord(id);
        return new ModelAndView("redirect:/medicalrecord");
    }
}
