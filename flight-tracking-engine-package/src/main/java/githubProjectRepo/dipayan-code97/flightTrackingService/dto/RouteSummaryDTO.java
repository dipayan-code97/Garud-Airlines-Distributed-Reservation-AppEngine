package githubProjectRepo.dipayan-code97.flightTrackingService.dto;

import githubProjectRepo.dipayan-code97.flightTrackingService.entity.Route;
import githubProjectRepo.dipayan-code97.flightTrackingService.utils.DateTimeUtils;

public class RouteSummaryDTO {

    private AirportSummaryDTO departure;
    private AirportSummaryDTO destination;
    private String flightTime;
    private Double distanceMiles;

    public RouteSummaryDTO(Route route) {
        this.departure = new AirportSummaryDTO(route.getDepartureAirport());
        this.destination = new AirportSummaryDTO(route.getDestinationAirport());
        this.flightTime = DateTimeUtils.hoursToHHMM(route.getFlightDurationHours());
        this.distanceMiles = route.getFlightDistanceMiles();
    }

    public AirportSummaryDTO getDeparture() {
        return this.departure;
    }

    public void setDeparture(AirportSummaryDTO departure) {
        this.departure = departure;
    }

    public AirportSummaryDTO getDestination() {
        return this.destination;
    }

    public void setDestination(AirportSummaryDTO destination) {
        this.destination = destination;
    }

    public String getFlightTime() {
        return this.flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public double getDistanceMiles() {
        return this.distanceMiles;
    }

    public void setDistanceMiles(double distanceMiles) {
        this.distanceMiles = distanceMiles;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null || getClass() != objectRef.getClass()) return false;
        if (!super.equals(objectRef)) return false;
        RouteSummaryDTO that = (RouteSummaryDTO) objectRef;
        return ((Double.compare(that.getDistanceMiles(), getDistanceMiles()) == 0)
                && Objects.equals(getDeparture(), that.getDeparture())
                && Objects.equals(getDestination(), that.getDestination())
                && Objects.equals(getFlightTime(), that.getFlightTime()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getDeparture(),
                getDestination(), getFlightTime(), getDistanceMiles()));
    }

    @Override
    public String toString() {
        return ("RouteSummaryDTO{" +
                "Departure=" + departure +
                ", Destination=" + destination +
                ", FlightTime='" + flightTime + '\'' +
                ", DistanceMiles=" + distanceMiles +
                '}');
    }
}
