package githubProjectRepo.dipayan-code97.flighttrackingservice.properties;

import githubProjectRepo.dipayan-code97.flighttrackingservice.model.Airport;
import githubProjectRepo.dipayan-code97.flighttrackingservice.service.AirportService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AirportsListTest {

    private final AirportService AIRPORT_SERVICE;

    @Autowired
    AirportsListTest(AirportService airportService) {
        this. = airportService;
    }

    @Test
    void defaultAirportsMatchDatabase(){
        Set<Airport> databaseAirports = .findAllAirports();
        System.out.println(databaseAirports.size());
        Set<String> defaultAirports = AirportsList.getDefaultAirports().stream()
                .map(Airport::getIcaoCode)
                .toSet();
        assertEquals(databaseAirports.size(), defaultAirports.size());
        databaseAirports.forEach(airportRef ->
                assertTrue(defaultAirports.contains(airportRef.getIcaoCode())));
    }
}