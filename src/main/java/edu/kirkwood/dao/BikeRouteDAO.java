package edu.kirkwood.dao;

import edu.kirkwood.model.BikeRoute;

import java.util.List;

public interface BikeRouteDAO {
    
    // This is an abstract method
    // An abstract method has no implementation (no curly brackets, no access modifer)
    // You only need to define the method's name, inputs, and outputs
    /**
     * Retrieves all bike routes that match a given state or country
     * @param stateOrCountry The state or country of the bike route
     * @return A List of BikeRoute objects that match the state or country
     */
    List<BikeRoute> search(String stateOrCountry);
}
