package githubProjectRepo.dipayan-code97.bankingService.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * @author : Dipayan_Paul
 * @created : 7/31/2023, Monday
 **/
@Entity(name = "jwtToken")
@Table(name = "jwt_tokens")

public class RegistrationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationNumber;

    @Column(name = "token", nullable = false)
    private String registrationToken;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime registrationStartDateAndTime;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime registrationExpiryDateAndTime;

    @ManyToOne
    @JoinColumn(name = "account_username", nullable = false)
    private BankAccount bankAccount;

    public RegistrationToken() {

    }
    public RegistrationToken(BankAccount bankAccount,
                             String registrationToken,
                             LocalDateTime registrationDateTime,
                             Boolean isTokenExtended){
        this.bankAccount = bankAccount;
        this.registrationToken = registrationToken;
        this.registrationStartDateAndTime = registrationDateTime;
        //extended tokens last 15 minutes, default tokens last 3
        this.registrationExpiryDateAndTime = (registrationDateTime.plusMinutes(isTokenExtended ? 15 : 3));
    }

    public Long getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(Long registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationToken() {
        return this.registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }

    public LocalDateTime getRegistrationStartDateAndTime() {
        return this.registrationStartDateAndTime;
    }

    public void setRegistrationStartDateAndTime(LocalDateTime registrationStartDateAndTime) {
        this.registrationStartDateAndTime = registrationStartDateAndTime;
    }

    public LocalDateTime getRegistrationExpiryDateAndTime() {
        return this.registrationExpiryDateAndTime;
    }

    public void setRegistrationExpiryDateAndTime(LocalDateTime registrationExpiryDateAndTime) {
        this.registrationExpiryDateAndTime = registrationExpiryDateAndTime;
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        RegistrationToken that = (RegistrationToken) objectRef;
        return (Objects.equals(getRegistrationNumber(), that.getRegistrationNumber())
                && Objects.equals(getRegistrationToken(), that.getRegistrationToken())
                && Objects.equals(getRegistrationStartDateAndTime(), that.getRegistrationStartDateAndTime())
                && Objects.equals(getRegistrationExpiryDateAndTime(), that.getRegistrationExpiryDateAndTime())
                && Objects.equals(getBankAccount(), that.getBankAccount()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getRegistrationNumber(),
                getRegistrationToken(),
                getRegistrationStartDateAndTime(),
                getRegistrationExpiryDateAndTime(),
                getBankAccount()));
    }

    @Override
    public String toString() {
        return ("RegistrationToken{" +
                "RegistrationId=" + registrationNumber +
                ", Token='" + registrationToken + '\'' +
                ", RegistrationDateAndTime=" + registrationStartDateAndTime +
                ", RegistrationExpiryDateAndTime=" + registrationExpiryDateAndTime +
                ", BankAccount=" + bankAccount +
                '}');
    }
}
