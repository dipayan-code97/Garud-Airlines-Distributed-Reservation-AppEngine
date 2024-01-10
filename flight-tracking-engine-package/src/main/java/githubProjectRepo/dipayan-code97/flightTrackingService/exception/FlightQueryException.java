package githubProjectRepo.dipayan-code97.flightTrackingService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class FlightQueryException extends RuntimeException {
    public FlightQueryException(String message) {
        super(message);
    }
}
