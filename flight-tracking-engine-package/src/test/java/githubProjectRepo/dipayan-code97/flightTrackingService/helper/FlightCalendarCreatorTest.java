package githubProjectRepo.dipayan-code97.flighttrackingservice.helper;

import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.Flight;
import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.Plane;
import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.ScheduledRoute;
import githubProjectRepo.dipayan-code97.flighttrackingservice.model.FlightTimeTable;
import githubProjectRepo.dipayan-code97.flighttrackingservice.service.FlightService;
import githubProjectRepo.dipayan-code97.flighttrackingservice.service.PlaneService;
import githubProjectRepo.dipayan-code97.flighttrackingservice.service.ScheduledRouteService;
import githubProjectRepo.dipayan-code97.flighttrackingservice.utils.DateTimeUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FlightCalendarCreatorTest {

    private final FlightCalendarCreator FLIGHT_CALENDAR_CREATOR;
    private final PlaneService PLANE_SERVICE;
    private final ScheduledRouteService SCHEDULED_ROUTE_SERVICE;
    private final FlightService FLIGHT_SERVICE;

    @Autowired
    public FlightCalendarCreatorTest(FlightCalendarCreator flightCalendarCreator,
                                     PlaneService planeService,
                                     ScheduledRouteService routeService,
                                     FlightService flightService) {
        this.FLIGHT_CALENDAR_CREATOR = flightCalendarCreator;
        this.PLANE_SERVICE = planeService;
        this.SCHEDULED_ROUTE_SERVICE = routeService;
        this.FLIGHT_SERVICE = flightService;
    }

    @Test
    void dateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        LocalDateTime localDateTime = LocalDate.of(2023, 7, 4).atTime(9, 0);
        assertEquals("07/04/2023 09:00", localDateTime.format(dateTimeFormatter));
    }

    @Test
    void flightsTimeTableUsesWholeFleet() {
        LocalDate todaysLocalDate = FLIGHT_SERVICE.findAllFlights().stream()
                .max(Comparator.comparing(Flight::getTakeOffDateTime))
                .orElse(new Flight()).getTakeOffDateTime().toLocalDate().plusDays(1);
        FlightTimeTable timeTable = FLIGHT_CALENDAR_CREATOR.createDaysTimeTable(
                todaysLocalDate, SCHEDULED_ROUTE_SERVICE.findDailySchedule());
        assertEquals(
                PLANE_SERVICE.planeCount(),
                //filter flights today to gather unique planes
                timeTable.getFlightsToday().stream()
                        .map(Flight::getPlane).collect(Collectors.toSet()).size()
        );
        datesMatch(timeTable.getFlightsToday(), todaysLocalDate);
    }

    void datesMatch(Set<Flight> flights, LocalDate today) {
        flights.forEach(allFlight -> assertFalse(allFlight.getTakeOffDateTime().toLocalDate().isAfter(today)));
    }

    @Test
    void thisTest(){
        test(FLIGHT_SERVICE.findAllFlights());
    }

    void test(Set<Flight> flight) {
        Map<String, Integer> flightTestRepo = new LinkedHashMap<>();
        flight.forEach(allFlight -> {
            String callSign = allFlight.getPlane().getCallSign();
            if (flightTestRepo.containsKey(callSign)) {
                flightTestRepo.put(callSign, (flightTestRepo.get(callSign) + 1));
            } else {
                flightTestRepo.put(callSign, 1);
            }
        });

        Map<Set<AirportNode>, Set<AirportNode>> alreadyUsedRoute = (SCHEDULED_ROUTE_SERVICE.findDailySchedule()
                                        .stream().map(route -> route.getPlane()
                                        .getCallSign()).collect(Collectors.toMap()));
        Map<String, Integer> unscheduled = new LinkedHashMap<>();
        Map<String, Integer> scheduled = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> schedulerEntry : flightTestRepo.entrySet()) {
            if (alreadyUsedRoute.contains(schedulerEntry.getKey())) {
                scheduled.put(schedulerEntry.getKey(), schedulerEntry.getValue());
            } else {
                unscheduled.put(schedulerEntry.getKey(), schedulerEntry.getValue());
            }
        }
        System.out.println("unscheduled flight routes :- " + unscheduled);
        System.out.println();
        System.out.println("scheduled flight routes :- " + scheduled);
    }
}
