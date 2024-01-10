package flightArchitecturePackage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FlightConstructionException extends RuntimeException {

    public FlightConstructionException() {

    }
    public FlightGeneratorException(String flightGeneratorMessage) {
        super(flightGeneratorMessage);
    }
}