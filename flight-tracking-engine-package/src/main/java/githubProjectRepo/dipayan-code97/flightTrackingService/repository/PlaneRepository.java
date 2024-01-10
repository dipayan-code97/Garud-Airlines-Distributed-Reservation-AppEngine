package githubProjectRepo.dipayan-code97.flightTrackingService.repository;

import githubProjectRepo.dipayan-code97.flightTrackingService.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, String> {
}
