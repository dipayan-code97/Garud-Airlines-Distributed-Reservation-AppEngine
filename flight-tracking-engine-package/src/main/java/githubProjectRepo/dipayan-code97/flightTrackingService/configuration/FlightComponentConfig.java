package githubProjectRepo.dipayan-code97.flighttrackingservice.configuration;

import githubProjectRepo.dipayan-code97.flighttrackingservice.properties.AirportsList;
import githubProjectRepo.dipayan-code97.flighttrackingservice.properties.PlaneList;
import githubProjectRepo.dipayan-code97.flighttrackingservice.service.AirportService;
import githubProjectRepo.dipayan-code97.flighttrackingservice.service.PlaneService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(1)
public class FlightComponentConfig {

    private final PlaneService PLANE_SERVICE;
    private final AirportService AIRPORT_SERVICE;
    private static final Logger FLIGHT_COMPONENT_LOGGER = LoggerFactory.getLogger(FlightComponentConfig.class);

    public FlightComponentConfig(PlaneService PLANE_SERVICE,
                                 AirportService AIRPORT_SERVICE) {
        this.PLANE_SERVICE = PLANE_SERVICE;
        this.AIRPORT_SERVICE = AIRPORT_SERVICE;
    }

    public PlaneService getPLANE_SERVICE() {
        return this.PLANE_SERVICE;
    }

    public AirportService getAIRPORT_SERVICE() {
        return this.AIRPORT_SERVICE;
    }

    @PostConstruct
    public void configureFlightComponents(){
        if (PLANE_SERVICE.planeCount() != 30) {
            FLIGHT_COMPONENT_LOGGER.info("Saving Default Planes");
            PLANE_SERVICE.deleteAllPlanesAndFlights();
            PLANE_SERVICE.saveDefaultPlanes();
        } else if ((AirportsList.getDefaultAirports().size()) != (AIRPORT_SERVICE.airportCount())) {
            FLIGHT_COMPONENT_LOGGER.info("Saving Default Airports");
            AIRPORT_SERVICE.deleteAllAirportsAndFlights();
            AIRPORT_SERVICE.saveDefaultAirports();
        } else {
            System.out.println("HIGH ALERT....Invalid Configured Component!!!");
        }
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        FlightComponentConfig that = (FlightComponentConfig) objectRef;
        return (Objects.equals(PLANE_SERVICE, that.PLANE_SERVICE)
                && Objects.equals(AIRPORT_SERVICE, that.AIRPORT_SERVICE));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                PLANE_SERVICE,
                AIRPORT_SERVICE));
    }

    @Override
    public String toString() {
        return ("FlightComponentConfig{" +
                "PLANE_SERVICE=" + PLANE_SERVICE +
                ", AIRPORT_SERVICE=" + AIRPORT_SERVICE +
                '}');
    }
}
