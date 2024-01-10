package githubProjectRepo.dipayan-code97.flightTrackingService.repository;

import githubProjectRepo.dipayan-code97.flightTrackingService.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, String> {
}
