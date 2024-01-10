package githubProjectRepo.dipayan-code97.flightTrackingService.dto;

import githubProjectRepo.dipayan.flightTrackingService.model.FlightRouteScheduler;
import githubProjectRepo.dipayan.flightTrackingService.utils.DateTimeUtils;
import java.util.Set;

public class FlightRouteSchedulerSummaryDTO {

    private final String FLIGHT_DATE;
    private final int FLIGHT_COUNT;
    private final Set<FlightRouteSchedularSummaryDTO> FLIGHT_ROUTE_SCHEDULER_SUMMARY_DTO_STORAGE;

    public FlightSchedulerSummaryDTO(FlightRouteScheduler flightRouteScheduler) {
        this.FLIGHT_DATE = DateTimeUtils.format(flightRouteScheduler.getDate());
        this.FLIGHT_ROUTE_SCHEDULER_SUMMARY_DTO_STORAGE = (flightRouteScheduler.getFlightsToday().stream().map(FlightSummaryDTO::new).toSet());
        this.FLIGHT_COUNT = FLIGHT_ROUTE_SCHEDULER_SUMMARY_DTO_STORAGE.size();
    }

    public String getFLIGHT_DATE() {
        return this.FLIGHT_DATE;
    }

    public void setFLIGHT_DATE(String FLIGHT_DATE) {
        this.FLIGHT_DATE = FLIGHT_DATE;
    }

    public int getFLIGHT_COUNT() {
        return this.FLIGHT_COUNT;
    }

    public void setFLIGHT_COUNT(int FLIGHT_COUNT) {
        this.FLIGHT_COUNT = FLIGHT_COUNT;
    }

    public Set<FlightSummaryDTO> getFLIGHT_ROUTE_SCHEDULER_SUMMARY_DTO_STORAGE() {
        return this.FLIGHT_ROUTE_SCHEDULER_SUMMARY_DTO_STORAGE;
    }

    public void setFLIGHT_ROUTE_SCHEDULER_SUMMARY_DTO_STORAGE(Set<FlightSummaryDTO> FLIGHT_ROUTE_SCHEDULER_SUMMARY_DTO_STORAGE) {
        this.FLIGHT_ROUTE_SCHEDULER_SUMMARY_DTO_STORAGE = FLIGHT_ROUTE_SCHEDULER_SUMMARY_DTO_STORAGE;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        FlightSchedulerSummaryDTO that = (FlightSchedulerSummaryDTO) objectRef;
        return (Objects.equals(getFLIGHT_COUNT() == that.getFLIGHT_COUNT()
                && Objects.equals(getFLIGHT_DATE(), that.getFLIGHT_DATE())
                && Objects.equals(getFLIGHT_ROUTE_SCHEDULER_SUMMARY_DTO_STORAGE(), that.getFLIGHT()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getFLIGHT_DATE(),
                getFLIGHT_COUNT(), getFLIGHT_ROUTE_SCHEDULER_SUMMARY_DTO_STORAGE()));
    }

    @Override
    public String toString() {
        return ("FlightSchedulerSummaryDTO{" +
                "Date='" + FLIGHT_DATE + '\'' +
                ", FlightCount=" + FLIGHT_COUNT +
                ", Flights=" + FLIGHT_ROUTE_SCHEDULER_SUMMARY_DTO_STORAGE +
                '}');
    }
}