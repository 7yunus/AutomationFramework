package org.example.sedin.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    public static String getPropertiesFileValue(String propertyName) throws IOException {
        String propertyValue;
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop.load(fis);
        propertyValue = prop.getProperty(propertyName);
        return propertyValue;
    }
}
