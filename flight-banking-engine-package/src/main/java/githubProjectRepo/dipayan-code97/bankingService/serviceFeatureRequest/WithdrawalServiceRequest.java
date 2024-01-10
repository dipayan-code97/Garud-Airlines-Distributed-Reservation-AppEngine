package githubProjectRepo.dipayan-code97.bankingService.serviceFeatureRequest;

/**
 * @author : Dipayan Paul
 * @created : 7/31/2023, Monday
 **/
public class WithdrawalRequest {

    private final String WITHDRAWAL_TOKEN_REQUEST;
    private final Double WITHDRAWAL_TRANSACTED_AMOUNT;

    public WithdrawalRequest(Double withdrawalTransactedAmount, String withdrawalTokenRequest) {
        this.WITHDRAWAL_TRANSACTED_AMOUNT = withdrawalTransactedAmount;
        this.WITHDRAWAL_TOKEN_REQUEST = withdrawalTokenRequest;
    }

    public String getWithdrawalTokenRequest() {
        return this.WITHDRAWAL_TOKEN_REQUEST;
    }

    public Double getWithdrawalTransactedAmount() {
        return this.WITHDRAWAL_TRANSACTED_AMOUNT;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        WithdrawalRequest that = (WithdrawalRequest) objectRef;
        return (Objects.equals(getWithdrawalTokenRequest(), that.getWithdrawalTokenRequest())
                && Objects.equals(getWithdrawalTransactedAmount(), that.getWithdrawalTransactedAmount()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getWithdrawalTokenRequest(),
                getWithdrawalTransactedAmount()));
    }

    @Override
    public String toString() {
        return ("WithdrawalRequest{" +
                "RequestToken='" + WITHDRAWAL_TOKEN_REQUEST + '\'' +
                ", TransactionAmount=" + WITHDRAWAL_TRANSACTED_AMOUNT +
                '}');
    }
}
