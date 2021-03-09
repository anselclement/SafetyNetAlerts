package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.config.DataBaseConfig;
import com.safetynet.safetynetalerts.model.Firestation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
                firestation.setAddress(rs.getNString("address"));
                firestation.setStation(rs.getString("station"));
                firestations.add(firestation);
            }

        }catch (Exception e){
            logger.info("Error getting all firestations");
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return firestations;
    }
}
