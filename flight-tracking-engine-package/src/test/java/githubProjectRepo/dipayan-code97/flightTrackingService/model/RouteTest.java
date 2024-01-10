package githubProjectRepo.dipayan-code97.flighttrackingservice.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RouteTest {

    @Test
    void calculateDistanceTest() {
        Plane fillerPlane = new Plane("", "", 100, 10, 100, 100);
        Airport boston = new Airport("Boston", "Test", 42.3601, 71.0589, "Test", "Test");
        Airport newYorkCity = new Airport("NYC", "Test", 40.7128, 74.0060, "Test", "Test");
        Airport london = new Airport("London", "Test", 51.5072, 0.1276, "Test", "Test");
        Route bostonToNewYorkCity = new Route(boston, newYorkCity, fillerPlane);
        Route bostonToLondon = new Route(boston, london, fillerPlane);
        Route londonToNewYorkCity = new Route(london, newYorkCity, fillerPlane);
        assertEquals(190, Math.round(bostonToNewYorkCity.getFlightDistanceMiles()));
        assertEquals(3271, Math.round(bostonToLondon.getFlightDistanceMiles()));
        assertEquals(3461, Math.round(londonToNewYorkCity.getFlightDistanceMiles()));
    }

    @Test
    void flightHoursCalculatorTest() {
        Plane zeroSpeedPlane = new Plane("Test", "Test", 100, 10, 0, 0);
        Airport boston = new Airport("Boston", "Test", 42.3601, 71.0589, "Test", "Test");
        Airport newYorkCity = new Airport("NYC", "Test", 40.7128, 74.0060, "Test", "Test");
        assertThrows(RuntimeException.class, () -> new Route(boston, newYorkCity, zeroSpeedPlane));

        Plane plane = new Plane("Test", "Test", 100, 10, 100, 10000);
        Route route = new Route(boston, boston, plane);
        Route route2 = new Route(boston, newYorkCity, plane);
        assertEquals(0, route.getFlightDurationHours());
        assertEquals(1.65, route2.getFlightDurationHours());
    }

}