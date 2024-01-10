package flightArchitecturePackage.service;

import flightArchitecturePackage.model.Airport;
import flightArchitecturePackage.service.request.FlightRequest;
import flightArchitecturePackage.service.AirportService;
import flightArchitecturePackage.utils.ContructFlightUtility;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AirportServiceTest {

    @Autowired
    private AirportService airportService;

    @Test
    void emptyIcaoCodeMakesAirportNull() {
        assertNull(airportService.findAirportById(""));
    }

    @Test
    void flightNotOverTenHours() {
        Set<Airport> airports =
                airportService.findAirportsWithinMaxHours(new FlightRequest("10", "propeller"), 200);
        double distanceInMiles = ContructFlightUtility.calculateFlightDistanceInMiles(
                airports.get(0), airports.get(1));
        assert(ContructFlightUtility.calculateFlightHours(200, distanceInMiles) <= 10);
    }

    @Test
    void timeOfAnyDoesNotThrowNumberFormatException() {
        assertDoesNotThrow(
                () -> airportService.findAirportsWithinMaxHours(
                        new FlightRequest("any", "propeller"), 100));
    }

}