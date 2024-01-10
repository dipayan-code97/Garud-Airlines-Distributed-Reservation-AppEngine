package githubProjectRepo.dipayan-code97.bankingservice.service;

import githubProjectRepo.dipayan-code97.bankingservice.entity.BankAccount;
import githubProjectRepo.dipayan-code97.bankingservice.exception.TransactionException;
import githubProjectRepo.dipayan-code97.bankingservice.request.DepositRequest;
import githubProjectRepo.dipayan-code97.bankingservice.request.WithdrawalRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Dipayan Paul
 * @created : 8/11/2023, Friday
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class BankAccountServiceTest {

    private final BankAccountService BANK_ACCOUNT_SERVICE;

    @Autowired
    BankAccountServiceTest(BankAccountService bankAccountService) {
        this.BANK_ACCOUNT_SERVICE = bankAccountService;
    }

    @Test
    void depositFunds(){
        BankAccount accountTest = new BankAccount("Test", "Test");
        test.setBalance(1000.0);

        DepositRequest depositRequest1 = new DepositRequest(1000.0, "TEST");

        assertDoesNotThrow(() -> BANK_ACCOUNT_SERVICE.depositFunds(depositRequest1, test));

        DepositRequest depositRequest2 = new DepositRequest(0.0, "TEST");
        assertThrows(TransactionException.class, () -> BANK_ACCOUNT_SERVICE.depositFunds(depositRequest2, test));

        DepositRequest depositRequest3 = new DepositRequest(-10.0, "TEST");
        assertThrows(TransactionException.class, () -> BANK_ACCOUNT_SERVICE.depositFunds(depositRequest3, test));
    }

    @Test
    void withdrawFunds(){
        BankAccount bankAccount = new BankAccount("Test", "Test");
        assertThrows(TransactionException.class, () -> BANK_ACCOUNT_SERVICE.withdrawFunds(
                new WithdrawalRequest(100.0, "TEST"), bankAccount));

        bankAccount.setBalance(1000.0);
        assertDoesNotThrow(() -> BANK_ACCOUNT_SERVICE.withdrawFunds(
                new WithdrawalRequest(100.0, "TEST"), bankAccount));

        WithdrawalRequest withdrawalRequest1 = new WithdrawalRequest(0.0, "TEST");
        assertThrows(TransactionException.class, () -> BANK_ACCOUNT_SERVICE.withdrawFunds(withdrawalRequest1, bankAccount));

        WithdrawalRequest withdrawalRequest2 = new WithdrawalRequest(-10000.0, "TEST");
        assertThrows(TransactionException.class, () -> BANK_ACCOUNT_SERVICE.withdrawFunds(withdrawalRequest2, bankAccount));
    }
}