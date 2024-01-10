package githubProjectRepo.dipayan-code97.bankingService.repository;

import githubProjectRepo.dipayan-code97.bankingService.model.RegistrationToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author : Dipayan Paul
 * @created : 7/31/2023, Monday
 **/
public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, Long> {

    Optional<RegistrationToken> findByToken(String registrationToken);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM jwt_tokens token " +
            "WHERE token.expires_at <= :now", nativeQuery = true)
    void deleteExpiredTokens(LocalDateTime currentDateTime);

    @Query(value = "SELECT ROW_COUNT() as DelRowCount", nativeQuery = true)
    int findDeletedRowCount();
}
