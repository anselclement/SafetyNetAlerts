package com.safetynet.safetynetalerts.unitaire.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                .andExpect(status().isOk())
                .andExpect(view().name("person"));
    }

    @Test
    public void createPersonTest() throws Exception{
        mockMvc.perform(get("/createPerson"))
                .andExpect(status().isOk())
                .andExpect(view().name("/form/formNewPerson"));
    }

    @Test
    public void savePersonTest() throws Exception{
        mockMvc.perform(post("/savePerson"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/person"));
    }

    @Test
    public void updatePersonTest() throws Exception{
        mockMvc.perform(get("/updatePerson/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/form/formUpdatePerson"));
    }

    @Test
    public void deletePersonTest() throws Exception{
        mockMvc.perform(get("/deletePerson/personlastname/personfirstname"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/person"));
    }

}
