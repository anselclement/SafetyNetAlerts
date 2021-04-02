package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.config.DataBaseConfig;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.HomesInfo;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PersonInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class FirestationDAO {

    private static final Logger logger = LoggerFactory.getLogger(FirestationDAO.class);

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    public List<Firestation> getFireStations(){

        Connection con = null;
        List<Firestation> firestations = new ArrayList<>();
        try{
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("Select * from firestations");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Firestation firestation = new Firestation();
                firestation.setId(rs.getLong("id"));
                firestation.setAddress(rs.getNString("address"));
                firestation.setStation(rs.getString("station"));
                firestations.add(firestation);
            }
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception e){
            logger.error("Error getting all firestations");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return firestations;
    }

    public HashMap getPersonsCoveredByFirestation(String stationNumber){

        Connection con = null;
        HashMap PersonsCoveredByFirestationAndCount = new HashMap();
        List<Person> listPersonsCoveredByFirestation = new ArrayList<>();
        int countAdult = 0;
        int countChild = 0;
        try{
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT p.first_name, p.last_name, p.address, p.phone, m.age FROM persons p, firestations f, medicalrecords m" +
                    " WHERE p.first_name = m.first_name AND p.address = f.address AND f.station = ?");
            ps.setString(1, stationNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Person person = new Person();
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setAddress(rs.getString("address"));
                person.setPhone(rs.getString("phone"));
                if(rs.getInt("age") > 18){
                    countAdult++;
                }else{
                    countChild++;
                }
                listPersonsCoveredByFirestation.add(person);
                PersonsCoveredByFirestationAndCount.put("person", listPersonsCoveredByFirestation);
            }
            PersonsCoveredByFirestationAndCount.put("countAdult", countAdult);
            PersonsCoveredByFirestationAndCount.put("countChild", countChild);
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception e){
            logger.error("Error getting all persons covered by firestation");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return PersonsCoveredByFirestationAndCount;
    }

    public List getPersonsPhoneNumberCoveredByFirestationNumber(String station){

        Connection con = null;
        List listPersonsPhoneNumberCoveredByFirestation = new ArrayList<>();
        try{
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("Select distinct p.phone from persons p, firestations f where p.address = f.address" +
                    " and f.station = ?");
            ps.setString(1, station);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listPersonsPhoneNumberCoveredByFirestation.add(rs.getString("phone"));
            }
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception e){
            logger.error("Error getting persons phone number covered by firestation number");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return listPersonsPhoneNumberCoveredByFirestation;
    }

    public List getPersonsCoveredByFirestationAddress(String address){

        Connection con = null;
        List<PersonInfo> personsCoveredByFirestationAddress = new ArrayList<>();
        try{
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("Select p.last_name, p.first_name, p.phone, m.age, f.station, ma.allergies, mm.medications from persons p," +
                    " firestations f, medicalrecords m, medicalrecord_allergies ma, medicalrecord_medications mm" +
                    " where p.address = f.address " +
                    "and f.address = ? " +
                    "and p.last_name = m.last_name " +
                    "and p.first_name = m.first_name " +
                    "and m.id = ma.medicalrecord_id " +
                    "and m.id = mm.medicalrecord_id");
            ps.setString(1, address);
            ResultSet rs = ps.executeQuery();
            boolean existPerson;
            while(rs.next()){
                existPerson = false;
                for (int i = 0; i < personsCoveredByFirestationAddress.size(); i++){
                    if (personsCoveredByFirestationAddress.get(i).getLastName().equals(rs.getString("last_name")) && personsCoveredByFirestationAddress.get(i).getFirstName().equals(rs.getString("first_name"))){
                        existPerson = true;
                        String medications = personsCoveredByFirestationAddress.get(i).getMedications();
                        if (!medications.equals(rs.getString("medications"))){
                            medications += ", " + rs.getString("medications");
                            personsCoveredByFirestationAddress.get(i).setMedications(medications);
                        }
                        String allergies = personsCoveredByFirestationAddress.get(i).getAllergies();
                        if (!allergies.equals(rs.getString("allergies"))){
                            allergies += ", " + rs.getString("allergies");
                            personsCoveredByFirestationAddress.get(i).setAllergies(allergies);
                        }
                    }
                }
                if (!existPerson){
                    PersonInfo personInfo = new PersonInfo();
                    personInfo.setLastName(rs.getString("last_name"));
                    personInfo.setFirstName(rs.getString("first_name"));
                    personInfo.setPhone(rs.getString("phone"));
                    personInfo.setAge(rs.getString("age"));
                    personInfo.setStation(rs.getString("station"));
                    personInfo.setMedications(rs.getString("medications"));
                    personInfo.setAllergies(rs.getString("allergies"));
                    personsCoveredByFirestationAddress.add(personInfo);
                }
            }
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception e){
            logger.error("Error getting persons covered by firestation address");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return personsCoveredByFirestationAddress;
    }

    public List getPersonsHomesCoveredByFirestationNumber(String station) {

        Connection con = null;
        List<HomesInfo> PersonsHomesCoveredByFirestationNumber = new ArrayList<>();
        try{
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("Select p.last_name, p.first_name, p.phone, m.age, p.address, f.station, ma.allergies, mm.medications from persons p," +
                    " firestations f, medicalrecords m, medicalrecord_allergies ma, medicalrecord_medications mm" +
                    " where p.address = f.address " +
                    "and f.station = ? " +
                    "and p.last_name = m.last_name " +
                    "and p.first_name = m.first_name " +
                    "and m.id = ma.medicalrecord_id " +
                    "and m.id = mm.medicalrecord_id");
            ps.setString(1, station);
            ResultSet rs = ps.executeQuery();
            boolean existPerson;
            while(rs.next()){
                existPerson = false;
                for (int i = 0; i < PersonsHomesCoveredByFirestationNumber.size(); i++){
                    if (PersonsHomesCoveredByFirestationNumber.get(i).getLastName().equals(rs.getString("last_name")) && PersonsHomesCoveredByFirestationNumber.get(i).getFirstName().equals(rs.getString("first_name"))){
                        existPerson = true;
                        String medications = PersonsHomesCoveredByFirestationNumber.get(i).getMedications();
                        if (!medications.equals(rs.getString("medications"))){
                            medications += ", " + rs.getString("medications");
                            PersonsHomesCoveredByFirestationNumber.get(i).setMedications(medications);
                        }
                        String allergies = PersonsHomesCoveredByFirestationNumber.get(i).getAllergies();
                        if (!allergies.equals(rs.getString("allergies"))){
                            allergies += ", " + rs.getString("allergies");
                            PersonsHomesCoveredByFirestationNumber.get(i).setAllergies(allergies);
                        }
                    }
                }
                if (!existPerson){
                    HomesInfo homesInfo = new HomesInfo();
                    homesInfo.setLastName(rs.getString("last_name"));
                    homesInfo.setFirstName(rs.getString("first_name"));
                    homesInfo.setPhone(rs.getString("phone"));
                    homesInfo.setAge(rs.getString("age"));
                    homesInfo.setAddress(rs.getString("address"));
                    homesInfo.setMedications(rs.getString("medications"));
                    homesInfo.setAllergies(rs.getString("allergies"));
                    PersonsHomesCoveredByFirestationNumber.add(homesInfo);
                }
            }
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception e){
            logger.error("Error getting persons covered by firestation address");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return PersonsHomesCoveredByFirestationNumber;
    }
}
