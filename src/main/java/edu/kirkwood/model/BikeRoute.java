package edu.kirkwood.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class BikeRoute {
    @JsonProperty("route_number")
    private int routeNumber;

    @JsonProperty("uploader")
    private String uploader;

    @JsonProperty("state_or_country")
    private String stateOrCountry;

    @JsonProperty("title")
    private String title;

    @JsonProperty("date_uploaded")
    private Date dateUploaded;

    @JsonProperty("miles")
    private double miles;

    @JsonProperty("elevation")
    private int elevation;

    @JsonProperty("route_type")
    private String routeType;

    @JsonProperty("roughest_terrain_type")
    private String roughestTerrainType;

    @JsonProperty("description")
    private String description;

    public BikeRoute() {
    }

    public BikeRoute(Date dateUploaded, String description, int elevation, int miles, String roughestTerrainType, int routeNumber, String routeType, String stateOrCountry, String title, String uploader) {
        this.dateUploaded = dateUploaded;
        this.description = description;
        this.elevation = elevation;
        this.miles = miles;
        this.roughestTerrainType = roughestTerrainType;
        this.routeNumber = routeNumber;
        this.routeType = routeType;
        this.stateOrCountry = stateOrCountry;
        this.title = title;
        this.uploader = uploader;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public String getUploader() {
        return uploader;
    }

    public String getStateOrCountry() {
        return stateOrCountry;
    }

    public String getTitle() {
        return title;
    }

    public Date getDateUploaded() {
        return dateUploaded;
    }

    public double getMiles() {
        return miles;
    }

    public int getElevation() {
        return elevation;
    }

    public String getRouteType() {
        return routeType;
    }

    public String getRoughestTerrainType() {
        return roughestTerrainType;
    }

    public String getDescription() {
        return description;
    }

    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public void setStateOrCountry(String stateOrCountry) {
        this.stateOrCountry = stateOrCountry;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateUploaded(Date dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public void setRoughestTerrainType(String roughestTerrainType) {
        this.roughestTerrainType = roughestTerrainType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BikeRoute other = (BikeRoute) obj;
        if (routeNumber != other.routeNumber)
            return false;
        if (uploader == null) {
            if (other.uploader != null)
                return false;
        } else if (!uploader.equals(other.uploader))
            return false;
        if (stateOrCountry == null) {
            if (other.stateOrCountry != null)
                return false;
        } else if (!stateOrCountry.equals(other.stateOrCountry))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (dateUploaded == null) {
            if (other.dateUploaded != null)
                return false;
        } else if (!dateUploaded.equals(other.dateUploaded))
            return false;
        if (miles != other.miles)
            return false;
        if (elevation != other.elevation)
            return false;
        if (routeType == null) {
            if (other.routeType != null)
                return false;
        } else if (!routeType.equals(other.routeType))
            return false;
        if (roughestTerrainType == null) {
            if (other.roughestTerrainType != null)
                return false;
        } else if (!roughestTerrainType.equals(other.roughestTerrainType))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }

    public static Comparator<BikeRoute> compareTitle = (br1, br2) -> br1.title.compareToIgnoreCase(br2.title);
    public static Comparator<BikeRoute> compareRouteNumber = Comparator.comparingInt(BikeRoute::getRouteNumber);
    public static Comparator<BikeRoute> compareDate = (br1, br2) -> br1.dateUploaded.compareTo(br2.dateUploaded);

    @Override
    public String toString() {
        return "BikeRoute [routeNumber: " + routeNumber + ", uploader: " + uploader + ", stateOrCountry: " + stateOrCountry
                + ", title: " + title + ", dateUploaded: " + dateUploaded + ", miles: " + miles + ", elevation: "
                + elevation + ", routeType: " + routeType + ", roughestTerrainType: " + roughestTerrainType
                + ", description: " + description + "]\n";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.routeNumber;
        hash = 19 * hash + Objects.hashCode(this.uploader);
        hash = 19 * hash + Objects.hashCode(this.stateOrCountry);
        hash = 19 * hash + Objects.hashCode(this.title);
        hash = 19 * hash + Objects.hashCode(this.dateUploaded);
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.miles) ^ (Double.doubleToLongBits(this.miles) >>> 32));
        hash = 19 * hash + this.elevation;
        hash = 19 * hash + Objects.hashCode(this.routeType);
        hash = 19 * hash + Objects.hashCode(this.roughestTerrainType);
        hash = 19 * hash + Objects.hashCode(this.description);
        return hash;
    }
}
