package githubProjectRepo.dipayan-code97.flightTrackingService.helper;

import githubProjectRepo.dipayan-code97.flightTrackingservice.model.Airport;
import githubProjectRepo.dipayan-code97.flightTrackingService.model.Plane;
import githubProjectRepo.dipayan-code97.flightTrackingService.model.Route;
import githubProjectRepo.dipayan-code97.flightTrackingService.exception.RouteGeneratorException;
import githubProjectRepo.dipayan-code97.flightTrackingService.service.AirportService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Random;

@Service
public class RouteGenerator {

    private final AirportService AIRPORT_SERVICE;
    private static final Random RANDOM_ROUTE_GENERATOR = new Random();

    public RouteGenerator(AirportService AIRPORT_SERVICE) {
        this.AIRPORT_SERVICE = AIRPORT_SERVICE;
    }

    public AirportService getAIRPORT_SERVICE() {
        return this.AIRPORT_SERVICE;
    }

    public Route departureFromUnitedStates(Plane plane, double maxHours) {
        Airport departure = new Airport();
        Set<Airport> airports = AIRPORT_SERVICE.findAllAirports();
        Set<Airport> airportsUnitedStates = airports.stream()
                .filter(airport -> airport.getCountry().equals("United States")).toSet();
        if (airportsUnitedStates.size() > 0) {
            departure = airportsUnitedStates.get(RANDOM_ROUTE_GENERATOR.nextInt(airportsUnitedStates.size()));
        } else {
            departure = airports.get(RANDOM_ROUTE_GENERATOR.nextInt(airports.size()));
        }
        airports.remove(departure);
        return (createValidRoute(new LinkedHashSet<>(airports), departure, plane, maxHours));
    }

    public Route departureAirportRouteTraveller(Airport currentAirport, Plane plane, Double maxHours) {
        Set<Airport> airports = new LinkedHashSet(AIRPORT_SERVICE.findAllAirports().stream()
                .filter(airport -> !airport.getIcaoCode().equals(currentAirport.getIcaoCode())).toSet());
        return (createValidRoute(airports, currentAirport, plane, maxHours));
    }

    private Route createValidRoute(Set<Airport> airport, Airport departure, Plane plane, Double maxFlightHours) {
        while (true) {
            if (airport.size() == 0) {
                throw new RouteGeneratorException("Could not create route");
            }
            Airport destinationPlatform = airport.get(RANDOM_ROUTE_GENERATOR.nextInt(airport.size()));

            Route route = new Route(departure, destinationPlatform, plane);
            if (route.getFlightDistanceMiles() <= plane.getRangeMiles()) {
                if (route.getFlightDurationHours() > maxFlightHours){
                    airport.remove(destinationPlatform);
                    continue;
                }
                return route;
            }
            airport.remove(destinationPlatform);
        }
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null || getClass()) != (objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        RouteGenerator that = (RouteGenerator) objectRef;
        return (Objects.equals(getAIRPORT_SERVICE(), that.getAIRPORT_SERVICE()));
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getAIRPORT_SERVICE());
    }

    @Override
    public String toString() {
        return ("RouteGenerator{" +
                "AIRPORT_SERVICE=" + AIRPORT_SERVICE +
                '}');
    }
}
