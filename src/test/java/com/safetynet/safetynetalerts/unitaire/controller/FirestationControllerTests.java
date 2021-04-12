package com.safetynet.safetynetalerts.unitaire.controller;

import com.safetynet.safetynetalerts.controller.FirestationController;
import com.safetynet.safetynetalerts.dao.FirestationDAO;
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


@WebMvcTest(controllers = FirestationController.class)
public class FirestationControllerTests {

    @Autowired
    MockMvc mockMvc;

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
                .andExpect(status().isOk());
    }

    @Test
    public void saveFirestationTest() throws Exception{
        mockMvc.perform(post("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"address\":\"15, rue du code\", \"station\":\"15\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateFirestationTest() throws Exception{
        mockMvc.perform(put("/firestation/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\":\"1\", \"address\":\"15, rue du code\", \"station\":\"20\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteFirestationTest() throws Exception{
        mockMvc.perform(delete("/firestation/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void listPersonsPhoneNumberCoveredByFirestationAddressTest() throws Exception{
        mockMvc.perform(get("/phoneAlert?firestation=address"))
                .andExpect(status().isOk());
    }

    @Test
    public void listPersonsCoveredByFirestationAddressTest() throws Exception{
        mockMvc.perform(get("/fire?address=address"))
                .andExpect(status().isOk());
    }

    @Test
    public  void listPersonsHomesCoveredByFirestationNumberTest() throws Exception{
        mockMvc.perform(get("/flood/stations?station=4"))
                .andExpect(status().isOk());
    }

    @Test
    public void listPersonsCoveredByFirestationTest() throws Exception{
        mockMvc.perform(get("/firestations?stationNumber=4"))
                .andExpect(status().isOk());
    }
}
