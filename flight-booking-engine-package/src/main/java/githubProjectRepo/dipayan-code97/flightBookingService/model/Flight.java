package githubProjectRepo.dipayan-code97.flightBookingService.model;

import githubProjectRepo.dipayan-code97.flightBookingService.payload.FlightResponse;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * @author : Dipayan Paul
 * @created : 8/19/2023, Saturday
 **/
/*
This class is used to store details related to flights that are booked.
Details include flight identifier, plane type & seat info, ticket prices etc.
This class does not store any direct flight info (departure, destination) except take off time
& flight duration, and instead relies on the flight tracking service using the flight identifier
 */
@Entity(name = "flight")
@Table(name = "flights")
public class Flight {

    @Id
    private String flightNumber;

    @Column(name = "takeOffTime")
    private LocalDateTime flightsLocalDateTime;

    @Column(name = "duration")
    private Time flightsDurationMinutes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private SeatingInformation seatingDetail;

    public Flight(FlightResponse flightResponse) {

    }

    public Flight(String flightNumber,
                  LocalDateTime flightsLocalDateTime,
                  Time flightsDurationMinutes,
                  SeatingInformation seatingDetail) {
        this.flightNumber = flightNumber;
        this.flightsLocalDateTime = flightsLocalDateTime;
        this.flightsDurationMinutes = flightsDurationMinutes;
        this.seatingDetail = seatingDetail;
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDateTime getFlightsLocalDateTime() {
        return this.flightsLocalDateTime;
    }

    public void setFlightsLocalDateTime(LocalDateTime flightsLocalDateTime) {
        this.flightsLocalDateTime = flightsLocalDateTime;
    }

    public Time getFlightsDurationMinutes() {
        return this.flightsDurationMinutes;
    }

    public void setFlightsDurationMinutes(Time flightsDurationMinutes) {
        this.flightsDurationMinutes = flightsDurationMinutes;
    }

    public SeatingDetails getSeatingDetails() {
        return this.seatingDetail;
    }

    public void setSeatingDetails(SeatingInformation seatingDetail) {
        this.seatingDetail = seatingDetail;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        Flight flight = (Flight) objectRef;
        return (Objects.equals(getFlightNumber(), flight.getFlightNumber())
                && Objects.equals(getFlightsLocalDateTime(), flight.getFlightsLocalDateTime())
                && Objects.equals(getFlightsDurationMinutes(), flight.getFlightsDurationMinutes())
                && Objects.equals(getSeatingDetail(), flight.getSeatingDetail()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getFlightNumber(),
                getFlightsLocalDateTime(),
                getFlightsDurationMinutes(),
                getSeatingDetail()));
    }

    @Override
    public String toString() {
        return ("Flight{" +
                "FlightIdentifier=" + flightNumber +
                ", FlightLocalDateTime=" + flightsLocalDateTime +
                ", FlightDurationMinutes=" + flightsDurationMinutes +
                ", SeatingInformation=" + seatingDetail +
                '}');
    }
}
