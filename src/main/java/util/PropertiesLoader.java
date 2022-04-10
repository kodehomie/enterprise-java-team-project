package util;

import java.io.IOException;
import java.util.Properties;


/**
 * This interface contains a default method that can be used anywhere a Properties
 * object is needed to be loaded.
 * @author Eric Knapp
 *
 */
public interface PropertiesLoader {

    /**
     * This default method will load a properties file into a Properties instance
     * and return it.
     * @param propertiesFilePath a path to a file on the java classpath list
     * @return a populated Properties instance or an empty Properties instance if
     * the file path was not found.
     * @throws IOException properties file cannot be read
     * @throws Exception something went wrong accessing the properties file
     */
    default Properties loadProperties(String propertiesFilePath) throws IOException, Exception{
        Properties properties = new Properties();

        properties.load(this.getClass().getResourceAsStream(propertiesFilePath));

        return properties;
    }
}