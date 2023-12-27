package com.gildedrose;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static final Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(ClassLoader.getSystemResource("application.properties").openStream());
        } catch (IOException e) {
            throw new RuntimeException("Could not load properties", e);
        }
    }

    public static int getIntProperty(String key, int defaultValue) {
        return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
    }

    public static String getStringProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
