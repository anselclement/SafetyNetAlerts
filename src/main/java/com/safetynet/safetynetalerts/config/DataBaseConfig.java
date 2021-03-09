package com.safetynet.safetynetalerts.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DataBaseConfig {

    private static final Logger logger = LogManager.getLogger("DataBaseConfig");

    public Connection getConnection() throws ClassNotFoundException, SQLException{
        logger.info("Create DB connection");
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/safetynetalerts","root","rootroot"
        );
    }

    public void closeConnection(Connection con){
        if(con != null){
            try{
                con.close();
                logger.info("Closing DB connection");
            } catch (SQLException e){
                logger.error("Error while closing connection", e);
            }
        }
    }

    public void closePreparedStatement(PreparedStatement ps){
        if(ps != null){
            try{
                ps.close();
                logger.info("Closing prepared Statement");
            } catch (SQLException e){
                logger.error("Error while closing prepared statement", e);
            }
        }
    }
}
