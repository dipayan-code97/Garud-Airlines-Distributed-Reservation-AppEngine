package githubProjectRepo.dipayan-code97.flightTrackingService.model;

import githubProjectRepo.dipayan-code97.flightTrackingService.utils.DateTimeUtils;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "flight")
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "call_sign")
    private Plane plane;

    @Embedded
    private Route route;

    @Column(name = "scheduled_takeoff_time_and_date")
    private LocalDateTime flightDepartureDateTime;

    public Flight(Plane plane, Route route,
                  LocalDateTime flightDepartureDateTime) {
        this.plane = plane;
        this.route = route;
        this.flightDepartureDateTime = flightDepartureDateTime;
    }

    public Flight(ScheduledRoute dailyScheduledRoute,
                  LocalDateTime flightDepartureDateTime) {
        this.plane = dailyScheduledRoute.getPlane();
        this.route = dailyScheduledRoute.getRoute();
        this.flightDepartureDateTime = flightDepartureDateTime;
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Plane getPlane() {
        return this.plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Route getRoute() {
        return this.route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public LocalDateTime getFlightDepartureDateTime() {
        return this.flightDepartureDateTime;
    }

    public void setFlightDepartureDateTime(LocalDateTime flightDepartureDateTime) {
        this.flightDepartureDateTime = flightDepartureDateTime;
    }

    public int getLandingHour() {
        //this could technically return a number greater than 24 so this has to be fixed later.
        //I currently have it this was because the current scheduling technically
        //allows for invalid flights to be dismissed, but this should be resolved later
        //so that flights aren't dismissed.
        return (this.flightDepartureDateTime.plusMinutes((int)
                (this.route.getFlightDurationHours() * 60)).getHour() + 1);
    }

    public LocalDateTime getLandingDateTime(){
        return (this.flightDepartureDateTime.plusMinutes((int) (this.route.getFlightDurationHours() * 60)));
    }

    public boolean isMatchingAirport(String icao, boolean departureQuery) {
        if (departureQuery){
            return (this.getRoute().getDepartureAirport().getIcaoCode().equals(icao));
        }
        return (this.getRoute().getDestinationAirport().getIcaoCode().equals(icao));
    }

    public String toString(){
        return (this.getRoute().getDepartureAirport().getIcaoCode() + "->" + this.getRoute().getDestinationAirport().getIcaoCode());
    }
}
