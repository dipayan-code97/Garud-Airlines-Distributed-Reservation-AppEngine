package githubProjectRepo.dipayan-code97.flightTrackingService.controller;

import githubProjectRepo.dipayan-code97.flightTrackingService.dto.FlightDTO;
import githubProjectRepo.dipayan-code97.flightTrackingService.dto.FlightSummaryDTO;
import githubProjectRepo.dipayan-code97.flightTrackingService.dto.FlightScheduleDTO;
import githubProjectRepo.dipayan-code97.flightTrackingService.dto.FlightScheduleDTO;
import githubProjectRepo.dipayan-code97.flightTrackingService.model.Airport;
import githubProjectRepo.dipayan-code97.flightTrackingService.planning_algorithm.Route;
import githubProjectRepo.dipayan-code97.flightTrackingService.planning_algorithm.algorithmDTO.FlightRouteDTO;
import githubProjectRepo.dipayan-code97.flightTrackingService.planning_algorithm.algorithmDTO.FlightRouteDTO;
import githubProjectRepo.dipayan-code97.flightTrackingService.planning_algorithm.PathGenerator;
import githubProjectRepo.dipayan-code97.flightTrackingService.service.AirportService;
import githubProjectRepo.dipayan-code97.flightTrackingService.service.FlightService;
import githubProjectRepo.dipayan-code97.flightTrackingService.service.PlaneService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.LinkedHashSet;

@RestController
@RequestMapping(value = "/api/v1/tracking")

public class FlightController {

    private final FlightService FLIGHT_SERVICE;
    private final PlaneService PLANE_SERVICE;
    private final AirportService AIRPORT_SERVICE;
    private final PathGenerator PATH_GENERATOR;

    public FlightController(FlightService flightService,
                            PlaneService planeService,
                            AirportService airportService,
                            PathGenerator pathGenerator) {
        this.FLIGHT_SERVICE = flightService;
        this.PLANE_SERVICE = planeService;
        this.AIRPORT_SERVICE = airportService;
        this.PATH_GENERATOR = pathGenerator;
    }

    public FlightService getFlightService() {
        return this.FLIGHT_SERVICE;
    }

    public PlaneService getPlaneService() {
        return this.PLANE_SERVICE;
    }

    public AirportService getAirportService() {
        return this.AIRPORT_SERVICE;
    }

    public PathGenerator getPathGenerator() {
        return this.PATH_GENERATOR;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> returnAllStationedFlights(
            @RequestParam(value = "departure", defaultValue = "") String departure,
            @RequestParam(value = "destination", defaultValue = "") String destination){
        if (!departure.equals("")) {
            return (ResponseEntity.ok(FLIGHT_SERVICE.findFlightsByAirport(
                    departure, true, AIRPORT_SERVICE)
                        .stream().map(FlightSummaryDTO::new).toList()));
        } else if (!destination.equals("")) {
            return (ResponseEntity.ok(FLIGHT_SERVICE.findFlightsByAirport(
                    destination, false, AIRPORT_SERVICE)
                        .stream().map(FlightSummaryDTO::new).toList()));
        } else {
            return (ResponseEntity.ok(FLIGHT_SERVICE
                    .findAllFlights().stream()
                    .map(FlightSummaryDTO::new).toList()));
        }
    }

    @GetMapping(value = "/identifier/{identifier}")
    public ResponseEntity<?> returnAllStationedFlights(@PathVariable(value = "identifier") long identifier){
        return (ResponseEntity.ok(new FlightDTO(FLIGHT_SERVICE.findFlightsByIdentifier(identifier))));
    }

    @GetMapping(value = "/live_flights")
    public ResponseEntity<?> returnAllActiveFlights() {
        return (ResponseEntity.ok(FLIGHT_SERVICE.findLiveFlights().stream()
                .map(FlightDTO::new).toList()));
    }

    /*
    200 code for flights found
    204 code for plane exists but no flights scheduled
    404 for plane that doesn't exist
     */
    @GetMapping(value = "/call_sign/{callSign}")
    public ResponseEntity<?> returnFlightByCallSign(@PathVariable("callSign") String planeCallSign){
        return (ResponseEntity.ok(FLIGHT_SERVICE.findFlightByCallSign(planeCallSign, PLANE_SERVICE).stream()
                .map(FlightDTO::new).toList()));
    }

    @GetMapping(value = "/time_table")
    public ResponseEntity<?> returnFlightsOnDate(@RequestParam("date") Date date){
        return (ResponseEntity.ok().body(FLIGHT_SERVICE.findFlightsByDate(date).stream()
                .map(FlightSummaryDTO::new).toList()));
    }

    @GetMapping(value = "/time_table/range")
    public ResponseEntity<?> returnFlightsOnDate(@RequestParam("start") Date start, @RequestParam("end") Date end){
        return (ResponseEntity.ok().body(FLIGHT_SERVICE.findFlightsByDateRange(start, end).stream()
                        .map(TimeTableSummaryDTO::new).toList()));
    }

    /*
    TODO:
     add URL param optimizer to find SPF based on total time past,
     least # of connecting flights, or lowest distance traveled

     default optimizer is quickest flight time (takeoff at departure to landing at destination)
    */
    @RequestMapping(value = "path_generator")
    public Set<FlightSummaryDTO> generateConnectingFlightRoute(@RequestParam("departure") String departurePlatform,
                                                                @RequestParam("destination") String destinationPlatform) {
        Airport departureAirportPlatform = AIRPORT_SERVICE.findAirportByICAO(departurePlatform);
        Airport destinationAirportPlatform = AIRPORT_SERVICE.findAirportByICAO(destinationPlatform);
        return (PATH_GENERATOR.uniquePath(departurePlatform, destinationPlatform).stream()
                .map(Edge::getFlight).map(FlightSummaryDTO::new).toSet());
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        FlightController that = (FlightController) objectRef;
        return (Objects.equals(getFlightService(), that.getFlightService())
                && Objects.equals(getPlaneService(), that.getPlaneService())
                && Objects.equals(getAirportService(), that.getAirportService())
                && Objects.equals(getPathGenerator(), that.getPathGenerator()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getFlightService(),
                getPlaneService(),
                getAirportService(),
                getPathGenerator()));
    }

    @Override
    public String toString() {
        return ("FlightController{" +
                "FlightService=" + FLIGHT_SERVICE +
                ", PlaneService=" + PLANE_SERVICE +
                ", AirportService=" + AIRPORT_SERVICE +
                ", PathGenerator=" + PATH_GENERATOR +
                '}');
    }
}
