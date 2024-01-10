package githubProjectRepo.dipayan-code97.flighttrackingservice.helper;

import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.Plane;
import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.Route;
import githubProjectRepo.dipayan-code97.flighttrackingservice.exception.RouteGeneratorException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class RouteGeneratorTest {

    private final RouteGenerator ROUTE_GENERATOR;

    @Autowired
    public RouteGeneratorTest(RouteGenerator ROUTE_GENERATOR) {
        this.ROUTE_GENERATOR = ROUTE_GENERATOR;
    }

    public RouteGenerator getROUTE_GENERATOR() {
        return this.ROUTE_GENERATOR;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        RouteGeneratorTest that = (RouteGeneratorTest) objectRef;
        return (Objects.equals(getROUTE_GENERATOR(), that.getROUTE_GENERATOR()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getROUTE_GENERATOR()));
    }

    @Test
    void validUnitedStatesFlightTest(){
        Plane plane = new Plane("Test", "Test", 1000, 10, 400, 2300);
        Route route = ROUTE_GENERATOR.fromUnitedStates(plane, 10.5);
        assertEquals("United States", route.getDepartureAirport().getCountry());
        assertTrue(route.getFlightDistanceMiles() <= plane.getRangeMiles());
        assertTrue(route.getFlightDurationHours() <= 10.5);
    }

    @Test
    void noRangeThrowsErrorTest(){
        Plane plane = new Plane("Test", "Test", 1000, 10, 400, 0);
        assertThrows(RouteGeneratorException.class, () -> ROUTE_GENERATOR.fromUnitedStates(plane, 100));
    }

}