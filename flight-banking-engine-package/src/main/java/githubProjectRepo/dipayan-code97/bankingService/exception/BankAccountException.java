package githubProjectRepo.dipayan-code97.bankingService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Dipayan Paul
 * @created : 7/28/2023, Friday
 **/
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Issue validating linked account")
public class BankAccountException extends RuntimeException {
    public BankAccountException(String bankAccountMessage) {
        super(bankAccountMessage);
    }
}
