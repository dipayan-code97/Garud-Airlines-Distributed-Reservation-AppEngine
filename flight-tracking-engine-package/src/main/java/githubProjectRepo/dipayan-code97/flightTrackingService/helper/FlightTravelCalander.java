package githubProjectRepo.dipayan-code97.flightTrackingService.helper;

import githubProjectRepo.dipayan-code97.flightTrackingService.model.Flight;
import githubProjectRepo.dipayan-code97.flightTrackingService.model.Plane;
import githubProjectRepo.dipayan-code97.flightTrackingService.model.Route;
import githubProjectRepo.dipayan-code97.flightTrackingService.model.FlightRouteScheduler;
import githubProjectRepo.dipayan-code97.flightTrackingService.exception.FlightQueryException;
import githubProjectRepo.dipayan-code97.flightTrackingService.exception.RouteGeneratorException;
import githubProjectRepo.dipayan-code97.flightTrackingService.model.FlightRouteScheduler;
import githubProjectRepo.dipayan-code97.flightTrackingService.service.FlightService;
import githubProjectRepo.dipayan-code97.flightTrackingService.service.PlaneService;
import githubProjectRepo.dipayan-code97.flightTrackingService.service.ScheduledRouteService;
import githubProjectRepo.dipayan-code97.flightTrackingService.utils.DateTimeUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Component
public class FlightTravelCalander {

    private final RouteGenerator ROUTE_GENERATOR;
    private final PlaneService PLANE_SERVICE;
    private final FlightService FLIGHT_SERVICE;
    private final ScheduledRouteService SCHEDULED_ROUTE_SERVICE;

    public FlightTravelCalander(RouteGenerator routeGenerator,
                                PlaneService planeService,
                                FlightService flightService,
                                ScheduledRouteService scheduledRouteService) {
        this.ROUTE_GENERATOR = routeGenerator;
        this.PLANE_SERVICE = planeService;
        this.FLIGHT_SERVICE = flightService;
        this.SCHEDULED_ROUTE_SERVICE = scheduledRouteService;
    }

    public RouteGenerator getRouteGenerator() {
        return this.ROUTE_GENERATOR;
    }

    public PlaneService getPlaneService() {
        return this.PLANE_SERVICE;
    }

    public FlightService getFlightService() {
        return this.FLIGHT_SERVICE;
    }

    public ScheduledRouteService getSCHEDULED_ROUTE_SERVICE() {
        return this.SCHEDULED_ROUTE_SERVICE;
    }

    public boolean isDayMissing(LocalDate date) {
        Set<LocalDate> dates = FLIGHT_SERVICE.findAllFlights().stream()
                .map(flight -> flight.getTakeOffDateTime().toLocalDate())
                .collect(Collectors.toSet());
        return (!dates.contains(date));
    }

    public Set<LocalDate> missingDays(LocalDate startDate, LocalDate endDate) {
        Set<LocalDate> dates = FLIGHT_SERVICE.findAllFlights().stream()
                .map(flight -> flight.getTakeOffDateTime().toLocalDate())
                .collect(Collectors.toSet());
        Set<LocalDate> missingDays = new LinkedHashSet<>();
        for (LocalDate date : DateTimeUtils.allDatesInRange(startDate, endDate, false)) {
            if (!dates.contains(date)) missingDays.add(date);
        }
        return missingDays;
    }

    public FlightRouteScheduler createEveryDayTimeTable(LocalDate date, Set<ScheduledRoute> dailyRoutes) {
        Set<Flight> flightsToday = new LinkedHashSet<>(dailyRoutes.stream()
                .map(route -> new Flight(route, LocalDateTime.of(date, route.getTime())))
                .toSet());

        //add round trip for daily flights (fly back to departure airport after landing in destination)
        Set<Flight> sameRouteroundTrips = new LinkedHashSet<>();
        sameRouteroundTrips.add(flightsToday);
        sameRouteroundTrips.forEach(flight -> flightsToday.add(new Flight(
                flight.getPlane(),
                new Route(flight.getRoute().getDestinationAirport(),
                        flight.getRoute().getDepartureAirport(),
                        flight.getPlane()),
                flight.getLandingDateTime().plusMinutes(30)
        )));

        //for each plane not being used by a daily flight, schedule 1 random flight
        SCHEDULED_ROUTE_SERVICE.findAvailablePlanes().forEach(plane -> {
            try {
                Flight recentFlight = FLIGHT_SERVICE.findLastFlightByCallSign(plane.getCallSign());
                flightsToday.addAll(scheduleFlightsUntilEOD(plane, date, recentFlight));
            } catch (FlightQueryException ex) {
                flightsToday.add(new Flight(
                        plane, ROUTE_GENERATOR.fromUnitedStates(plane, 11), LocalDateTime.of(date, DateTimeUtils.createTimeOfFlight(12))));
            }
        });
        return new (FlightScheduler(date, flightsToday));
    }

    /*
    Starting at the last flights time of landing (+30min), schedule flights throughout the day with
    30min intervals between until the landing time crosses over into the next day. Departures are set by
    the planes current location
     */
    public Set<Flight> scheduleFlightsUntilEOD(Plane plane, LocalDate date, Flight recentFlight) {
        Set<Flight> newFlights = new LinkedHashSet<>();
        if (recentFlight.getLandingDateTime().toLocalDate().isAfter(date)) {
            return Set.of();
        }
        while (true) {
            Route route = ROUTE_GENERATOR.fromAirport(recentFlight.getRoute().getDestinationAirport(), plane, 11);
            LocalDateTime departureTime = LocalDateTime.of(date, recentFlight.getLandingDateTime().toLocalTime().plusMinutes(30));

            Flight newestFlight = new Flight(plane, route, departureTime);
            newFlights.add(newestFlight);
            //need to make sure this pus 30 is doing what I want it to (haven't tested yet)
            //intended so that the next take-off times in loop aren't a different day
            if (newestFlight.getLandingDateTime().plusMinutes(30).toLocalDate().isAfter(date)) {
                break;
            }
            recentFlight = newestFlight;
        }
        return newFlights;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        FlightTravelCalander that = (FlightTravelCalander) objectRef;
        return (Objects.equals(getRouteGenerator(), that.getRouteGenerator())
                && Objects.equals(getPlaneService(), that.getPlaneService())
                && Objects.equals(getFlightService(), that.getFlightService())
                && Objects.equals(getSCHEDULED_ROUTE_SERVICE(), that.getSCHEDULED_ROUTE_SERVICE()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getRouteGenerator(), getPlaneService(),
                getFlightService(), getSCHEDULED_ROUTE_SERVICE()));
    }

    @Override
    public String toString() {
        return ("FlightTravelCalander{" +
                "ROUTE_GENERATOR=" + ROUTE_GENERATOR +
                ", PLANE_SERVICE=" + PLANE_SERVICE +
                ", FLIGHT_SERVICE=" + FLIGHT_SERVICE +
                ", SCHEDULED_ROUTE_SERVICE=" + SCHEDULED_ROUTE_SERVICE +
                '}');
    }
}
