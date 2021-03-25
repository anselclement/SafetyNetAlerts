package com.safetynet.safetynetalerts.unitaire.controller;

import com.safetynet.safetynetalerts.controller.FirestationController;
import com.safetynet.safetynetalerts.dao.FirestationDAO;
import com.safetynet.safetynetalerts.repository.FirestationRepository;
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

@WebMvcTest(controllers = FirestationController.class)
public class FirestationControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private FirestationRepository firestationRepository;

    @MockBean
    private FirestationService firestationService;

    @MockBean
    private PersonService personService;

    @MockBean
    private MedicalrecordService medicalrecordService;

    @MockBean
    private FirestationDAO firestationDAO;

    @Test
    public void getFirestationsTest() throws Exception{
        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk())
                .andExpect(view().name("firestation"));
    }

    @Test
    public void createFirestationTest() throws Exception{
        mockMvc.perform(get("/createFirestation"))
                .andExpect(status().isOk())
                .andExpect(view().name("/form/formNewFirestation"));
    }

    @Test
    public void saveFirestationTest() throws Exception{
        mockMvc.perform(post("/saveFirestation"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/firestation"));
    }

    @Test
    public void updateFirestationTest() throws Exception{
        mockMvc.perform(get("/updateFirestation/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/form/formUpdateFirestation"));
    }

    @Test
    public void deleteFirestationTest() throws Exception{
        mockMvc.perform(get("/deleteFirestation/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/firestation"));
    }

}
