package edu.kirkwood.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {
    private static Properties properties = new Properties();

    public static Connection getConnection(boolean dataTypeIsMovie) throws SQLException {
        // Moved static into function so ((dataTypeIsMovie)? MovieDAOFactory.class.getClassLoader() : BikeRouteDAOFactory.class.getClassLoader()) works. Reduces file count.
        try(InputStream input = ((dataTypeIsMovie)? MovieDAOFactory.class.getClassLoader() : BikeRouteDAOFactory.class.getClassLoader())
                .getResourceAsStream("application.properties");) {
            if(input == null) {
                throw new RuntimeException("application.properties file not found");
            }
            properties.load(input);
        } catch(IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            throw new RuntimeException("MySQL driver not found");
        }

        String connectionString =  properties.getProperty("mysql.connectionString");
        if(connectionString == null || connectionString.isEmpty()) {
            throw new RuntimeException("MySQL Connection String cannot be found.");
        }

        try {
            Connection connection = DriverManager.getConnection(connectionString);
            if(connection.isValid(2)) {
                return connection;
            } else {
                throw new RuntimeException("Failed to connect to MySQL database");
            }
        } catch(SQLException e) {
            throw new RuntimeException("Please check your connection string for error. " + e.getMessage());
        }
    }
}
