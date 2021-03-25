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

    public List getListPersonsCoveredByFirestation(String stationNumber){

        Connection con = null;
        List<Person> listPersonsCoveredByFirestation = new ArrayList<>();
        try{
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT persons.first_name, persons.last_name, persons.address, persons.phone FROM persons " +
                    "INNER JOIN firestations " +
                    "INNER JOIN medicalrecords ON persons.first_name = medicalrecords.first_name " +
                    "WHERE persons.address = firestations.address AND firestations.station = ?");
            ps.setString(1, stationNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Person person = new Person();
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setAddress(rs.getString("address"));
                person.setPhone(rs.getString("phone"));
                listPersonsCoveredByFirestation.add(person);
            }
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception e){
            logger.info("Error getting all persons covered by firestation");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return listPersonsCoveredByFirestation;
    }
}
