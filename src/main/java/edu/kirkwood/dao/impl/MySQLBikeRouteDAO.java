package edu.kirkwood.dao.impl;

import edu.kirkwood.dao.BikeRouteDAO;
import edu.kirkwood.model.BikeRoute;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.dao.MySQLConnection.getConnection;

public class MySQLBikeRouteDAO implements BikeRouteDAO {

    @Override
    public List<BikeRoute> search(String state_or_country) {
        // Try-with-resources
        // The object instantiated inside of the parenthesis must be Autocloseable
        try(Connection connection = getConnection(false)) {
            CallableStatement statement = connection.prepareCall("{ CALL sp_SearchBikeRoutesByStateOrCountry(?)}");
            statement.setString(1,  state_or_country);
            ResultSet resultSet = statement.executeQuery();
            List<BikeRoute> bikeRoutes = new ArrayList<>();
            while(resultSet.next()) {
                BikeRoute bikeRoute = new BikeRoute();
                bikeRoute.setRouteNumber(resultSet.getInt("route_number"));
                bikeRoute.setUploader(resultSet.getString("uploader"));
                bikeRoute.setStateOrCountry(resultSet.getString("state_or_country"));
                bikeRoute.setTitle(resultSet.getString("title"));
                bikeRoute.setDateUploaded(resultSet.getDate("date_uploaded"));
                bikeRoute.setMiles(resultSet.getInt("miles"));
                bikeRoute.setElevation(resultSet.getInt("elevation"));
                bikeRoute.setRouteType(resultSet.getString("route_type"));
                bikeRoute.setRoughestTerrainType(resultSet.getString("roughest_terrain_type"));
                bikeRoute.setDescription(resultSet.getString("description"));
                bikeRoutes.add(bikeRoute);
            } 
            return bikeRoutes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}