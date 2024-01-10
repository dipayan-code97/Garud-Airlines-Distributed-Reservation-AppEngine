package githubProjectRepo.dipayan-code97.bankingService.serviceFeatureRequest;

/**
 * @author : Dipayan Paul
 * @created : 7/31/2023, Monday
 **/
public class DepositServiceRequest {

    private final String DEPOSIT_REQUEST_TOKEN;
    private final Double TRANSACTION_AMOUNT;

    public DepositServiceRequest(Double transactionAmount,
                                 String depositRequestToken) {
        this.TRANSACTION_AMOUNT = transactionAmount;
        this.DEPOSIT_REQUEST_TOKEN = depositRequestToken;
    }

    public String getDepositRequestToken() {
        return this.DEPOSIT_REQUEST_TOKEN;
    }

    public Double getTransactionAmount() {
        return this.TRANSACTION_AMOUNT;
    }


    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        DepositRequest that = (DepositRequest) objectRef;
        return (Objects.equals(getDepositRequestToken(),
                that.getDEPOSIT_REQUEST_TOKEN())
                && Objects.equals(getTransactionAmount(),
                that.getTRANSACTION_AMOUNT()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getDepositRequestToken(),
                getTransactionAmount()));
    }

    @Override
    public String toString() {
        return ("DepositRequest{" +
                "requestToken='" + DEPOSIT_REQUEST_TOKEN + '\'' +
                ", transactionAmount=" + TRANSACTION_AMOUNT +
                '}');
    }
}
