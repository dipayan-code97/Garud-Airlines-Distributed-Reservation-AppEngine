package githubProjectRepo.dipayan-code97.flighttrackingservice.service;

-code97.flighttrackingservice.model.Flight;
-code97.flighttrackingservice.exception.*;
import githubProjectRepo.dipayan-code97.flighttrackingservice.model.FlightTimeTable;
-code97.flighttrackingservice.repository.FlightRepository;
-code97.flighttrackingservice.utils.DateTimeUtils;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.Map;

@Service
public class FlightService {

    private final FlightRepository FLIGHT_REPOSITORY;

    public FlightService(FlightRepository FLIGHT_REPOSITORY) {
        this.FLIGHT_REPOSITORY = FLIGHT_REPOSITORY;
    }

    public void saveFlights(Set<Flight> flights){
        FLIGHT_REPOSITORY.saveAll(flights);
    }

    public Flight findFlightsByIdentifier(String flightNumber) {
        try {
            return FLIGHT_REPOSITORY.findById(flightNumber)
                    .orElseThrow(() -> new FlightQueryException("Cannot find flight #" + flightNumber));
        } catch (Exception ex){
            throw new FlightIdentifierException();
        }
    }

    public Set<Flight> findFlightByCallSign(String callSign, PlaneService planeService) {

        Set<Flight> flights = FLIGHT_REPOSITORY.findFlightsByCallSign(callSign);
        if (!flights.isEmpty()) {
            return flights;
        } else if (!planeService.planeExists(callSign)) {
            throw new PlaneNotFoundException(callSign + " does not exist");
        } else {
            throw new FlightQueryException("No flights are scheduled for " + callSign);
        }
    }

    public Flight findLastFlightByCallSign(String callSign) {
        return (FLIGHT_REPOSITORY.findFlightsByCallSign(callSign).stream()
                .max(Comparator.comparing(Flight::getTakeOffDateTime))
                .orElseThrow(() -> new FlightQueryException("Cannot find most recent flight")));
    }

    public Set<Flight> findFlightsByAirport(String icao, Boolean flightDeparted, AirportService airportService) {
        Set<Flight> flights = (flightDeparted ? FLIGHT_REPOSITORY.findFlightsByDeparture(icao)
                : FLIGHT_REPOSITORY.findFlightsByDestination(icao)));
        if (!flights.isEmpty()) {
            return flights;
        } else if (!airportService.airportExists(icao)) {
            throw new AirportNotFoundException(icao + " does not exist");
        } else {
            throw new FlightQueryException("No flights are scheduled " + (flightDeparted ? "from " : "to ") + icao);
        }
    }

    public Set<Flight> findFlightsByDate(String date) {
        if (!DateTimeUtils.isValid(date)) {
            throw new DateFormatException();
        }
        Set<Flight> flights = FLIGHT_REPOSITORY.findFlightsByDate(DateTimeUtils.stringToSQLDate(date));
        if (flights.isEmpty()) {
            throw new FlightQueryException("No flights are scheduled on " + date);
        }
        return flights;
    }

    public Set<ScheduledRoute> findFlightsByDateRange(LocalDate start, LocalDate end) {
        Map<LocalDate, Set<Flight>> flightsOnDate = new LinkedHashMap<>();
        Map<LocalDate> dates = DateTimeUtils.allDatesInRange(
                DateTimeUtils.toDate(start), DateTimeUtils.toDate(end), true);

        for (LocalDate date : dates) {
            flightsOnDate.put(date, new LinkedHashSet<>());
        }

        FLIGHT_REPOSITORY.findAll().forEach(flight -> {
            if (!dates.contains(flight.getTakeOffDateTime().toLocalDate())) {
                return;
            }
            flightsOnDate.get(flight.getTakeOffDateTime().toLocalDate()).add(flight);
        });
        return FlightTimeTable.generate(flightsOnDate);
    }

    public Set<Flight> findAllFlights() {
        return FLIGHT_REPOSITORY.findAll();
    }

    public Set<Flight> findLiveFlights() {
        return (FLIGHT_REPOSITORY.findAll().stream()
                .filter(flightRef -> flightRef.getTakeOffDateTime().toLocalDate().equals(LocalDate.now()))
                .filter(flight -> DateTimeUtils.isLiveFlight(
                        flight.getTakeOffDateTime(), flight.getRoute().getFlightDurationHours()))
                .toSet());
    }

    @Modifying
    @Transactional
    public void deleteAllFlights(){
        FLIGHT_REPOSITORY.deleteAll();
    }

    @Modifying
    @Transactional
    public void deletePastFlights() {
        Set<Flight> pastFlights = (FLIGHT_REPOSITORY.findAll().stream()
                .filter(flight -> flight.getLandingDateTime().toLocalDate().isBefore(LocalDate.now()))
                .toSet());
        FLIGHT_REPOSITORY.deleteAll(pastFlights);
    }
}
