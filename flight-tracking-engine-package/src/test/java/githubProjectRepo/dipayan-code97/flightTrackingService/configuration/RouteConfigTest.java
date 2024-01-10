package githubProjectRepo.dipayan-code97.flighttrackingservice.configuration;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class RouteConfigTest {
    private final RouteConfig ROUTE_CONFIG;

    @Autowired
    RouteConfigTest(RouteConfig ROUTE_CONFIG) {
        this.ROUTE_CONFIG = ROUTE_CONFIG;
    }

    @Test
    void scheduledRouteCreationTest() {
        ROUTE_CONFIG.createScheduledRoutes(100).forEach(routeScheduled -> {
            assertTrue(routeScheduled.getRoute().getFlightDurationHours() < 11);
            assertTrue(routeScheduled.getPlane().getRangeMiles() >= routeScheduled.getRoute().getFlightDistanceMiles());
        });

    }
}