package githubProjectRepo.dipayan-code-97.flighttrackingservice.service;

import githubProjectRepo.dipayan-code-97.flighttrackingservice.model.Flight;
import githubProjectRepo.dipayan-code-97.flighttrackingservice.utils.DateTimeUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Queue;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class FlightServiceTest {

    private final FlightService FLIGHT_SERVICE;
    private final AirportService AIRPORT_SERVICE;
    private final ScheduledRouteService SCHEDULED_ROUTE_SERVICE;
    private final PlaneService PLANE_SERVICE;

    @Autowired
    public FlightServiceTest(FlightService FLIGHT_SERVICE,
                             AirportService AIRPORT_SERVICE,
                             ScheduledRouteService SCHEDULED_ROUTE_SERVICE,
                             PlaneService PLANE_SERVICE) {
        this.FLIGHT_SERVICE = FLIGHT_SERVICE;
        this.AIRPORT_SERVICE = AIRPORT_SERVICE;
        this.SCHEDULED_ROUTE_SERVICE = SCHEDULED_ROUTE_SERVICE;
        this.PLANE_SERVICE = PLANE_SERVICE;
    }

    @Test
    void noScheduleDiscrepancies() {
        Set<Flight> flight = FLIGHT_SERVICE.findAllFlights();
        flight.stream().map(Flight::getPlane).collect(Collectors.toSet()).forEach(planeRef -> {
            Queue<Flight> flightsByPlane = flight.stream()
                    .filter(planes -> planes.getPlane().equals(planeRef))
                    .sorted(Comparator.comparing(Flight::getTakeOffDateTime))
                    .collect(Collectors.toCollection(LinkedList::new));
            while (flightsByPlane.peek() != null) {
                Flight flight = flightsByPlane.poll();
                LocalDateTime landing = flight.getTakeOffDateTime().plusMinutes((int) (flight.getRoute().getFlightDurationHours() * 60));
                if (flightsByPlane.peek() == null) {
                    continue;
                }
                LocalDateTime nextFlightDeparture = flightsByPlane.peek().getTakeOffDateTime();
                if (nextFlightDeparture.isBefore(landing)) {
                    System.out.println("Call Sign: " + flight.getPlane().getCallSign());
                    System.out.println("Takeoff time: " + DateTimeUtils.format(flight.getTakeOffDateTime()));
                    System.out.println("Landing time: " + DateTimeUtils.format(landing));
                    System.out.println("Next Takeoff time: " + DateTimeUtils.format(nextFlightDeparture));
                    System.out.println();
                }
                assertFalse(nextFlightDeparture.isBefore(landing.plusMinutes(30)));
            }
        });
    }

    @Test
    void liveFlightTest() {
        LocalDateTime localDateTime = LocalDateTime.now();
        FLIGHT_SERVICE.findLiveFlights().forEach(flightRef -> {
            assertTrue(flightRef.getLandingDateTime().isAfter(localDateTime));
            assertTrue(flightRef.getTakeOffDateTime().isBefore(localDateTime));
        });
    }

    @Test
    void dateRangeTest() {
        String start = DateTimeUtils.format(LocalDate.now());
        String end = DateTimeUtils.format(LocalDate.now().plusDays(3));
        FLIGHT_SERVICE.findFlightsByDateRange(start, end).forEach(flightSchedulerRef -> {
            assertFalse(flightSchedulerRef.getDate().isBefore(DateTimeUtils.toDate(start)));
            assertFalse(flightSchedulerRef.getDate().isAfter(DateTimeUtils.toDate(end)));
        });
    }

    @Test
    void dateTest() {
        String todaysDateTime = DateTimeUtils.format(LocalDate.now());
        FLIGHT_SERVICE.findFlightsByDate(todaysDateTime).forEach(flightRef -> {
            assertEquals(DateTimeUtils.toDate(todaysDateTime), flightRef.getTakeOffDateTime().toLocalDate());
        });
    }

    @Test
    void findFlightsByAirportTest() {
        String departureInfo = SCHEDULED_ROUTE_SERVICE.findDailySchedule().get(0).getRoute().getDepartureAirport().getIcaoCode();
        String arrivalInfo = SCHEDULED_ROUTE_SERVICE.findDailySchedule().get(0).getRoute().getDepartureAirport().getIcaoCode();
        FLIGHT_SERVICE.findFlightsByAirport(departureInfo, true, AIRPORT_SERVICE).forEach(flight -> {
            assertEquals(flight.getRoute().getDepartureAirport().getIcaoCode(), departureInfo);
        });

        FLIGHT_SERVICE.findFlightsByAirport(arrivalInfo, false, AIRPORT_SERVICE).forEach(flightRef -> {
            assertEquals(flightRef.getRoute().getDestinationAirport().getIcaoCode(), arrivalInfo);
        });
    }

    @Test
    void flightByIdentifier() {
        if (FLIGHT_SERVICE.findAllFlights().isEmpty()) {
            return;
        }
        Flight flight = FLIGHT_SERVICE.findAllFlights().get(0);
        assertDoesNotThrow(() -> FLIGHT_SERVICE.findFlightsByIdentifier(flight.getFlightIdentifier()));
        assertEquals(flight.getFlightIdentifier(), (FLIGHT_SERVICE
                                                    .findFlightsByIdentifier(flight
                                                    .getFlightIdentifier())
                                                    .getFlightIdentifier()));
    }

    @Test
    void flightByCallSign() {
        if (FLIGHT_SERVICE.findAllFlights().isEmpty()) {
            return;
        }
        String callSign = SCHEDULED_ROUTE_SERVICE.findDailySchedule().get(0).getCallSign();
        assertDoesNotThrow(() -> FLIGHT_SERVICE.findFlightByCallSign(callSign, PLANE_SERVICE));
        FLIGHT_SERVICE.findFlightByCallSign(callSign, PLANE_SERVICE).forEach(flightRef -> {
            assertEquals(callSign, flightRef.getPlane().getCallSign());
        });
    }
}