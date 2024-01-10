package githubProjectRepo.dipayan-code97.flighttrackingservice.planning_algorithm;

import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.Airport;
import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.Flight;
import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.Route;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AirportNode {

    private final String icao;
    private Airport airport;
    private Set<Flight> departingFlights;
    //these are edges between airports
    private Map<Set<AirportNode>, Set<AirportNode>> connectedAirports;

    public AirportNode(Airport airport, Set<Flight> connectingFlights) {
        this.icao = airport.getIcaoCode();
        this.airport = airport;
        this.departingFlights = connectingFlights;
    }

    public AirportNode(String icao, Airport airport, Set<Flight> departingFlights,
                       Map<Set<AirportNode>, Set<AirportNode>> connectedAirports) {
        this.icao = icao;
        this.airport = airport;
        this.departingFlights = connectedAirports;
        this.connectedAirports = new LinkedHashSet<>();
    }

    public String getIcao() {
        return this.icao;
    }

    public Airport getAirport() {
        return this.airport;
    }

    public Set<Flight> getDepartingFlights() {
        return this.departingFlights;
    }

    public Map<Set<AirportNode>, Set<AirportNode>> getConnectedAirports() {
        return this.connectedAirports;
    }

    //filter all known airport nodes to see which connect to this
    // (based on destination of connectingFlights
    public void addConnectedAirports(Set<AirportNode> allActiveAirports) {
        Set<AirportNode> destinations = this.departingFlights.stream()
                .map(flight -> flight.getRoute().getDestinationAirport().getIcaoCode())
                .filter(icaoCode -> !icaoCode.equals(this.icao))
                .collect(Collectors.toSet());
        for (AirportNode airport : allActiveAirports) {
            if (destinations.contains(airport.icao)) {
                this.connectedAirports.add(airport);
            }
        }
    }

    @Override
    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;

        AirportNode that = (AirportNode) objectRef;
        return Objects.equals(icao, that.icao);
    }

    @Override
    public int hashCode() {
        return (icao != null ? icao.hashCode() : 0);
    }

    @Override
    public String toString() {
        return ("AirportNode{" +
                "icao='" + icao + '\'' +
                ", Airport=" + airport +
                ", DepartingFlights=" + departingFlights +
                ", ConnectedAirports=" + connectedAirports +
                '}');
    }
}
