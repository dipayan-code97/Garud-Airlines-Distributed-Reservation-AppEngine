package githubProjectRepo.dipayan-code97.flightTrackingService.scheduler;

import githubProjectRepo.dipayan-code97.flightTrackingService.helper.FlightCalendarCreator;
import githubProjectRepo.dipayan-code97.flightTrackingService.properties.SchedulingProperties;
import githubProjectRepo.dipayan-code97.flightTrackingService.service.FlightService;
import githubProjectRepo.dipayan-code97.flightTrackingService.service.ScheduledRouteService;
import githubProjectRepo.dipayan-code97.flightTrackingService.utils.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@EnableScheduling
@Service
public class FlightSchedulerService {

    private final FlightCalendarCreator FLIGHT_CALANDER_CREATOR;
    private final FlightService FLIGHT_SERVICE;
    private final ScheduledRouteService SCHEDULED_ROUTE_SERVICE;
    private static final Logger FLIGHT_SCHEDULER_LOGGER = LoggerFactory.getLogger(FlightScheduler.class);

    public FlightSchedulerService(FlightCalendarCreator flightCalendarCreator,
                                  FlightService flightService,
                                  ScheduledRouteService scheduledRouteService) {
        this.FLIGHT_CALANDER_CREATOR = flightCalendarCreator;
        this.FLIGHT_SERVICE = flightService;
        this.SCHEDULED_ROUTE_SERVICE = scheduledRouteService;
    }

    public FlightCalendarCreator getflightCalanderCreator() {
        return this.FLIGHT_CALANDER_CREATOR;
    }

    public FlightService getFlightService() {
        return this.FLIGHT_SERVICE;
    }

    public ScheduledRouteService getScheduledRouteService() {
        return this.SCHEDULED_ROUTE_SERVICE;
    }

    /*
            RouteConfiguration.java is called on app startup to ensure flights are scheduled 1 week out,
            meaning that even if the app is not running when @Scheduled is expected to run, the airline
            calendar will always be filled with one week of flights
             */
    @Scheduled(cron = SchedulingProperties.runAtMidnight)
    public void scheduleFlightsOneWeekOut() {
        LocalDate oneWeek = LocalDate.now().plusDays(7);
        if (FLIGHT_CALANDER_CREATOR.isDayMissing(oneWeek)) {
            FLIGHT_SCHEDULER_LOGGER.info("Generating time table for " + DateTimeUtils.format(oneWeek));
            FLIGHT_SERVICE.saveFlights(FLIGHT_CALANDER_CREATOR.createDaysTimeTable(
                    oneWeek, SCHEDULED_ROUTE_SERVICE.findDailySchedule()).getFlightsToday());
        }
        FLIGHT_SERVICE.deletePastFlights();
    }
    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        FlightSchedulerService that = (FlightSchedulerService) objectRef;
        return (Objects.equals(getflightCalanderCreator(), that.getflightCalanderCreator())
                && Objects.equals(getFlightService(), that.getFlightService())
                && Objects.equals(getScheduledRouteService(), that.getScheduledRouteService()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getflightCalanderCreator(),
                getFlightService(), getScheduledRouteService()));
    }

    @Override
    public String toString() {
        return ("FlightSchedulerService{" +
                "FLIGHT_CALANDER_CREATOR=" + FLIGHT_CALANDER_CREATOR +
                ", FLIGHT_SERVICE=" + FLIGHT_SERVICE +
                ", SCHEDULED_ROUTE_SERVICE=" + SCHEDULED_ROUTE_SERVICE +
                '}');
    }
}
