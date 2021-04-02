package com.safetynet.safetynetalerts.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.DataContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class JSONReader {

    private static final Logger logger = LoggerFactory.getLogger(JSONReader.class);

    public DataContainer readJSON(){

        DataContainer obj = new DataContainer();
        ObjectMapper mapper = new ObjectMapper();

        try{
            obj = mapper.readValue(new File("src/main/resources/data.json"), DataContainer.class);
            logger.info("mapping du fichier data.json");
            return obj;
        }catch (Exception e){
            logger.error("erreur lors du mapping du fichier data.json");
            e.printStackTrace();
        }
        return obj;
    }
}
