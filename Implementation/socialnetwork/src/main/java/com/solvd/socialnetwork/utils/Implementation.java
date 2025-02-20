package com.solvd.socialnetwork.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.solvd.socialnetwork.factories.DAOFactoryProvider;

public abstract class Implementation {

    protected static Properties loadImplementation(String propertiesFile) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = DAOFactoryProvider.class.getClassLoader().getResourceAsStream(propertiesFile)) {
            properties.load(input);   
    }
    return properties;
}
}
