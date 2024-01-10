package githubProjectRepo.dipayan-code97.flighttrackingservice.planning_algorithm;

import githubProjectRepo.dipayan-code97.flighttrackingservice.model.Airport;
import githubProjectRepo.dipayan-code97.flighttrackingservice.model.Flight;
import java.util.Set;
import java.util.LinkedHashSet;

public class FlightRoute {

    private Set<Airport> airportsVisited;
    private Set<Flight> flightRoute;

    public FlightRoute(Set<Airport> airportsVisited,
                       Set<Flight> flightRoute) {
        this.airportsVisited = airportsVisited;
        this.flightRoute = flightRoute;
    }

    public Set<Airport> getAirportsVisited() {
        return this.airportsVisited;
    }

    public void setAirportsVisited(Set<Airport> airportsVisited) {
        this.airportsVisited = airportsVisited;
    }

    public Set<Flight> getFlightRoute() {
        return this.flightRoute;
    }

    public void setFlightRoute(Set<Flight> flightRoute) {
        this.flightRoute = flightRoute;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        FlightRoute that = (FlightRoute) objectRef;
        return (Objects.equals(getAirportsVisited(), that.getAirportsVisited())
                && Objects.equals(getFlightRoute(), that.getFlightRoute()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getAirportsVisited(), getFlightRoute()));
    }

    @Override
    public String toString() {
        return ("FlightRoute{" +
                "AirportsVisited=" + airportsVisited +
                ", FlightRoute=" + flightRoute +
                '}');
    }
}
