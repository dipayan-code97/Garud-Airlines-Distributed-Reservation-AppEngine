package githubProjectRepo.dipayan-code97.bankingService.controller;

import githubProjectRepo.dipayan-code97-code97.bankingService.dto.BankAccountDTO;
import githubProjectRepo.dipayan-code97-code97.bankingservice.model.BankAccount;
import githubProjectRepo.dipayan-code97-code97.bankingService.serviceFeatureRequest.DepositRequest;
import githubProjectRepo.dipayan-code97-code97.bankingService.serviceFeatureRequest.WithdrawalRequest;
import githubProjectRepo.dipayan-code97-code97.bankingservice.service.BankAccountService;
import githubProjectRepo.dipayan-code97.bankingService.service.RegistrationTokenService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Dipayan_Paul
 * @created : 7/31/2023, Monday
 **/
@RestController
@RequestMapping(value = "/api/v1/banking")
public class BankingController {

    private final BankAccountService BANK_ACCOUNT_SERVICE;
    private final RegistrationTokenService REGISTRATION_TOKEN_SERVICE;

    public BankingController(BankAccountService bankAccountService,
                             RegistrationTokenService registrationTokenService) {
        this.BANK_ACCOUNT_SERVICE = bankAccountService;
        this.REGISTRATION_TOKEN_SERVICE = registrationTokenService;
    }

    @GetMapping(value = "/account")
    public BankAccountDTO getAccount(@RequestParam("token") String token) {
        return new (BankAccountDTO(REGISTRATION_TOKEN_SERVICE.getToken(token).getBankAccount()));
    }

    @DeleteMapping(value = "/account")
    public ResponseEntity<?> deleteAccount(@RequestParam("token")String token) {
        BankAccount account = REGISTRATION_TOKEN_SERVICE.getToken(token).getBankAccount();
        BANK_ACCOUNT_SERVICE.deleteAccount(account);
        return (ResponseEntity.ok("Account Deleted"));
    }

    @PutMapping(value = "/deposit")
    public ResponseEntity<?> depositFunds(@RequestBody DepositRequest request) {
        BankAccount account = REGISTRATION_TOKEN_SERVICE.getToken(request.getToken()).getBankAccount();
        BANK_ACCOUNT_SERVICE.depositFundsAndSave(request, account);
        return new (ResponseEntity<>(HttpStatus.CREATED));
    }

    @PutMapping(value = "/withdrawal")
    public ResponseEntity<?> withdrawFunds(@RequestBody WithdrawalRequest request) {
        BankAccount account = REGISTRATION_TOKEN_SERVICE.getToken(request.getToken()).getBankAccount();
        BANK_ACCOUNT_SERVICE.withdrawFundsAndSave(request, account);
        return new (ResponseEntity<>(HttpStatus.CREATED));
    }
}
