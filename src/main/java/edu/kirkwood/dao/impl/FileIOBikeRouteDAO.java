//  ChatGPT (GPT-5.1)
//  12/5/2025
/*  WARNING - File written by ChatGPT
    PROMPT:
    Using these three example files (one mysql, one xml, and one json),
    please create a file called "FileIO" that manages output for the xml and json files
    (input will come later and mysql is already done).
    <Omitted Code Block 1>
    <Omitted Code Block 2>
    <Omitted Code Block 3>
    The mysql file is used as an example. The XML and Json data handling should work similarly to the mysql example
    (although the mysql is in an exterior database, the XML and Json files that are given are in the project).
    This whole app runs on java, maven, and a bunch of dependencies. Thank you!
*/
package edu.kirkwood.dao.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import edu.kirkwood.dao.BikeRouteDAO;
import edu.kirkwood.model.BikeRoute;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads BikeRoute data from JSON or XML files in the project using Jackson.
 * Designed to mimic the structure of the MySQLBikeRouteDAO.
 */
public class FileIOBikeRouteDAO implements BikeRouteDAO {

    private final File jsonFile;
    private final File xmlFile;

    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final XmlMapper xmlMapper = new XmlMapper();

    public FileIOBikeRouteDAO(String jsonPath, String xmlPath) {
        this.jsonFile = new File(jsonPath);
        this.xmlFile = new File(xmlPath);
    }

    @Override
    public List<BikeRoute> search(String state_or_country) {

        try {
            // Try JSON first if it exists
            if (jsonFile.exists()) {
                return searchJson(state_or_country);
            }

            // Otherwise try XML
            if (xmlFile.exists()) {
                return searchXml(state_or_country);
            }

            throw new RuntimeException("Neither JSON nor XML file exists.");

        } catch (Exception e) {
            throw new RuntimeException("Error reading BikeRoute data: " + e.getMessage(), e);
        }
    }

    // ------------------------------
    // JSON READING
    // ------------------------------

    private List<BikeRoute> searchJson(String state_or_country) throws IOException {
        BikeRouteJsonWrapper wrapper = jsonMapper.readValue(jsonFile, BikeRouteJsonWrapper.class);

        List<BikeRoute> results = new ArrayList<>();

        for (BikeRoute route : wrapper.getBikeRoutes()) {
            if (route.getStateOrCountry().equalsIgnoreCase(state_or_country)) {
                results.add(route);
            }
        }
        return results;
    }

    // ------------------------------
    // XML READING
    // ------------------------------

    private List<BikeRoute> searchXml(String state_or_country) throws IOException {
        BikeRouteXmlWrapper wrapper = xmlMapper.readValue(xmlFile, BikeRouteXmlWrapper.class);

        List<BikeRoute> results = new ArrayList<>();

        for (BikeRoute route : wrapper.getBikeRoutes().getBikeRoutes()) {
            if (route.getStateOrCountry().equalsIgnoreCase(state_or_country)) {
                results.add(route);
            }
        }
        return results;
    }

    // --------------------------------------------------
    // WRAPPER CLASSES FOR JSON/XML STRUCTURE
    // --------------------------------------------------

    /** Matches the structure of the provided JSON file */
    public static class BikeRouteJsonWrapper {

        @JsonProperty("numberOfRoutes")
        private int numberOfRoutes;

        @JsonProperty("BikeRoutes") // FIX: match JSON key exactly
        private List<BikeRoute> bikeRoutes;

        public int getNumberOfRoutes() {
            return numberOfRoutes;
        }

        public void setNumberOfRoutes(int numberOfRoutes) {
            this.numberOfRoutes = numberOfRoutes;
        }

        public List<BikeRoute> getBikeRoutes() {
            return bikeRoutes;
        }

        public void setBikeRoutes(List<BikeRoute> bikeRoutes) {
            this.bikeRoutes = bikeRoutes;
        }
    }

    /** Matches the structure of the provided XML file */
    public static class BikeRouteXmlWrapper {

        @JsonProperty("numberOfRoutes")
        private int numberOfRoutes;

        @JsonProperty("BikeRoutes")
        private BikeRouteList bikeRoutes;

        public int getNumberOfRoutes() {
            return numberOfRoutes;
        }

        public void setNumberOfRoutes(int numberOfRoutes) {
            this.numberOfRoutes = numberOfRoutes;
        }

        public BikeRouteList getBikeRoutes() {
            return bikeRoutes;
        }

        public void setBikeRoutes(BikeRouteList bikeRoutes) {
            this.bikeRoutes = bikeRoutes;
        }
    }

    public static class BikeRouteList {

        @JsonProperty("bikeRoute") // Must match XML tag name
        private List<BikeRoute> bikeRoute;

        public List<BikeRoute> getBikeRoutes() {
            return bikeRoute;
        }

        public void setBikeRoutes(List<BikeRoute> bikeRoute) {
            this.bikeRoute = bikeRoute;
        }
    }
}
