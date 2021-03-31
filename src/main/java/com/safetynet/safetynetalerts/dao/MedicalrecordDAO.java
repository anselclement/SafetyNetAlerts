package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.config.DataBaseConfig;
import com.safetynet.safetynetalerts.model.Medicalrecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalrecordDAO {

    private static final Logger logger = LoggerFactory.getLogger(MedicalrecordDAO.class);

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    private Medicalrecord medicalrecord;

    public int calculateAge(LocalDate birthdate, LocalDate currentDate){
        return Period.between(birthdate, currentDate).getYears();
    }

    public List<Medicalrecord> getMedicalrecords(){

        Connection con = null;
        LocalDate currentDate = LocalDate.now();
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
                medicalrecord.setBirthdate(rs.getDate("birthdate"));
                medicalrecord.setAge(calculateAge(medicalrecord.getBirthdate().toLocalDate(), currentDate));
                medicalrecords.add(medicalrecord);
            }
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception e){
            logger.error("Error getting all medical records");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return medicalrecords;
    }
}
