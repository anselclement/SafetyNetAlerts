package com.safetynet.safetynetalerts.unitaire.controller;

import com.safetynet.safetynetalerts.controller.MedicalrecordController;
import com.safetynet.safetynetalerts.dao.MedicalrecordDAO;
import com.safetynet.safetynetalerts.service.FirestationService;
import com.safetynet.safetynetalerts.service.MedicalrecordService;
import com.safetynet.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @MockBean
    private MedicalrecordDAO medicalrecordDAO;

    @Test
    public void getMedicalrecordsTest() throws Exception{
        mockMvc.perform(get("/medicalrecord"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveMedicalrecordTest() throws Exception{
        mockMvc.perform(post("/medicalrecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content("    {\n" +
                        "      \"firstName\": \"Clément\",\n" +
                        "      \"lastName\": \"Ansel\",\n" +
                        "      \"birthdate\": \"08/14/1991\",\n" +
                        "      \"medications\": [\n" +
                        "        \"aznol:350mg\",\n" +
                        "        \"hydrapermazol:100mg\"\n" +
                        "      ],\n" +
                        "      \"allergies\": [\n" +
                        "        \"nillacilan\"\n" +
                        "      ]\n" +
                        "    }"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateMedicalrecordTest() throws Exception{
        mockMvc.perform(put("/medicalrecord/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("    {\n" +
                        "      \"id\":\"1\",\n" +
                        "      \"firstName\": \"Clément\",\n" +
                        "      \"lastName\": \"Ansel\",\n" +
                        "      \"birthdate\": \"08/14/1991\",\n" +
                        "      \"medications\": [\n" +
                        "        \"aznol:350mg\",\n" +
                        "        \"hydrapermazol:100mg\"\n" +
                        "      ],\n" +
                        "      \"allergies\": [\n" +
                        "        \"nillacilan\",\n" +
                        "        \"peanut\"\n" +
                        "      ]\n" +
                        "    }"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMedicalrecordTest() throws Exception{
        mockMvc.perform(delete("/medicalrecord?lastName=ansel&firstName=clément"))
                .andExpect(status().isOk());
    }

    @Test
    public void personInfosByLastNameAndFirstNameTest() throws Exception{
        mockMvc.perform(get("/personInfo?firstName=clément&lastName=ansel"))
                .andExpect(status().isOk());
    }
}
