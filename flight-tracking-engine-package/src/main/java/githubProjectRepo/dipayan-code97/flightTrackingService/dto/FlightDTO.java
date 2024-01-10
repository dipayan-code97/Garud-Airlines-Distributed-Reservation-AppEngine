package githubProjectRepo.dipayan-code97.flightTrackingService.dto;

import githubProjectRepo.dipayan-code97.flightTrackingService.model.Flight;-code97.flightTrackingService.utils.DateTimeUtils;

public class FlightDTO {

    private final String FLIGHT_NUMBER;
    private RouteDTO routeDTO;
    private PlaneDTO planeDTO;
    private String departureDateTime;
    private String arrivalDateTime;

    public FlightDTO(Flight flight) {
        this.FLIGHT_NUMBER = flight.getFlightIdentifier();
        this.routeDTO = new RouteDTO(flight.getRoute());
        this.planeDTO = new PlaneDTO(flight.getPlane());
        this.departureDateTime = DateTimeUtils.format(flight.getTakeOffDateTime());
        this.arrivalDateTime = DateTimeUtils.format(flight.getArrivalTime());
    }

    public String getFlightNumber() {
        return this.FLIGHT_NUMBER;
    }

    public void setFlightNumber(String FLIGHT_NUMBER) {
        this.FLIGHT_NUMBER = FLIGHT_NUMBER;
    }

    public RouteDTO getRouteDTO() {
        return this.routeDTO;
    }

    public void setRouteDTO(RouteDTO routeDTO) {
        this.routeDTO = routeDTO;
    }

    public PlaneDTO getPlaneDTO() {
        return this.planeDTO;
    }

    public void setPlaneDTO(PlaneDTO planeDTO) {
        this.planeDTO = planeDTO;
    }

    public String getDepartureDateTime() {
        return this.departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getArrivalDateTime() {
        return this.arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        FlightDTO flightDTO = (FlightDTO) objectRef;
        return (Objects.equals(getFlightNumber(), flightDTO.getFlightNumber())
                && Objects.equals(getRouteDTO(), flightDTO.getRouteDTO())
                && Objects.equals(getPlaneDTO(), flightDTO.getPlaneDTO())
                && Objects.equals(getDepartureDateTime(), flightDTO.getDepartureDateTime())
                && Objects.equals(getArrivalDateTime(), flightDTO.getArrivalDateTime()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getFlightNumber(),
                getRouteDTO(), getPlaneDTO(),
                getDepartureDateTime(), getArrivalDateTime()));
    }

    @Override
    public String toString() {
        return ("FlightDTO{" +
                "FlightNumber='" + FLIGHT_NUMBER + '\'' +
                ", RouteDTO=" + routeDTO +
                ", PlaneDTO=" + planeDTO +
                ", DepartureDateTime='" + departureDateTime + '\'' +
                ", ArrivalDateTime='" + arrivalDateTime + '\'' +
                '}');
    }
}
