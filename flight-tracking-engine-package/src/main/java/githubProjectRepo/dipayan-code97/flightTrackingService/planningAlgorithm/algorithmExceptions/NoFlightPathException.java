package githubProjectRepo.dipayan-code97.flighttrackingservice.planning_algorithm;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No flight exists between these airports")
public class NoFlightPathException extends RuntimeException {
}
