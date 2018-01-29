package com.vitim.device.support;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class VitimDataProperties {

    private static VitimDataProperties instance = null;
    private Properties properties;


    protected VitimDataProperties(){

        properties = new Properties();
        try {
			properties.load(new FileReader(new File("Vitim.properties")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    public static VitimDataProperties getInstance() {
        if(instance == null) {
            instance = new VitimDataProperties();
        }
        return instance;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }

}