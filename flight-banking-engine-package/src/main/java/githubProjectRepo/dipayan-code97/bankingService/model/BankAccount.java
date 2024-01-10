package githubProjectRepo.dipayan-code97.bankingService.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author : Dipayan_Paul
 * @created : 7/28/2023, Friday
 **/
@Entity(name = "bankAccount")
@Table(name = "bank_account")

public class BankAccount implements UserDetails {

    @Id
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "reward_points")
    private Integer rewardPoints;

    @Enumerated(EnumType.STRING)
    private BankAccountRole bankAccountRole;

    public BankAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.bankAccountRole = BankAccountRole.USER;
        this.balance = 0.0D;
        this.rewardPoints = 0;
    }

    public BankAccount() {

    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getRewardPoints() {
        return this.rewardPoints;
    }

    public void setRewardPoints(Integer rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public BankAccountRole getBankAccountRole() {
        return this.bankAccountRole;
    }

    public void setBankAccountRole(BankAccountRole bankAccountRole) {
        this.bankAccountRole = bankAccountRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isValidWithdrawal(Double withdrawalAmount){
        return ((withdrawalAmount > 0) && (balance - withdrawalAmount >= 0));
    }

    public boolean isValidDeposit(Double depositAmount){
        return ((depositAmount < 1_000_000D) && (depositAmount > 0));
    }

    public void addBalance(Double balanceAmount){
        Double newBalance = (balanceAmount + this.balance);
        this.setBalance(newBalance);
    }

    public void subtractBalance(Double balanceAmount){
        Double newBalance = (this.balance - balanceAmount);
        this.setBalance(newBalance);
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        BankAccount that = (BankAccount) objectRef;
        return (Objects.equals(getUsername(), that.getUsername())
                && Objects.equals(getPassword(), that.getPassword())
                && Objects.equals(getBalance(), that.getBalance())
                && Objects.equals(getRewardPoints(), that.getRewardPoints())
                && Objects.equals(getBankAccountRole(), that.getBankAccountRole()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getUsername(), getPassword(),
                getBalance(), getRewardPoints(),
                getBankAccountRole()));
    }

    @Override
    public String toString() {
        return ("BankAccount{" +
                "Username='" + username + '\'' +
                ", Password='" + password + '\'' +
                ", Balance=" + balance +
                ", RewardPoints=" + rewardPoints +
                ", BankAccountRole=" + bankAccountRole +
                '}');
    }
}
