package githubProjectRepo.dipayan-code97.flighttrackingservice.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    @Test
    void correctLandingTime() {
        Plane plane = new Plane("Test", "Test", 100, 110, 100, 1000);
        Airport boston = new Airport("Boston", "Test", 42.3601, 71.0589, "Test", "Test");
        Airport northYorkCity = new Airport("NYC", "Test", 40.7128, 74.0060, "Test", "Test");
        Route route = new Route(boston, northYorkCity, plane);

        LocalDateTime departureTime = LocalDateTime.now();
        Flight flight = new Flight(plane, route, departureTime);
        assertEquals(departureTime.plusMinutes((int) (1.65 * 60)), flight.getLandingDateTime());
    }
}