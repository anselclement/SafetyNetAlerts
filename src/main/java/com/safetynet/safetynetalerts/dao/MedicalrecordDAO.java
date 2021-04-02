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
import java.util.HashMap;
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

    public HashMap getPersonsInfoByLastNameAndFirstName(String lastName, String firstName){

        Connection con = null;
        HashMap personsInfoByLastNameAndFirstName = new HashMap();
        String setLastName = null;
        String address = null;
        String eMail = null;
        Integer age = null;
        List<String> listAllergies = new ArrayList<>();
        List<String> listMedications = new ArrayList<>();
        try{
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT DISTINCT m.last_name, p.address, p.email, m.age, ma.allergies, mm.medications" +
                    " FROM persons p, medicalrecords m, medicalrecord_allergies ma, medicalrecord_medications mm" +
                    " WHERE p.last_name = m.last_name" +
                    " AND p.first_name = m.first_name" +
                    " AND m.id = ma.medicalrecord_id" +
                    " AND m.id = mm.medicalrecord_id" +
                    " AND m.last_name = ?" +
                    " AND m.first_name = ?");
            ps.setString(1, lastName);
            ps.setString(2, firstName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                setLastName = rs.getString("last_name");
                address = rs.getString("address");
                eMail = rs.getString("email");
                age  = rs.getInt("age");
                listAllergies.add(rs.getString("allergies"));
                listMedications.add(rs.getString("medications"));
            }
            personsInfoByLastNameAndFirstName.put("last_name", setLastName);
            personsInfoByLastNameAndFirstName.put("address", address);
            personsInfoByLastNameAndFirstName.put("email", eMail);
            personsInfoByLastNameAndFirstName.put("age", age);
            personsInfoByLastNameAndFirstName.put("allergies", listAllergies);
            personsInfoByLastNameAndFirstName.put("medications", listMedications);
        }catch (Exception e){
            logger.error("Error getting persons info by their last name and first name");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return personsInfoByLastNameAndFirstName;
    }
}
