package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.config.DataBaseConfig;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicalrecordDAO {

    private static final Logger logger = LogManager.getLogger("MedicalrecordDAO");

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    public List<Medicalrecord> getMedicalrecords(){

        Connection con = null;
        List<Medicalrecord> medicalrecords = new ArrayList<>();
        try{
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("Select * from medicalrecords");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Medicalrecord medicalrecord = new Medicalrecord();
                medicalrecord.setId(rs.getLong("id"));
                medicalrecord.setFirstName(rs.getString("first_name"));
                medicalrecord.setLastName(rs.getString("last_name"));
                medicalrecord.setBirthdate(rs.getString("birthdate"));
                medicalrecords.add(medicalrecord);
            }

        }catch (Exception e){
            logger.info("Error getting all medical records");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return medicalrecords;
    }
}
