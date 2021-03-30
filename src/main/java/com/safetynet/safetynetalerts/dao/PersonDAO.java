package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.config.DataBaseConfig;
import com.safetynet.safetynetalerts.model.Medicalrecord;
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
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception e){
            logger.error("Error getting all persons");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return persons;
    }

    public List getPersonsEmailByCity(String city){

        Connection con = null;
        List listEmail = new ArrayList<>();
        try{
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("Select email from persons where city = ?");
            ps.setString(1, city);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                listEmail.add(rs.getString("email"));
            }
            dataBaseConfig.closePreparedStatement(ps);
        } catch (Exception e) {
            logger.info("Error getting email");
        } finally{
            dataBaseConfig.closeConnection(con);
        }
        return listEmail;
    }

    public HashMap getChildListAtGivenAddressWithMembersOfFamily(String address){

        Connection con = null;
        HashMap childListAtGivenAddressWithMembersOfFamily = new HashMap();
        List<Medicalrecord> listChild = new ArrayList<>();
        List othersMembersOfFamily = new ArrayList();
        try{
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("Select p.last_name, p.first_name, m.age from medicalrecords m, persons p where p.last_name = m.last_name" +
                    " and p.first_name = m.first_name and p.address = ?");
            ps.setString(1, address);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if (rs.getInt("age") <= 18){
                    Medicalrecord medicalrecord = new Medicalrecord();
                    medicalrecord.setFirstName(rs.getString("first_name"));
                    medicalrecord.setLastName(rs.getString("last_name"));
                    medicalrecord.setAge(rs.getInt("age"));
                    listChild.add(medicalrecord);
                }else{
                    Medicalrecord medicalrecord = new Medicalrecord();
                    medicalrecord.setFirstName(rs.getString("first_name"));
                    medicalrecord.setLastName(rs.getString("last_name"));
                    medicalrecord.setAge(rs.getInt("age"));
                    othersMembersOfFamily.add(medicalrecord);
                }
                childListAtGivenAddressWithMembersOfFamily.put("listChild", listChild);
                childListAtGivenAddressWithMembersOfFamily.put("othersMembers", othersMembersOfFamily);
            }
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception e){
            logger.error("Error getting child list with their family");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return childListAtGivenAddressWithMembersOfFamily;
    }
}
