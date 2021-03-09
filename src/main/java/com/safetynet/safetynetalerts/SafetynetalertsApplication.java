package com.safetynet.safetynetalerts;

import com.safetynet.safetynetalerts.config.JSONReader;
import com.safetynet.safetynetalerts.dao.FirestationDAO;
import com.safetynet.safetynetalerts.dao.MedicalrecordDAO;
import com.safetynet.safetynetalerts.dao.PersonDAO;
import com.safetynet.safetynetalerts.model.DataContainer;
import com.safetynet.safetynetalerts.service.FirestationService;
import com.safetynet.safetynetalerts.service.MedicalrecordService;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SafetynetalertsApplication {

	@Autowired
	PersonService personService;

	@Autowired
	FirestationService firestationService;

	@Autowired
	MedicalrecordService medicalrecordService;

	@Bean
	public void loadModel(){
		JSONReader jsonReader = new JSONReader();
		DataContainer dataContainer = jsonReader.readJSON();

		PersonDAO personDAO = new PersonDAO();
		FirestationDAO firestationDAO = new FirestationDAO();
		MedicalrecordDAO medicalrecordDAO = new MedicalrecordDAO();

		if(personDAO.getPersons().isEmpty() && firestationDAO.getFireStations().isEmpty() && medicalrecordDAO.getMedicalrecords().isEmpty()) {
			personService.save(dataContainer.getPersons());
			firestationService.save(dataContainer.getFirestations());
			medicalrecordService.save(dataContainer.getMedicalrecords());
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SafetynetalertsApplication.class, args);
	}

}
