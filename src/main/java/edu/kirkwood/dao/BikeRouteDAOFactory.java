package edu.kirkwood.dao;

import edu.kirkwood.dao.impl.FileIOBikeRouteDAO;
import edu.kirkwood.dao.impl.MySQLBikeRouteDAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BikeRouteDAOFactory {
    private static Properties properties = new Properties();

    static {
        try(InputStream input = BikeRouteDAOFactory.class.getClassLoader()
                                .getResourceAsStream("application.properties");) {
            if(input == null) {
                throw new RuntimeException("application.properties file not found");
            }
            properties.load(input);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To get the data access object specified in the application.properties file
     * @return a BikeRouteDAO (MySQLBikeRouteDAO, XMLBikeRouteDAO, JsonBikeRouteDAO)
     */
    public static BikeRouteDAO getBikeRouteDAO() {
        String sourceType =  properties.getProperty("datasource.type");
        if(sourceType == null || sourceType.isEmpty()) {
            throw new RuntimeException("Unknown datasource type");
        }
        switch (sourceType.toUpperCase()) {
            case "XML", "JSON" -> {
                String apiURL =  properties.getProperty("xml.apiurl");
                if(apiURL == null || apiURL.isEmpty()) {
                    throw new  RuntimeException("xml.apiurl is empty");
                }
                return new FileIOBikeRouteDAO(
                    "src/main/resources/BikeRoutesJson.json",
                    "src/main/resources/BikeRoutesXML.xml");
            }
            case "MYSQL" -> {
                return new MySQLBikeRouteDAO();
//            case "MONGODB":
//                break;
            }
//            case "MONGODB":
//                break;
        }
        return null;
    }
}
