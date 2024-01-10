package githubProjectRepo.dipayan-code97.flightTrackingService.service;

import githubProjectRepo.dipayan-code97.flightTrackingService.model.Plane;
import githubProjectRepo.dipayan-code97.flightTrackingService.properties.PlaneList;
import githubProjectRepo.dipayan-code97.flightTrackingService.repository.PlaneRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PlaneService {

    private final PlaneRepository PLANE_REPOSITORY;
    private final FlightService FLIGHT_SERVICE;

    public PlaneService(PlaneRepository PLANE_REPOSITORY,
                        FlightService FLIGHT_SERVICE) {
        this.PLANE_REPOSITORY = PLANE_REPOSITORY;
        this.FLIGHT_SERVICE = FLIGHT_SERVICE;
    }

    public PlaneRepository getPlaneRepository() {
        return this.PLANE_REPOSITORY;
    }

    public FlightService getFlightService() {
        return this.FLIGHT_SERVICE;
    }

    public Set<Plane> findAllServicePlanes() {
        return PLANE_REPOSITORY.findAll();
    }

    public Boolean planeExists(String callSign) {
        return PLANE_REPOSITORY.findById(callSign).isPresent();
    }

    @Modifying
    @Transactional
    public void deleteAllServicePlanesAndFlights() {
        PLANE_REPOSITORY.deleteAll();
        FLIGHT_SERVICE.deleteAllFlights();
    }

    public void saveDefaultPlanes() {
        PLANE_REPOSITORY.saveAll(PlaneList.getDefaultPlanes());
    }

    public Long planeCount(){
        return PLANE_REPOSITORY.count();
    }

    public Set<Plane> findUnstationedPlanes(Set<Plane> stationedPlanes) {
        Set<Plane> unstationed = PLANE_REPOSITORY.findAll();
        unstationed.removeAll(stationedPlanes);
        return unstationed;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        PlaneService that = (PlaneService) objectRef;
        return (Objects.equals(getPlaneRepository(), that.getPlaneRepository())
                && Objects.equals(getFlightService(), that.getFlightService()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getPlaneRepository(), getFlightService()));
    }

    @Override
    public String toString() {
        return ("PlaneService{" +
                "PLANE_REPOSITORY=" + PLANE_REPOSITORY +
                ", FLIGHT_SERVICE=" + FLIGHT_SERVICE +
                '}');
    }
}
