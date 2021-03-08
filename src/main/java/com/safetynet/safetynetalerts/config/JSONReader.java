package com.safetynet.safetynetalerts.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.DataContainer;

import java.io.File;

public class JSONReader {

    public DataContainer readJSON(){

        DataContainer obj = new DataContainer();
        ObjectMapper mapper = new ObjectMapper();

        try{
            obj = mapper.readValue(new File("src/main/resources/data.json"), DataContainer.class);
            return obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
