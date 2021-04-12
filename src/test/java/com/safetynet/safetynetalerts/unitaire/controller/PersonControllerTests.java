package com.safetynet.safetynetalerts.unitaire.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.safetynet.safetynetalerts.controller.PersonController;
import com.safetynet.safetynetalerts.dao.PersonDAO;
import com.safetynet.safetynetalerts.service.FirestationService;
import com.safetynet.safetynetalerts.service.MedicalrecordService;
import com.safetynet.safetynetalerts.service.PersonService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private FirestationService firestationService;

    @MockBean
    private MedicalrecordService medicalrecordService;

    @MockBean
    private PersonDAO personDAO;

    @Test
    public void getPersonsTest() throws Exception{
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk());
    }

    @Test
    public void savePersonTest() throws Exception{
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n" +
                        "    \"firstName\": \"Clément\",\n" +
                        "    \"lastName\": \"Ansel\",\n" +
                        "    \"address\": \"1509 Culver St\",\n" +
                        "    \"city\": \"Lille\",\n" +
                        "    \"zip\": \"59000\",\n" +
                        "    \"phone\": \"841-874-6512\",\n" +
                        "    \"email\": \"jaboyd@email.com\"\n" +
                        "  }"))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePersonTest() throws Exception{
        mockMvc.perform(put("/person/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"firstName\": \"Clément\",\n" +
                        "    \"lastName\": \"Ansel\",\n" +
                        "    \"address\": \"1509 Culver St\",\n" +
                        "    \"city\": \"Calais\",\n" +
                        "    \"zip\": \"62100\",\n" +
                        "    \"phone\": \"0603946006\",\n" +
                        "    \"email\": \"jaboyd@email.com\"\n" +
                        "  }"))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePersonTest() throws Exception{
        mockMvc.perform(delete("/person?lastName=ansel&firstName=clément"))
                .andExpect(status().isOk());
    }

    @Test
    public void listPersonsEmailByCityTest() throws Exception{
        mockMvc.perform(get("/communityEmail?city=city"))
                .andExpect(status().isOk());
    }

    @Test
    public void ChildAtGivenAddressWithMembersOfFamilyTest() throws Exception{
        mockMvc.perform(get("/childAlert?address=address"))
                .andExpect(status().isOk());
    }
}
