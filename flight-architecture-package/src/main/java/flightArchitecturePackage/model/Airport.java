package flightArchitecturePackage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "airport")
@Table(name = "airport")

public class Airport {

    @Id
    private String icaoCode;

    @Column(name = "airport_size", nullable = false)
    private String dimensionalSize;

    @Column(name = "airport_name", nullable = false)
    private String airportName;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "continent", nullable = false)
    private String continent;

    @Column(name = "country", nullable = false)
    private String country;

    public Airport(String icaoCode,
                   String dimensionalSize,
                   String airportName,
                   Double latitude,
                   Double longitude,
                   String continent,
                   String country) {
        this.icaoCode = icaoCode;
        this.dimensionalSize = dimensionalSize;
        this.airportName = airportName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.continent = continent;
        this.country = country;
    }

    public String getIcaoCode() {
        return this.icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
    }

    public String getDimensionalSize() {
        return this.dimensionalSize;
    }

    public void setDimensionalSize(String dimensionalSize) {
        this.dimensionalSize = dimensionalSize;
    }

    public String getAirportName() {
        return this.airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getContinent() {
        return this.continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        Airport airport = (Airport) objectRef;
        return (Objects.equals(getIcaoCode(), airport.getIcaoCode())
                && Objects.equals(getDimensionalSize(), airport.getDimensionalSize())
                && Objects.equals(getAirportName(), airport.getAirportName())
                && Objects.equals(getLatitude(), airport.getLatitude())
                && Objects.equals(getLongitude(), airport.getLongitude())
                && Objects.equals(getContinent(), airport.getContinent())
                && Objects.equals(getCountry(), airport.getCountry()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getIcaoCode(),
                getDimensionalSize(), getAirportName(),
                getLatitude(), getLongitude(),
                getContinent(), getCountry()));
    }

    @Override
    public String toString() {
        return ("Airport{" +
                "IcaoCode='" + icaoCode + '\'' +
                ", Size='" + dimensionalSize + '\'' +
                ", AirportName='" + airportName + '\'' +
                ", Latitude=" + latitude +
                ", Longitude=" + longitude +
                ", Continent='" + continent + '\'' +
                ", Country='" + country + '\'' +
                '}');
    }
}