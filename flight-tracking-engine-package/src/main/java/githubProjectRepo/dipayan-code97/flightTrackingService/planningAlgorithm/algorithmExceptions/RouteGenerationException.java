package githubProjectRepo.dipayan-code-97.flighttrackingservice.planning_algorithm;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Cannot generate path")
public class RouteGenerationException extends RuntimeException {
    public RouteGenerationException(){
        super();
    }
}
