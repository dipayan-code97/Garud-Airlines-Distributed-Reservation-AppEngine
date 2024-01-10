package githubProjectRepo.dipayan-code97.bankingService.serviceFeatureRequest;

/**
 * @author : Dipayan Paul
 * @created : 7/31/2023, Monday
 **/
public class LoginServiceRequest {

    private final String LOGIN_USERNAME;
    private final String LOGIN_PASSWORD;
    private final Boolean LOGIN_SESSION_IS_EXTENDED;

    public LoginServiceRequest(String loginUsername,
                               String loginPassword,
                               Boolean loginSessionIsExtended) {
        this.LOGIN_USERNAME = loginUsername;
        this.LOGIN_PASSWORD = loginPassword;
        this.LOGIN_SESSION_IS_EXTENDED = loginSessionIsExtended;
    }

    public String getoginUsername() {
        return this.LOGIN_USERNAME;
    }

    public String getLoginPassword() {
        return this.LOGIN_PASSWORD;
    }

    public Boolean getLoginSessionIsExtended() {
        return this.LOGIN_SESSION_IS_EXTENDED;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        LoginRequest that = (LoginRequest) objectRef;
        return (Objects.equals(getoginUsername(), that.getLOGIN_USERNAME())
                && Objects.equals(getLoginPassword(), that.getLOGIN_PASSWORD())
                && Objects.equals(getLoginSessionIsExtended(), that.getLOGIN_SESSION_IS_EXTENDED()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getoginUsername(),
                getLoginPassword(), getLoginSessionIsExtended()));
    }

    @Override
    public String toString() {
        return ("LoginRequest{" +
                "Username='" + LOGIN_USERNAME + '\'' +
                ", Password='" + LOGIN_PASSWORD + '\'' +
                ", SessionIsExtended=" + LOGIN_SESSION_IS_EXTENDED +
                '}');
    }
}
