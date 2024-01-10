package githubRepo.dipayan-code97.bankingservice.service;

import githubRepo.dipayan-code97.bankingservice.model.BankAccount;
import githubRepo.dipayan-code97.bankingservice.exception.RegistrationException;
import githubRepo.dipayan-code97.bankingservice.repository.BankAccountRepository;
import githubRepo.dipayan-code97.bankingservice.request.LoginRequest;
import githubRepo.dipayan-code97.bankingservice.request.RegistrationRequest;
import githubRepo.dipayan-code97.bankingservice.utils.PasswordValidator;
import org.springframework.stereotype.Service;

/**
 * @author : Dipayan_Paul
 * @created : 7/31/2023, Monday
 **/
@Service
public class RegistrationService {

    private final BankAccountService BANK_ACCOUNT_SERVICE;

    public RegistrationService(BankAccountService bankAccountService) {
        this.BANK_ACCOUNT_SERVICE = bankAccountService;
    }

    public String register(RegistrationRequest registrationRequest) {
        return BANK_ACCOUNT_SERVICE.saveNewAccount(registrationRequest);
    }

    public BankAccountService getBANK_ACCOUNT_SERVICE() {
        return this.BANK_ACCOUNT_SERVICE;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        RegistrationService that = (RegistrationService) object;
        return (Objects.equals(getBANK_ACCOUNT_SERVICE(), that.getBANK_ACCOUNT_SERVICE()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getBANK_ACCOUNT_SERVICE()));
    }

    @Override
    public String toString() {
        return ("RegistrationService{" +
                "BankAccountService=" + BANK_ACCOUNT_SERVICE +
                '}');
    }
}
