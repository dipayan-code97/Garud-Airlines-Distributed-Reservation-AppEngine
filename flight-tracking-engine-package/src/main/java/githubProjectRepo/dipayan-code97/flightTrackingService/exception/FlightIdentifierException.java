package githubProjectRepo.dipayan-code97.flightTrackingService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid flight-number")
public class FlightIdentifierException extends RuntimeException{
}
