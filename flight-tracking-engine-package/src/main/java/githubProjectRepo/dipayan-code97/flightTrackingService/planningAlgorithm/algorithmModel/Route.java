package githubProjectRepo.dipayan-code97.flightTrackingService.planning_algorithm;

import githubProjectRepo.dipayan-code97.flightTrackingService.model.Flight;
import githubProjectRepo.dipayan-code97.flightTrackingService.model.Route;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Route {

    private final AirportNode TARGET_NODE;
    private final Flight FLIGHT;
    private final Integer PASSENGER_LUGGAGE_WEIGHT;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd HH:mm");

    public Route(AirportNode TARGET_NODE, Flight FLIGHT) {
        this.TARGET_NODE = TARGET_NODE;
        this.FLIGHT = FLIGHT;
    }

    public AirportNode getTarget() {
        return this.TARGET_NODE;
    }

    public Flight getFlight() {
        return this.FLIGHT;
    }

    public Integer getpassengerLuggageWeight() {
        return this.PASSENGER_LUGGAGE_WEIGHT;
    }

    public String format(LocalDateTime dateTime){
        return dateTime.format(DATE_TIME_FORMATTER);
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if (objectRef == null || getClass() != objectRef.getClass()) return false;
        if (!super.equals(objectRef)) return false;
        Route route = (Route) objectRef;
        return (Objects.equals(getTarget(), route.getTarget())
                && Objects.equals(getFlight(), route.getFlight())
                && Objects.equals(getpassengerLuggageWeight(), route.getPassengerWeight()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getTarget(), getFlight(),
                getpassengerLuggageWeight()));
    }
    public String toString() {
        return (format(this.getFlight().getTakeOffDateTime())
                + " : " + this.getFlight().getRoute().getDepartureAirport().getIcaoCode()
                + "->" + this.getFlight().getRoute().getDestinationAirport().getIcaoCode()
                + " : " + format(this.getFlight().getLandingDateTime()));
    }

}
