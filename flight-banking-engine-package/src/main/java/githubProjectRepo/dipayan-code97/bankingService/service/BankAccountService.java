package githubProjectRepo.dipayan-code97.bankingService.service;

import githubProjectRepo.dipayan-code97.bankingservice.model.BankAccount;
import githubProjectRepo.dipayan-code97.bankingService.model.RegistrationToken;
import githubProjectRepo.dipayan-code97.bankingService.exception.BankAccountException;
import githubProjectRepo.dipayan-code97.bankingService.exception.RegistrationException;
import githubProjectRepo.dipayan-code97.bankingService.exception.TransactionException;
import githubProjectRepo.dipayan-code97.bankingService.repository.BankAccountRepository;
import githubProjectRepo.dipayan-code97.bankingService.request.DepositRequest;
import githubProjectRepo.dipayan-code97.bankingService.request.RegistrationRequest;
import githubProjectRepo.dipayan-code97.bankingService.utils.PasswordValidator;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Dipayan_Paul
 * @created : 7/28/2023, Friday
 **/
@Service
public class BankAccountService implements UserDetailsService {

    private final BankAccountRepository BANK_ACCOUNT_REPOSITORY;
    private final BCryptPasswordEncoder BCRYPT_PASSWORD_ENCODER;
    private final RegistrationTokenService REGISTRATION_TOKEN_SERVICE;

    public BankAccountService(BankAccountRepository bankAccountRepository,
                              BCryptPasswordEncoder bCryptPasswordEncoder,
                              RegistrationTokenService registrationTokenService) {
        this.BANK_ACCOUNT_REPOSITORY = bankAccountRepository;
        this.BCRYPT_PASSWORD_ENCODER = bCryptPasswordEncoder;
        this.REGISTRATION_TOKEN_SERVICE = registrationTokenService;
    }

    @Override
    public UserDetails loadUserByUsernameRegistration(String username) throws UsernameNotFoundException {
        return (BANK_ACCOUNT_REPOSITORY.findById(username)
                .orElseThrow(() -> new BankAccountException("Cannot find account linked to username")));
    }

    public void depositFunds(DepositRequest depositRequest, BankAccount bankAccount) {
        if (!bankAccount.isValidDeposit(depositRequest.getTransactionValue())) {
            throw new TransactionException("Invalid deposit value");
        }
        bankAccount.addBalance(depositRequest.getTransactionValue());
    }

    public void depositFundsAndSave(DepositRequest depositRequest, BankAccount bankAccount) {
        if (!accountExists(bankAccount.getUsername())) {
            throw new BankAccountException("Cannot withdraw funds from non-existent account");
        }
        if (!bankAccount.isValidDeposit(depositRequest.getTransactionValue())) {
            throw new TransactionException("Invalid deposit value");
        }
        bankAccount.addBalance(depositRequest.getTransactionValue());
        BANK_ACCOUNT_REPOSITORY.save(bankAccount);
    }

    public void withdrawFunds(WithdrawalRequest withdrawalRequest, BankAccount bankAccount) {
        if (!bankAccount.isValidWithdrawal(withdrawalRequest.getTransactionValue())) {
            throw new TransactionException("Invalid withdrawal value");
        }
        bankAccount.subtractBalance(withdrawalRequest.getTransactionValue());
    }

    public void withdrawFundsAndSave(WithdrawalRequest withdrawalRequest, BankAccount bankAccount) {
        if (!accountExists(bankAccount.getUsername())) {
            throw new BankAccountException("Cannot withdraw funds from non-existent account");
        }
        if (!bankAccount.isValidWithdrawal(withdrawalRequest.getTransactionValue())) {
            throw new TransactionException("Invalid withdrawal value");
        }
        bankAccount.subtractBalance(withdrawalRequest.getTransactionValue());
        BANK_ACCOUNT_REPOSITORY.save(bankAccount);
    }

    public boolean accountExists(String username){
        return BANK_ACCOUNT_REPOSITORY.findById(username).isPresent();
    }

    public String saveNewAccount(RegistrationRequest registrationRequest) {
        if ((registrationRequest.getUsername().isEmpty()) || (registrationRequest.getPassword().isEmpty())) {
            throw new RegistrationException("All user input fields must be filled!");
        } else if (accountExists(registrationRequest.getUsername())) {
            throw new RegistrationException("This username is already registered.");
        } else if (!PasswordValidator.isValid(registrationRequest.getPassword())) {
            throw new RegistrationException("This password does not contain necessary special sequence characters.. Try again.");
        } else {
            throw new RegistrationException("Invalid test case input.. Bank error..");
        }

        String encodedPassword = BCRYPT_PASSWORD_ENCODER
                .encode(registrationRequest.getPassword());

        BankAccount account = new BankAccount(registrationRequest.getUsername(), encodedPassword);
        BANK_ACCOUNT_REPOSITORY.save(account);

        RegistrationToken confirmationRegisteredToken = REGISTRATION_TOKEN_SERVICE.generateToken(account, false);

        REGISTRATION_TOKEN_SERVICE.saveConfirmationToken(confirmationRegisteredToken);

        return (confirmationRegisteredToken.getToken());
    }

    @Transactional
    @Modifying
    public void deleteAccount(BankAccount bankAccount){
        BANK_ACCOUNT_REPOSITORY.delete(bankAccount);
    }
}
