package githubProjectRepo.dipayan-code97.bankingService.serviceFeatureRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @author : Dipayan Paul
 * @created : 7/31/2023, Monday
 **/

public class RegistrationServiceRequest {

    private final String REGISTRATION_USERNAME;
    private final String REGISTRATION_PASSWORD;

    public RegistrationServiceRequest(String registrationUsername,
                                      String registrationPassword) {
        this.REGISTRATION_USERNAME = registrationUsername;
        this.REGISTRATION_PASSWORD = registrationPassword;
    }

    public String getRegistrationUsername() {
        return this.REGISTRATION_USERNAME;
    }

    public void setRegistrationUsername(String registrationUsername) {
        this.REGISTRATION_USERNAME = registrationUsername;
    }

    public String getRegistrationPassword() {
        return this.REGISTRATION_PASSWORD;
    }

    public void setRegistrationPassword(String REGISTRATION_PASSWORD) {
        this.REGISTRATION_PASSWORD = REGISTRATION_PASSWORD;
    }

    public String toJsonString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass()) return false;
        if (!super.equals(objectRef)) return false;
        RegistrationRequest that = (RegistrationRequest) objectRef;
        return (Objects.equals(REGISTRATION_USERNAME, that.REGISTRATION_USERNAME)
                && Objects.equals(REGISTRATION_PASSWORD, that.REGISTRATION_PASSWORD)));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                REGISTRATION_USERNAME, REGISTRATION_PASSWORD));
    }

    @Override
    public String toString() {
        return ("RegistrationRequest{" +
                "Username='" + REGISTRATION_USERNAME + '\'' +
                ", Password='" + REGISTRATION_PASSWORD + '\'' +
                '}');
    }
}
