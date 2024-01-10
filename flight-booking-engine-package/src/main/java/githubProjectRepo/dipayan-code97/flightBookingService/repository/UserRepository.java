package githubProjectRepo.dipayan-code97.flightBookingService.repository;

import githubProjectRepo.dipayan-code97.flightBookingService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Dipayan Paul
 * @created : 8/19/2023, Saturday
 **/
public interface UserRepository extends JpaRepository<User, String> {
}
