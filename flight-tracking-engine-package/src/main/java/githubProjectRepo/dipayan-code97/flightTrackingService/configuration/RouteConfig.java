package githubProjectRepo.dipayan-code97.flighttrackingservice.configuration;

import githubProjectRepo.dipayan-code97.flighttrackingservice.model.Plane;
import githubProjectRepo.dipayan-code97.flighttrackingservice.model.Route;
import githubProjectRepo.dipayan-code97.flighttrackingservice.model.ScheduledRoute;
import githubProjectRepo.dipayan-code97.flighttrackingservice.helper.FlightCalendarCreator;
import githubProjectRepo.dipayan-code97.flighttrackingservice.helper.RouteGenerator;
import githubProjectRepo.dipayan-code97.flighttrackingservice.service.FlightService;
import githubProjectRepo.dipayan-code97.flighttrackingservice.service.ScheduledRouteService;
import githubProjectRepo.dipayan-code97.flighttrackingservice.utils.DateTimeUtils;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Random;

@Configuration
@Order(2)
public class RouteConfig {

    private final ScheduledRouteService SCHEDULED_ROUTE_SERVICE;
    private final RouteGenerator ROUTE_CONFIG_GENERATOR;
    private final FlightCalendarCreator FLIGHT_CALENDAR_CREATOR;
    private final FlightService FLIGHT_SERVICE;
    private static final Random RANDOM_FLIGHT_ROUTE_CONFIG = new Random();
    private static final Logger ROUTE_CONFIG_LOGGER = LoggerFactory.getLogger(RouteConfig.class);

    public RouteConfig(ScheduledRouteService SCHEDULED_ROUTE_SERVICE,
                       RouteGenerator ROUTE_CONFIG_GENERATOR,
                       FlightCalendarCreator FLIGHT_CALENDAR_CREATOR,
                       FlightService FLIGHT_SERVICE) {
        this.SCHEDULED_ROUTE_SERVICE = SCHEDULED_ROUTE_SERVICE;
        this.ROUTE_CONFIG_GENERATOR = ROUTE_CONFIG_GENERATOR;
        this.FLIGHT_CALENDAR_CREATOR = FLIGHT_CALENDAR_CREATOR;
        this.FLIGHT_SERVICE = FLIGHT_SERVICE;
    }

    public ScheduledRouteService getSCHEDULED_ROUTE_SERVICE() {
        return this.SCHEDULED_ROUTE_SERVICE;
    }

    public RouteGenerator getROUTE_CONFIG_GENERATOR() {
        return this.ROUTE_CONFIG_GENERATOR;
    }

    public FlightCalendarCreator getFLIGHT_CALENDAR_CREATOR() {
        return this.FLIGHT_CALENDAR_CREATOR;
    }

    public FlightService getFLIGHT_SERVICE() {
        return this.FLIGHT_SERVICE;
    }


    /*
            This method will ensure that 15 daily routes are saved to the database. Alongside these daily flights,
            the remaining planes will fly 1-2 random routes per day. This method will also ensure one week of flights
            are saved upon startup, and allow for the scheduler (FlightSchedulerService.java)
            to work into the future instead of needing to catch up on missed days caused by downtime
            discrepancies (flights are created at midnight with the scheduler,
                and therefore it is open to discrepancies if the application is not running at midnight)
             */
    @PostConstruct
    public void configureScheduledRoutes() {
        //configure 15 scheduledRoute to fly the same time every day, based on plane callSign
        int remainingFlights = (int) SCHEDULED_ROUTE_SERVICE.remainingRoutesToSchedule();
        if(remainingFlights > 0){
            SCHEDULED_ROUTE_SERVICE.saveScheduledRoutes(createScheduledRoutes(remainingFlights));
        }
        fillMissingDaysInCalendar();
        FLIGHT_SERVICE.deletePastFlights();
    }

    public Set<ScheduledRoute> createScheduledRoutes(int routesToCreate){
        Set<Plane> availablePlanes = SCHEDULED_ROUTE_SERVICE.findAvailablePlanes();
        Set<ScheduledRoute> scheduledRoutes = new LinkedHashSet<>();
        for (int route = 1; route < routesToCreate; route++){
            if (availablePlanes.size() == 0) break;
            Plane plane = availablePlanes.get(RANDOM_FLIGHT_ROUTE_CONFIG.nextInt(availablePlanes.size()));
            Route route = ROUTE_CONFIG_GENERATOR.fromUnitedStates(plane, 11);
            scheduledRoutes.add(new ScheduledRoute(plane, route, DateTimeUtils.createTimeOfFlight(11, true)));
            availablePlanes.remove(plane);
        }
        return scheduledRoutes;
    }

    public void fillMissingDaysInCalendar() {
        Set<LocalDate> missingDates = FLIGHT_CALENDAR_CREATOR.missingDays(LocalDate.now(), LocalDate.now().plusDays(7));
        if (!missingDates.isEmpty()){
            ROUTE_CONFIG_LOGGER.info(missingDates.size() + " missing dates: scheduling time tables");
            Set<ScheduledRoute> scheduledRoutes = SCHEDULED_ROUTE_SERVICE.findDailySchedule();
            for (LocalDate missingDate : missingDates) {
                FLIGHT_SERVICE.saveFlights(
                        FLIGHT_CALENDAR_CREATOR.createDaysTimeTable(missingDate, scheduledRoutes)
                            .getFlightsToday());
            }
        }
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null || getClass()) != (objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        RouteConfig that = (RouteConfig) objectRef;
        return (Objects.equals(getSCHEDULED_ROUTE_SERVICE(), that.getSCHEDULED_ROUTE_SERVICE())
                && Objects.equals(getROUTE_CONFIG_GENERATOR(), that.getROUTE_CONFIG_GENERATOR())
                && Objects.equals(getFLIGHT_CALENDAR_CREATOR(), that.getFLIGHT_CALENDAR_CREATOR())
                && Objects.equals(getFLIGHT_SERVICE(), that.getFLIGHT_SERVICE()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getSCHEDULED_ROUTE_SERVICE(),
                getROUTE_CONFIG_GENERATOR(),
                getFLIGHT_CALENDAR_CREATOR(),
                getFLIGHT_SERVICE()));
    }

    @Override
    public String toString() {
        return ("RouteConfig{" +
                "ScheduledRouteService=" + SCHEDULED_ROUTE_SERVICE +
                ", RouteGenerator=" + ROUTE_CONFIG_GENERATOR +
                ", CalendarCreator=" + FLIGHT_CALENDAR_CREATOR +
                ", FlightService=" + FLIGHT_SERVICE +
                '}');
    }
}
