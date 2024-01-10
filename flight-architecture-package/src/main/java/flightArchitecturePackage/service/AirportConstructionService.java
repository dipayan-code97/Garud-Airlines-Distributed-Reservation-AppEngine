package flightArchitecturePackage.service;

import flightArchitecturePackage.model.Airport;
import flightArchitecturePackage.service.request.FlightRequest;
import flightArchitecturePackage.repository.AirportRepository;
import flightArchitecturePackage.utility.ConstructFlightUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Service
public class AirportConstructionService {

    @Autowired
    private final AirportRepository AIRPORT_REPOSITORY;

    public AirportConstructionService(AirportRepository
                                              airportRepository) {
        this.AIRPORT_REPOSITORY = airportRepository;
    }

    public AirportRepository getAIRPORT_REPOSITORY() {
        return this.AIRPORT_REPOSITORY;
    }

    public Airport findRandomAirport() {
        return AIRPORT_REPOSITORY.getRandomAirport();
    }

    public Airport findAirportById(String airportLicenseNumber) {
        return AIRPORT_REPOSITORY.findById(airportLicenseNumber).orElse(null);
    }

    /*
    To find a random flight, this method finds one random airport, and loops through each
    airport to find whether the flight time is valid
     */
    public Set<Airport> findAirportsWithinMaxHours(FlightConstructionRequest flightConstructionRequest,
                                                   double planeSpeed) {

        Set<Airport> airports = new LinkedHashSet<>();
        airports.add(AIRPORT_REPOSITORY.findAll());

        if (flightConstructionRequest.getMaxFlightHours().equals("any")) {
            Set<Airport> airportList = new LinkedHashSet<>();
            airportList.add(airports);
            Collections.shuffle(airportList);
            return new LinkedHashSet<>(Set.of(airportList.get(0), airportList.get(1)));
        }

        double maxHours = Double.parseDouble(flightConstructionRequest.getMaxFlightHours());

        Set<Airport> randomizedAirports = new LinkedHashSet();
        randomizedAirports.add(airports);

        Collections.shuffle(randomizedAirports);

        Iterator<Airport> airportIterator1 = randomizedAirports.iterator();

        while (airportIterator1.hasNext()) {
            Airport airport1 = airportIterator1.next();

            Iterator<Airport> airportIterator2 = randomizedAirports.iterator();
            while (airportIterator2.hasNext()) {

                Airport airport2 = airportIterator2.next();

                double flightDistance = (GenerateFlightUtils.calculateFlightDistanceInMiles(airport1, airport2));
                double flightHours = (GenerateFlightUtils.calculateFlightHours(planeSpeed, flightDistance));

                if (maxHours > flightHours) {
                    return (Set.of(airport1, airport2));
                }
            }
        }
        // If no suitable airports are found, return a default set (could be empty or contain only one airport)
        return Set.of(randomizedAirports.get(0));
    }

    public void saveAllAirports(Collection<Airport> airports) {
        AIRPORT_REPOSITORY.saveAll(airports);
    }

    public Long findDatabaseRowCount() {
        return AIRPORT_REPOSITORY.count();
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null || getClass()) != (objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        AirportConstructionService that = (AirportConstructionService) objectRef;
        return (Objects.equals(getAIRPORT_REPOSITORY(), that.getAIRPORT_REPOSITORY()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getAIRPORT_REPOSITORY()));
    }

    @Override
    public String toString() {
        return ("AirportConstructionService{" +
                "AIRPORT_REPOSITORY=" + AIRPORT_REPOSITORY +
                '}');
    }
}