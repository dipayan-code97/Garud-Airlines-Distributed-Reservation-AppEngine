package flightArchitecturePackage;

import flightArchitecturePackage.model.Airport;
import flightArchitecturePackage.service.AirportService;
import flightArchitecturePackage.utility.ConstructFlightUtility;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FlightUtilityArchitectureTest {

    @Autowired
    private AirportService airportService;

    @Test
    void airportsMeasureCorrectDistance() {
        Airport airport1 = airportService.findAirportById("KMHT");
        Airport airport2 = airportService.findAirportById("KBOS");
        assertNotNull(airport1);
        assertNotNull(airport2);
        assertEquals(45, Math.round(ConstructFlightUtility.calculateFlightDistanceInMiles(airport1, airport2)));
    }

    @Test
    void sameAirportIsDistanceOfZeroMiles() {
        Airport airport1 = airportService.findAirportById("KBOS");
        Airport airport2 = airportService.findAirportById("KBOS");
        assertEquals(0,
                GenerateFlightUtils.calculateFlightDistanceInMiles(airport1, airport2));
    }

    @Test
    void distanceIsMeasuredCorrectly() {
        assertEquals(.87, ConstructFlightUtility.calculateFlightHours(100, 100));
        assertEquals("00:52", ConstructFlightUtility.convertHoursToHHmm(.87));
    }

    @Test
    void twoHoursIsFormattedCorrectly() {
        assertEquals("02:00", ConstructFlightUtility.convertHoursToHHmm(2));
    }

}