package githubProjectRepo.dipayan-code97.flightTrackingService.dto;

import githubProjectRepo.dipayan-code97.flightTrackingService.entity.Route;
import githubProjectRepo.dipayan-code97.flightTrackingService.utils.DateTimeUtils;

public class RouteDTO {

    private AirportDTO departure;
    private AirportDTO destination;
    private String flightTime;
    private Double distanceMiles;

    public RouteDTO(Route route) {
        this.departure = new AirportDTO(route.getDepartureAirport());
        this.destination = new AirportDTO(route.getDestinationAirport());
        this.flightTime = DateTimeUtils.hoursToHHMM(route.getFlightDurationHours());
        this.distanceMiles = Double.parseDouble(route.getFlightDistanceMiles());
    }

    public AirportDTO getDeparture() {
        return this.departure;
    }

    public void setDeparture(AirportDTO departure) {
        this.departure = departure;
    }

    public AirportDTO getDestination() {
        return this.destination;
    }

    public void setDestination(AirportDTO destination) {
        this.destination = destination;
    }

    public String getFlightTime() {
        return this.flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public Double getDistanceMiles() {
        return this.distanceMiles;
    }

    public void setDistanceMiles(Double distanceMiles) {
        this.distanceMiles = distanceMiles;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        RouteDTO routeDTO = (RouteDTO) objectRef;
        return (Objects.equals(getDeparture(), routeDTO.getDeparture())
                && Objects.equals(getDestination(), routeDTO.getDestination())
                && Objects.equals(getFlightTime(), routeDTO.getFlightTime())
                && Objects.equals(getDistanceMiles(), routeDTO.getDistanceMiles()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getDeparture(),
                getDestination(), getFlightTime(), getDistanceMiles()));
    }

    @Override
    public String toString() {
        return ("RouteDTO{" +
                "Departure=" + departure +
                ", Destination=" + destination +
                ", FlightTime='" + flightTime + '\'' +
                ", DistanceMiles=" + distanceMiles +
                '}');
    }
}
