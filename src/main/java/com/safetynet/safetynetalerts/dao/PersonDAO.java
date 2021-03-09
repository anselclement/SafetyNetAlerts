package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.config.DataBaseConfig;
import com.safetynet.safetynetalerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    private static final Logger logger = LogManager.getLogger("PersonDAO");

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    public List<Person> getPersons(){

        Connection con = null;
        List<Person> persons = new ArrayList<>();
        try{
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("Select * from persons");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Person person = new Person();
                person.setId(rs.getLong("id"));
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setAddress(rs.getString("address"));
                person.setCity(rs.getString("city"));
                person.setZip(rs.getString("zip"));
                person.setEmail(rs.getString("email"));
                person.setPhone(rs.getString("phone"));
                persons.add(person);
            }

        }catch (Exception e){
            logger.info("Error getting all persons");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return persons;
    }
}
