package githubProjectRepo.dipayan-code97.flightTrackingService.repository;

import githubProjectRepo.dipayan-code97.flightTrackingService.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.Set;
import java.util.LinkedHashSet;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    final String FIND_CALL_SIGN = "SELECT * FROM flights f WHERE f.call_sign=:callSign";
    final String FIND_DEPARTURE = "SELECT * FROM flights f WHERE f.departure_icao=:code";
    final String FIND_DESTINATION = "SELECT * FROM flights f WHERE f.destination_icao=:code";
    final String FIND_DATES = "SELECT * FROM flights f WHERE DATE(f.scheduled_takeoff_time_and_date)=:date";

    @Query(value = FIND_CALL_SIGN, nativeQuery = true)
    Set<Flight> findFlightsByCallSign(String callSign);

    @Query(value = FIND_DEPARTURE, nativeQuery = true)
    Set<Flight> findFlightsByDeparture(String code);

    @Query(value = FIND_DESTINATION, nativeQuery = true)
    Set<Flight> findFlightsByDestination(String code);

    @Query(value = FIND_DATES, nativeQuery = true)
    Set<Flight> findFlightsByDate(Date date);
}
