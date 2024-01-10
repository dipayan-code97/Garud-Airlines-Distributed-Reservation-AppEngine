package githubProjectRepo.dipayan-code97.flightTrackingservice.dto;

import githubProjectRepo.dipayan-code97.flightTrackingService.model.ScheduledRoute;
import githubProjectRepo.dipayan-code97.flightTrackingService.utils.DateTimeUtils;
import java.util.Set;

public class FlightRouteSchedulerDTO {

    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private int flightCount;
    private Set<FlightDTO> flight;

    public FlightRouteSchedulerDTO(FlightRouteSchedulerDTO flightRouteSchedulerDTO) {
        this.date = DateTimeUtils.format(flightRouteSchedulerDTO.getDate());
        this.flight = new LinkedHashSet<>();
        this.flightCount = flight.size();
    }

    public LocalDateTime getDepartureDateTime() {
        return this.departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return this.arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public int getFlightCount() {
        return this.flightCount;
    }

    public void setFlightCount(int flightCount) {
        this.flightCount = flightCount;
    }

    public Set<FlightDTO> getFlight() {
        return this.flight;
    }

    public void setFlight(Set<FlightDTO> flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return ("FlightRouteSchedulerDTO{" +
                "DepartureDateTime=" + departureDateTime +
                ", ArrivalDateTime=" + arrivalDateTime +
                ", FlightCount=" + flightCount +
                ", Flights=" + flight +
                '}');
    }
}
