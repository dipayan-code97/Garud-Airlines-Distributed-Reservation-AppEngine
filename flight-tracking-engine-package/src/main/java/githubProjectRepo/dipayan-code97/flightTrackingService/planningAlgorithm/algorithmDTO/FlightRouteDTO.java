package githubProjectRepo.dipayan-code97.flighttrackingservice.planning_algorithm;

import githubProjectRepo.dipayan-code97.flighttrackingservice.dto.AirportDTO;
import githubProjectRepo.dipayan-code97.flighttrackingservice.dto.FlightSummaryDTO;
import java.util.Set;
import java.util.stream.Collectors;

public class FlightRouteDTO {

    private final Set<AirportDTO> airportsVisited;
    private final Set<FlightSummaryDTO> flightRoute;

    public FlightRouteDTO(FlightRoute flightRoute){
        this.airportsVisited = flightRoute.getAirportsVisited().stream().map(AirportDTO::new).collect(Collectors.toSet());
        this.flightRoute = flightRoute.getFlightPath().stream().map(FlightSummaryDTO::new).collect(Collectors.toSet());
    }

    public Set<AirportDTO> getAirportsVisited() {
        return this.airportsVisited;
    }

    public Set<FlightSummaryDTO> getFlightRoute() {
        return this.flightRoute;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        FlightPathDTO that = (FlightPathDTO) objectRef;
        return (Objects.equals(getAirportsVisited(), that.getAirportsVisited())
                && Objects.equals(getFlightRoute(), that.getFlightRoute()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getAirportsVisited(),
                getFlightRoute()));
    }

    @Override
    public String toString() {
        return ("FlightRouteDTO{" +
                "AirportsVisited=" + airportsVisited +
                ", FlightRoute=" + flightRoute +
                '}');
    }
}
