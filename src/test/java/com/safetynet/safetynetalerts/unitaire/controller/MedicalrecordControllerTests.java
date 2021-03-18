package com.safetynet.safetynetalerts.unitaire.controller;

import com.safetynet.safetynetalerts.controller.MedicalrecordController;
import com.safetynet.safetynetalerts.service.FirestationService;
import com.safetynet.safetynetalerts.service.MedicalrecordService;
import com.safetynet.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = MedicalrecordController.class)
public class MedicalrecordControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MedicalrecordService medicalrecordService;

    @MockBean
    private PersonService personService;

    @MockBean
    private FirestationService firestationService;

    @Test
    public void getMedicalrecordsTest() throws Exception{
        mockMvc.perform(get("/medicalrecord"))
                .andExpect(status().isOk())
                .andExpect(view().name("medicalrecord"));
    }

    @Test
    public void createMedicalrecordTest() throws Exception{
        mockMvc.perform(get("/createMedicalrecord"))
                .andExpect(status().isOk())
                .andExpect(view().name("/form/formNewMedicalRecord"));
    }
    @Test
    public void saveMedicalrecordTest() throws Exception{
        mockMvc.perform(post("/saveMedicalrecord"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/medicalrecord"));
    }

    @Test
    public void updateMedicalrecordTest() throws Exception{
        mockMvc.perform(get("/updateMedicalrecord/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/form/formUpdateMedicalRecord"));
    }

    @Test
    public void deleteMedicalrecordTest() throws Exception{
        mockMvc.perform(get("/deleteMedicalrecord/medicalrecordlastname/medicalrecordfirstname"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/medicalrecord"));
    }

}
