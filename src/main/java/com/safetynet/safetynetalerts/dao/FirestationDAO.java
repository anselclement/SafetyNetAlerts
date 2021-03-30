package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.config.DataBaseConfig;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class FirestationDAO {

    private static final Logger logger = LogManager.getLogger("FirestationDAO");

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
            logger.info("Error getting all firestations");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return firestations;
    }

    public HashMap getListPersonsCoveredByFirestation(String stationNumber){

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
            logger.info("Error getting all persons covered by firestation");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return PersonsCoveredByFirestationAndCount;
    }

    public List getPersonsPhoneNumberCoveredByFirestationAddress(String address){

        Connection con = null;
        List listPersonsPhoneNumberCoveredByFirestation = new ArrayList<>();
        try{
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("Select p.phone from persons p, firestations f where p.address = f.address" +
                    " and f.station = ?");
            ps.setString(1, address);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listPersonsPhoneNumberCoveredByFirestation.add(rs.getString("phone"));
            }
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception e){
            logger.info("Error getting persons phone number covered by firestation address");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return listPersonsPhoneNumberCoveredByFirestation;
    }

}
