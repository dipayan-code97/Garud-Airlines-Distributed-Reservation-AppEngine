package githubProjectRepo.dipayan-code97.bankingService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Dipayan Paul
 * @created : 7/31/2023, Monday
 **/
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RegistrationTokenException extends RuntimeException {
    public RegistrationTokenException(String tokenMessage) {
        super(tokenMessage);
    }
}
