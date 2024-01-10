package githubProjectRepo.dipayan-code97.flightTrackingService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "airport")
@Table(name = "airports")
public class Airport {

    @Id
    @Column(name = "icao_code")
    private String icaoCode;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "continent")
    private String continent;

    @Column(name = "country")
    private String country;

    public Airport() {

    }
    public Airport(String icaoCode, String name, Double latitude, Double longitude, String continent, String country) {
        this.icaoCode = icaoCode;
        this.name = name;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (objectRef == null || getClass() != objectRef.getClass()) return false;
        if (!super.equals(objectRef)) return false;
        Airport airport = (Airport) objectRef;
        return (Objects.equals(getIcaoCode(), airport.getIcaoCode())
                && Objects.equals(getName(), airport.getName())
                && Objects.equals(getLatitude(), airport.getLatitude())
                && Objects.equals(getLongitude(), airport.getLongitude())
                && Objects.equals(getContinent(), airport.getContinent())
                && Objects.equals(getCountry(), airport.getCountry()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getIcaoCode(),
                getName(), getLatitude(),
                getLongitude(), getContinent(),
                getCountry()));
    }
}
