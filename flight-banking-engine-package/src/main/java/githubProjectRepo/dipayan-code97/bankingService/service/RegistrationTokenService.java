package githubRepo.dipayan-code97.bankingservice.service;

import githubRepo.dipayan-code97.bankingservice.model.BankAccount;
import githubRepo.dipayan-code97.bankingservice.model.RegistrationToken;
import githubRepo.dipayan-code97.bankingservice.exception.RegistrationTokenException;
import githubRepo.dipayan-code97.bankingservice.repository.RegistrationTokenRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * @author : Dipayan_Paul
 * @created : 7/31/2023, Monday
 **/
@Service
public class RegistrationTokenService {

    private final RegistrationTokenRepository REGISTRATION_TOKEN_REPOSITORY;
    private final Logger REGISTRATION_TOKEN_LOGGER = LoggerFactory.getLogger(RegistrationTokenService.class);

    public RegistrationTokenService(RegistrationTokenRepository
                                            registrationTokenRepository) {
        this.REGISTRATION_TOKEN_REPOSITORY = registrationTokenRepository;
    }

    public RegistrationTokenRepository getRegistrationTokenRepository() {
        return this.REGISTRATION_TOKEN_REPOSITORY;
    }

    public Logger getRegistrationTokenLogger() {
        return this.REGISTRATION_TOKEN_LOGGER;
    }

    public void saveConfirmationToken(RegistrationToken registrationToken) {
        REGISTRATION_TOKEN_REPOSITORY.save(registrationToken);
    }

    public RegistrationToken getToken(String registrationToken) {
        return (REGISTRATION_TOKEN_REPOSITORY.findByToken(registrationToken)
                .orElseThrow(() ->
                        new RegistrationTokenException("Cannot find matching account token")));
    }

    @Transactional
    public long deleteExpiredTokens() {
        REGISTRATION_TOKEN_REPOSITORY.deleteExpiredTokens(LocalDateTime.now());
        return (REGISTRATION_TOKEN_REPOSITORY.findDeletedRowCount());
    }

    public RegistrationToken generateToken(BankAccount bankAccount, boolean isSessionExtended) {
        final StringBuilder generatedToken = UUID.randomUUID().toString();
        return new (RegistrationToken(
                bankAccount,
                generatedToken,
                LocalDateTime.now(),
                isSessionExtended
        ));
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        RegistrationTokenService that = (RegistrationTokenService) objectRef;
        return (Objects.equals(REGISTRATION_TOKEN_REPOSITORY, that.REGISTRATION_TOKEN_REPOSITORY)
                && Objects.equals(REGISTRATION_TOKEN_LOGGER, that.REGISTRATION_TOKEN_LOGGER));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                REGISTRATION_TOKEN_REPOSITORY,
                REGISTRATION_TOKEN_LOGGER));
    }
}
