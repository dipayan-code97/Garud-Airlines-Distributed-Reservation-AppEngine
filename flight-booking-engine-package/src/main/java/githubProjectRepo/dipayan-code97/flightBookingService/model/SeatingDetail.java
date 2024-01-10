package githubProjectRepo.dipayan-code97.flightBookingService.model;

import jakarta.persistence.*;

/**
 * @author : Dipayan Paul
 * @created : 8/29/2023, Tuesday
 **/
/*
This class is used to track remaining seats, ticket prices, etc for flights. The TicketServicing.java
    helper class maintains remaining seat count based on demand, ticket pricing, and time till takeoff
 */
@Entity(name = "seatingInformation")
@Table(name = "seating_information")
public class SeatingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String seatNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_identifier")
    private Flight flight;

    public SeatingDetail(Long seatNumber,
                         Flight flight) {
        this.seatNumber = seatNumber;
        this.flight = flight;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public void setSeatNumber(Long seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Flight getFlight() {
        return this.getFlight();
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        SeatingInformation that = (SeatingInformation) objectRef;
        return (Objects.equals(getSeatNumber(), that.getSeatNumber())
                && Objects.equals(getFlight(), that.getFlight()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getSeatNumber(),
                getFlight()));
    }

    @Override
    public String toString() {
        return ("SeatingInformation{" +
                "SeatNumber='" + seatNumber + '\'' +
                ", FlightAggregator=" + flight +
                '}');
    }
}
