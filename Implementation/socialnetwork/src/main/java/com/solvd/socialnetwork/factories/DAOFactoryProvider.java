package com.solvd.socialnetwork.factories;

import java.io.IOException;
import org.apache.logging.log4j.Logger;
import com.solvd.socialnetwork.utils.Implementation;
import org.apache.logging.log4j.LogManager;

public class DAOFactoryProvider extends Implementation {
    private static final Logger logger = LogManager.getLogger(DAOFactoryProvider.class);
    private static final String PROPERTIES_FILE = "_database.properties";
    private static final String FACTORY_TYPE = loadImplementationType();

    public static IDAOFactory getDAOFactory() {
        if (FACTORY_TYPE == null) {
            throw new IllegalArgumentException("Factory type cannot be null");
        }
        if (FACTORY_TYPE.equalsIgnoreCase("JDBC")) {
            return new JDBCFactory();
        } else if (FACTORY_TYPE.equalsIgnoreCase("MyBatis")) {
            return new MyBatisDAOFactory();
        } else {
            throw new IllegalArgumentException("Factory type is not supported");
        }
    }

    private static String loadImplementationType() {
        String type = null;
        try {
            type = loadImplementation(PROPERTIES_FILE).getProperty("db.implementation");
        } catch (IOException e) {
            logger.error("Error loading implementation type", e);
        }
        return type;
    }

}
