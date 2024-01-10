package githubProjectRepo.dipayan-code97.flightTrackingService.service;

import githubProjectRepo.dipayan-code97.flightTrackingService.model.Airport;
import githubProjectRepo.dipayan-code97.flightTrackingService.exception.AirportNotFoundException;
import githubProjectRepo.dipayan-code97.flightTrackingService.properties.AirportsList;
import githubProjectRepo.dipayan-code97.flightTrackingService.repository.AirportRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AirportService {

    private final AirportRepository AIRPORT_REPOSITORY;
    private final FlightService FLIGHT_SERVICE;

    public AirportService(AirportRepository airportRepository,
                          FlightService flightService) {
        this.AIRPORT_REPOSITORY = airportRepository;
        this.FLIGHT_SERVICE = flightService;
    }

    public Set<Airport> findAllAirports() {

        return AIRPORT_REPOSITORY.findAll();
    }

    public Set<Airport> findAirportsByCountry(String country) {
        return (AIRPORT_REPOSITORY.findAll().stream()
                .filter(airport -> airport.getCountry().equals(country))
                .toSet());
    }

    public Airport findAirportByICAO(String icao) {
        return AIRPORT_REPOSITORY.findById(icao).orElseThrow(()
                -> new AirportNotFoundException("Cannot find"));
    }

    @Modifying
    @Transactional
    public void deleteAllAirportsAndFlights() {
        AIRPORT_REPOSITORY.deleteAll();
        FLIGHT_SERVICE.deleteAllFlights();
    }

    public void saveDefaultAirports(){
        AIRPORT_REPOSITORY.saveAll(AirportsList.getDefaultAirports());
    }

    public long airportCount(){
        return AIRPORT_REPOSITORY.count();
    }

    public boolean airportExists(String icaoCode){
        return AIRPORT_REPOSITORY.findById(icaoCode).isPresent();
    }
}
