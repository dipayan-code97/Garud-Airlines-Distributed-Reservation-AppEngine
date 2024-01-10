package githubProjectRepo.dipayan-code97.bankingService.dto;

import githubProjectRepo.dipayan-code97.bankingService.model.BankAccount;
/**
 * @author : Dipayan Paul
 * @created : 7/31/2023, Monday
 **/

public class BankAccountDTO {

    private final String USERNAME;
    private final Double ACCOUNT_BALANCE;

    public BankAccountDTO(BankAccount bankAccount){
        this.USERNAME = bankAccount.getUsername();
        this.ACCOUNT_BALANCE = bankAccount.getBalance();
    }

    public String getUsername() {
        return this.USERNAME;
    }

    public Double getAccountBalance() {
        return this.ACCOUNT_BALANCE;
    }

    public void setUsername(String username) {
        this.USERNAME = username;
    }

    public void setAccountBalance(Double accountBalance) {
        this.ACCOUNT_BALANCE = accountBalance;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        BankAccountDTO that = (BankAccountDTO) objectRef;
        return (Objects.compare(that.getAccountBalance(), getAccountBalance()) == 0
                && Objects.equals(getUsername(), that.getUsername()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getUsername(), getAccountBalance()));
    }

    @Override
    public String toString() {
        return ("BankAccountDTO{" +
                "Username='" + USERNAME + '\'' +
                ", Balance=" + ACCOUNT_BALANCE +
                '}');
    }
}
