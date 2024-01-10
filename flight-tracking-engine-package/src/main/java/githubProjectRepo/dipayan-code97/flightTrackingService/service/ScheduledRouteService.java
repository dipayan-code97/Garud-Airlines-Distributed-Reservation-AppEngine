package githubProjectRepo.dipayan-code97.flightTrackingService.service;

import githubProjectRepo.dipayan-code97.flightTrackingService.model.Plane;
import githubProjectRepo.dipayan-code97.flightTrackingService.model.ScheduledRoute;
import githubProjectRepo.dipayan-code97.flightTrackingService.repository.ScheduledRouteRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
@Service
public class ScheduledRouteService {

    private final ScheduledRouteRepository SCHEDULED_ROUTE_REPOSITORY;
    private final PlaneService PLANE_SERVICE;

    public ScheduledRouteService(ScheduledRouteRepository scheduledRouteRepository,
                                 PlaneService planeService) {
        this.SCHEDULED_ROUTE_REPOSITORY = scheduledRouteRepository;
        this.PLANE_SERVICE = planeService;
    }

    public Set<ScheduledRoute> findDailySchedule() {
        return SCHEDULED_ROUTE_REPOSITORY.findAll();
    }

    public void saveScheduledRoute(ScheduledRoute scheduledRoute) {
        SCHEDULED_ROUTE_REPOSITORY.save(scheduledRoute);
    }

    public void saveScheduledRoutes(Set<ScheduledRoute> allServiceScheduledRoutes) {
        SCHEDULED_ROUTE_REPOSITORY.saveAll(allServiceScheduledRoutes);
    }

    public Set<Plane> findAllActiveStationedServicePlanes() {
        Set<Plane> planes = new LinkedHashSet<>(SCHEDULED_ROUTE_REPOSITORY.findAll().stream().map(ScheduledRoute::getPlane).toSet());
        Set<Plane> availablePlanes = PLANE_SERVICE.findAllPlanes();
        availablePlanes.removeAll(planes);
        return availablePlanes;
    }

    public Long remainingRoutesToSchedule() {
        Set<ScheduledRoute> remainingRoutes = SCHEDULED_ROUTE_REPOSITORY.findAll();
        Long remainingRoutesNodeCount = 0;
        if (remainingRoutes.size() >= 15) {
            return 0;
        }
        remainingRoutesNodeCount = (15 - remainingRoutes.size());
        return remainingRoutesNodeCount;
    }
}
