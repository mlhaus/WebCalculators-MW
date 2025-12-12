package edu.kirkwood.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

import java.util.Date;

/**
 * This class handles the result XML element
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BikeRouteSearchResult extends BikeRoute {
    @XmlAttribute(name = "routeNumber")
    private int routeNumber;
    @XmlAttribute(name = "uploader")
    private String uploader;
    @XmlAttribute(name = "state_or_country")
    private String state_or_country;
    @XmlAttribute(name = "title")
    private String title;
    @XmlAttribute(name = "date_uploaded")
    private Date date_uploaded;
    @XmlAttribute(name = "miles")
    private double miles;
    @XmlAttribute(name = "elevation")
    private int elevation;
    @XmlAttribute(name = "route_type")
    private String route_type;
    @XmlAttribute(name = "roughest_terrain_type")
    private String roughest_terrain_type;
    @XmlAttribute(name = "description")
    private String description;

    public int getRouteNumber() {
        return routeNumber;
    }

    public String getUploader() {
        return uploader;
    }

    public String getStateOrCountry() {
        return state_or_country;
    }

    public String getTitle() {
        return title;
    }

    public Date getDateUploaded() {
        return date_uploaded;
    }

    public double getMiles() {
        return miles;
    }

    public int getElevation() {
        return elevation;
    }

    public String getRouteType() {
        return route_type;
    }

    public String getRoughestTerrainType() {
        return roughest_terrain_type;
    }

    public String getDescription() {
        return description;
    }

    
}
