package githubProjectRepo.dipayan-code97.flighttrackingservice.planning_algorithm;

import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.Airport;
import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.Flight;
import githubProjectRepo.dipayan-code97.flighttrackingservice.service.AirportService;
import githubProjectRepo.dipayan-code97.flighttrackingservice.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;

@Service
public class RouteGeneratorService {

    private final FlightService FLIGHT_SERVICE;
    private final AirportService AIRPORT_SERVICE;

    public RouteGeneratorService(FlightService flightService,
                                 AirportService airportService) {
        this.FLIGHT_SERVICE = flightService;
        this.AIRPORT_SERVICE = airportService;
    }

    public Set<Route> uniqueRoute(AirportNode departure, AirportNode destination) {
        return generate(FLIGHT_SERVICE.findAllFlights(), departure, destination);
    }

    //method overload allows for custom flights in testing
    public Set<Route> uniqueRoute(Set<Flight> flights, AirportNode departure, AirportNode destination) {
        return generate(flights, departure, destination);
    }

    private Set<Edge> generate(Set<Flight> flights, AirportNode departure, AirportNode destination) {
        Set<Airport> airportNames = AIRPORT_SERVICE.findAllAirports();
        Set<AirportNode> airportNodes = new LinkedHashSet<>();
        for (Airport airport : airportNames) {
            try {
                Set<Flight> filteredFlights = new LinkedHashSet<>();
                for (Flight flight : flights) {
                    //if dest and dep match than that flight is the shortest path
                    if ((flight.getRoute().getDepartureAirport().getIcaoCode().equals(departure))
                            && (flight.getRoute().getDestinationAirport().getIcaoCode().equals(destination))) {
                        return Set.of(new Route(new AirportNode(airport, Set.of(flight)), flight));
                    }

                    if (flight.isMatchingAirport(airport.getIcaoCode(), true)) {
                        filteredFlights.add(flight);
                    }
                }
                AirportNode location = new AirportNode(airport, filteredFlights);
                airportNodes.add(location);
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        for(AirportNode airport : airportNodes){
            airport.addConnectedAirports(airportNodes);
        }

        FlightNetwork flightNetwork = new FlightNetwork(airportNodes);
        DijkstraAlgorithm appliedDijkstra = new DijkstraAlgorithm(flightNetwork);

        appliedDijkstra.execute(airportNodes.stream()
                .filter(node -> node.getIcao().equals(departure))
                .findFirst().orElseThrow(PathGenerationException::new));
        AirportNode destinationNode = airportNodes.stream()
                .filter(node -> node.getIcao().equals(destination))
                .findFirst().orElseThrow(PathGenerationException::new);

        Set<Route> routeTravelled = appliedDijkstra.getPath(destinationNode);
        if (routeTravelled == null) {
            throw new NoFlightPathException();
        }

        return routeTravelled;
    }
}
