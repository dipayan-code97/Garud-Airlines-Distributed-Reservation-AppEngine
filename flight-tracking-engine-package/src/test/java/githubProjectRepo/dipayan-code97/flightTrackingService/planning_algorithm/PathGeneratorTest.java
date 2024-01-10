package githubProjectRepo.dipayan-code97.flighttrackingservice.planning_algorithm;

-code97.flighttrackingservice.model.Airport;
-code97.flighttrackingservice.model.Flight;
-code97.flighttrackingservice.model.Plane;
-code97.flighttrackingservice.model.Route;
-code97.flighttrackingservice.service.AirportService;
import githubProjectRepo.dipayan-code97.flighttrackingservice.service.FlightService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class PathGeneratorTest {

    private final FlightService FLIGHT_SERVICE;
    private final PathGenerator PATH_GENERATOR;
    private final AirportService AIRPORT_SERVICE;

    @Autowired
    public PathGeneratorTest(FlightService FLIGHT_SERVICE,
                             PathGenerator PATH_GENERATOR,
                             AirportService AIRPORT_SERVICE) {
        this.FLIGHT_SERVICE = FLIGHT_SERVICE;
        this.PATH_GENERATOR = PATH_GENERATOR;
        this.AIRPORT_SERVICE = AIRPORT_SERVICE;
    }

    @Test
    void validate() {
        Plane plane = new Plane("B737", "B737", 10, 10, 10, 10);
        Set<Airport> airports = AIRPORT_SERVICE.findAllAirports();
        Airport kbos = airports.stream().filter(airportRef -> airportRef.getIcaoCode().equals("KBOS")).findFirst().orElseThrow();
        Airport klga = airports.stream().filter(airportRef -> airportRef.getIcaoCode().equals("KLGA")).findFirst().orElseThrow();
        Airport lirp = airports.stream().filter(airportRef -> airportRef.getIcaoCode().equals("LIRP")).findFirst().orElseThrow();
        Airport eidw = airports.stream().filter(airportRef -> airportRef.getIcaoCode().equals("EIDW")).findFirst().orElseThrow();
        Airport olba = airports.stream().filter(airportRef -> airportRef.getIcaoCode().equals("OLBA")).findFirst().orElseThrow();
        Airport eddf = airports.stream().filter(airportRef -> airportRef.getIcaoCode().equals("EDDF")).findFirst().orElseThrow();
        Set<Flight> flights = Set.of(
                new Flight(plane, new Route(kbos, klga, plane), LocalDateTime.now()),
                new Flight(plane, new Route(klga, olba, plane), LocalDateTime.now().plusDays(2))
        );

        assertDoesNotThrow(() -> PATH_GENERATOR.uniquePath(flights, "KBOS", "OLBA"));
        Set<Route> route = PATH_GENERATOR.uniquePath(flights, "KBOS", "OLBA");

        assertEquals("KBOS", route.get(0).getFlight().getRoute().getDepartureAirport().getIcaoCode());
        assertEquals("OLBA", route.get(route.size() - 1).getFlight().getRoute().getDestinationAirport().getIcaoCode());
        for (int routeIndex = 0; routeIndex < (route.size() - 1); routeIndex++) {
            assertTrue(route.get(routeIndex).getFlight().getLandingDateTime()
                    .isBefore(route.get(routeIndex + 1).getFlight().getTakeOffDateTime()));
        }

        //test to make sure single flight path is found
        Set<Flight> flights2 = (Set.of(
                new Flight(plane, new Route(kbos, klga, plane), LocalDateTime.now()))
        );
        assertDoesNotThrow(() -> PATH_GENERATOR.uniquePath(flights2, "KBOS", "KLGA"));
        Set<Route> route2 = PATH_GENERATOR.uniquePath(flights2, "KBOS", "KLGA");
        assertEquals("KBOS", route2.get(0).getFlight().getRoute().getDepartureAirport().getIcaoCode());
        assertEquals("KLGA", route2.get(0).getFlight().getRoute().getDestinationAirport().getIcaoCode());

        assertEquals(1, route2.size());
    }

    @Test
    void complexValidation() {
        Plane plane = new Plane("B737", "B737", 10, 10, 500, 10);
        Set<Airport> airports = AIRPORT_SERVICE.findAllAirports();
        Airport kbos = airports.stream().filter(airportRef -> airportRef.getIcaoCode().equals("KBOS")).findFirst().orElseThrow();
        Airport klga = airports.stream().filter(airportRef -> airportRef.getIcaoCode().equals("KLGA")).findFirst().orElseThrow();
        Airport lirp = airports.stream().filter(airportRef -> airportRef.getIcaoCode().equals("LIRP")).findFirst().orElseThrow();
        Airport eidw = airports.stream().filter(airportRef -> airportRef.getIcaoCode().equals("EIDW")).findFirst().orElseThrow();
        Airport olba = airports.stream().filter(airportRef -> airportRef.getIcaoCode().equals("OLBA")).findFirst().orElseThrow();
        Airport eddf = airports.stream().filter(airportRef -> airportRef.getIcaoCode().equals("EDDF")).findFirst().orElseThrow();
        Set<Flight> flight = Set.of(
                new Flight(plane, new Route(kbos, klga, plane), LocalDateTime.now()), //1
                new Flight(plane, new Route(klga, olba, plane), LocalDateTime.now().plusDays(3)),
                new Flight(plane, new Route(klga, eidw, plane), LocalDateTime.now().plusDays(6)), //2
                new Flight(plane, new Route(eidw, lirp, plane), LocalDateTime.now().plusDays(9)), //3
                new Flight(plane, new Route(lirp, eddf, plane), LocalDateTime.now().plusDays(12)), //4
                new Flight(plane, new Route(eidw, lirp, plane), LocalDateTime.now().plusDays(15)),
                new Flight(plane, new Route(lirp, eidw, plane), LocalDateTime.now().plusDays(18))
        );

        assertDoesNotThrow(() -> PATH_GENERATOR.uniquePath(flight, "KBOS", "EDDF"));
        Set<Route> route = PATH_GENERATOR.uniquePath(flight, "KBOS", "EDDF");

        assertEquals("KBOS", route.get(0).getFlight().getRoute().getDepartureAirport().getIcaoCode());
        assertEquals("EDDF", route.get(route.size() - 1).getFlight().getRoute().getDestinationAirport().getIcaoCode());
        for(int routeIndex = 0; routeIndex < (route.size() - 1); routeIndex++){
            assertTrue(route.get(routeIndex).getFlight().getLandingDateTime()
                    .isBefore(route.get(routeIndex + 1).getFlight().getTakeOffDateTime()));
        }
    }

}