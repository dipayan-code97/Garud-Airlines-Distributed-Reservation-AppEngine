package githubProjectRepo.dipayan-code97.bankingService.configuration;

import githubProjectRepo.dipayan-code97.bankingservice.properties.SchedulingProperties;
import githubProjectRepo.dipayan-code97.bankingservice.service.RegistrationTokenService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author : Dipayan_Paul
 * @created : 7/31/2023, Monday
 **/
@Configuration
@EnableScheduling
@AllArgsConstructor
//clear all database tokens at end of day
public class RegistrationTokenPurge {

    private final RegistrationTokenService REGISTRATION_TOKEN_SERVICE;
    private final Logger REGISTRATION_REQUEST_LOGGER = LoggerFactory.getLogger(RegistrationTokenPurge.class);

    public RegistrationTokenPurge(RegistrationTokenService REGISTRATION_TOKEN_SERVICE) {
        this.REGISTRATION_TOKEN_SERVICE = REGISTRATION_TOKEN_SERVICE;
    }

    public RegistrationTokenService getRegistrationTokenService() {
        return this.REGISTRATION_TOKEN_SERVICE;
    }

    public Logger getRegistrationRequestLogger() {
        return this.REGISTRATION_REQUEST_LOGGER;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        RegistrationTokenPurge that = (RegistrationTokenPurge) objectRef;
        return (Objects.equals(getregistrationTokenService(), that.getregistrationTokenService())
                && Objects.equals(getRegistrationRequestLogger(), that.getRegistrationRequestLogger()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getregistrationTokenService(), getRegistrationRequestLogger()));
    }

    @Scheduled(cron = SchedulingProperties.runAtMidnight)
    public void execute() {
        long deleted = REGISTRATION_TOKEN_SERVICE.deleteExpiredTokens();
        if (deleted > 0) {
            REGISTRATION_REQUEST_LOGGER.info("Tokens purged: " + deleted);
        }
    }

    @Override
    public String toString() {
        return ("RegistrationTokenPurge{" +
                "REGISTRATION_TOKEN_SERVICE=" + REGISTRATION_TOKEN_SERVICE +
                ", REGISTRATION_REQUEST_LOGGER=" + REGISTRATION_REQUEST_LOGGER +
                '}');
    }
}
