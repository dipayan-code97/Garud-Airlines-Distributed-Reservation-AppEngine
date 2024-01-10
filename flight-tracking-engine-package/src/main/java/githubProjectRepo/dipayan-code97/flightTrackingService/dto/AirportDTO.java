package githubProjectRepo.dipayan-code97.flightTrackingService.dto;

import githubProjectRepo.dipayan-code97.flightTrackingService.model.Airport;

public class AirportDTO {

    private String icaoCode;
    private String airportName;
    private Double latitude;
    private Double longitude;
    private String continent;
    private String country;

    public AirportDTO(Airport airport) {
        this.icaoCode = airport.getIcaoCode();
        this.airportName = airport.getName();
        this.latitude = airport.getLatitude();
        this.longitude = airport.getLongitude();
        this.continent = airport.getContinent();
        this.country = airport.getCountry();
    }

    public String getIcaoCode() {
        return this.icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
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

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        AirportDTO that = (AirportDTO) object;
        return Objects.equals(getIcaoCode(), that.getIcaoCode())
                && Objects.equals(getAirportName(), that.getAirportName())
                && Objects.equals(getLatitude(), that.getLatitude())
                && Objects.equals(getLongitude(), that.getLongitude())
                && Objects.equals(getContinent(), that.getContinent())
                && Objects.equals(getCountry(), that.getCountry());
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getIcaoCode(),
                getAirportName(), getLatitude(),
                getLongitude(), getContinent(),
                getCountry()));
    }

    @Override
    public String toString() {
        return ("AirportDTO{" +
                "IcaoCode='" + icaoCode + '\'' +
                ", AirportName='" + airportName + '\'' +
                ", Latitude=" + latitude +
                ", Longitude=" + longitude +
                ", Continent='" + continent + '\'' +
                ", Country='" + country + '\'' +
                '}');
    }
}
