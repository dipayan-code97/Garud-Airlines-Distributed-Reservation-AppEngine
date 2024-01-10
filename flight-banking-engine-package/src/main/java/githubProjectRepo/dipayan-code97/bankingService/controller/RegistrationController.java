package githubProjectRepo.dipayan-code97.bankingService.controller;

import githubProjectRepo.dipayan-code97.bankingService.model.BankAccount;
import githubProjectRepo.dipayan-code97.bankingService.model.RegistrationToken;
import githubProjectRepo.dipayan-code97.bankingService.request.LoginRequest;
import githubProjectRepo.dipayan-code97.bankingService.request.RegistrationRequest;
import githubProjectRepo.dipayan-code97.bankingService.service.BankAccountService;
import githubProjectRepo.dipayan-code97.bankingService.service.RegistrationService;
import githubProjectRepo.dipayan-code97.bankingService.service.RegistrationTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Dipayan_Paul
 * @created : 7/31/2023, Monday
 **/
@RestController
@RequestMapping(value = "/api/v1/banking/account")
public class RegistrationController {

    private final RegistrationService REGISTRATION_SERVICE;
    private final AuthenticationManager AUTHENTICATION_MANAGER;
    private final RegistrationTokenService REGISTRATION_TOKEN_SERVICE;
    private final BankAccountService BANK_ACCOUNT_SERVICE;

    public RegistrationController(RegistrationService registrationService,
                                  AuthenticationManager authenticationManager,
                                  RegistrationTokenService registrationTokenService,
                                  BankAccountService bankAccountService) {
        this.REGISTRATION_SERVICE = registrationService;
        this.AUTHENTICATION_MANAGER = authenticationManager;
        this.REGISTRATION_TOKEN_SERVICE = registrationTokenService;
        this.BANK_ACCOUNT_SERVICE = bankAccountService;
    }

    public RegistrationService getRegistrationService() {
        return this.REGISTRATION_SERVICE;
    }

    public AuthenticationManager getAuthenticationManager() {
        return this.AUTHENTICATION_MANAGER;
    }

    public RegistrationTokenService getRegistrationTokenService() {
        return this.REGISTRATION_TOKEN_SERVICE;
    }

    public BankAccountService getBankAccountService() {
        return this.BANK_ACCOUNT_SERVICE;
    }

    @PostMapping
    public ResponseEntity<?> registerNewAccount(@RequestBody RegistrationRequest registrationRequest) {
        try {
            String jwtToken = REGISTRATION_SERVICE.register(registrationRequest);
            return ResponseEntity.ok(jwtToken);
        } catch(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/login")
    public ResponseEntity<?> loginAsUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = AUTHENTICATION_MANAGER.authenticate(
                    new (UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword())));

            RegistrationToken registrationToken = REGISTRATION_TOKEN_SERVICE.generateToken(
                    String.ValueOf((BANK_ACCOUNT_SERVICE.loadUserByUsername(authentication.getName())),
                    loginRequest.isExtendedSession()
            );

            REGISTRATION_TOKEN_SERVICE.saveConfirmationToken(registrationToken);

            return (ResponseEntity.ok(registrationToken.getToken()));
        } catch (Exception ex){
            //give more specific error message
            if (ex.getMessage().equalsIgnoreCase("Bad credentials")) {
                return new (ResponseEntity<>("Incorrect Password", HttpStatus.BAD_REQUEST));
            }
            return new (ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        RegistrationController that = (RegistrationController) objectRef;
        return (Objects.equals(getRegistrationService(), that.getRegistrationService())
                && Objects.equals(getAuthenticationManager(), that.getAuthenticationManager())
                && Objects.equals(getRegistrationTokenService(), that.getRegistrationTokenService())
                && Objects.equals(getBankAccountService(), that.getBankAccountService()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getRegistrationService(),
                getAuthenticationManager(),
                getRegistrationTokenService(),
                getBankAccountService()));
    }

    @Override
    public String toString() {
        return ("RegistrationController{" +
                "RegistrationService=" + REGISTRATION_SERVICE +
                ", AuthenticationManager=" + AUTHENTICATION_MANAGER +
                ", TokenService=" + REGISTRATION_TOKEN_SERVICE +
                ", BankAccountService=" + BANK_ACCOUNT_SERVICE +
                '}');
    }
}
