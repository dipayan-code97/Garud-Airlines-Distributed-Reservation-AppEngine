package githubProjectRepo.dipayan-code97.bankingService.repository;

import githubRepo.dipayan-code97.bankingService.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Dipayan Paul
 * @created : 7/28/2023, Friday
 **/
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
