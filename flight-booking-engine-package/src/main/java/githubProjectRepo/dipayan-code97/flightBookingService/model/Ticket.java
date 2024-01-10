package githubProjectRepo.dipayan-code97.flightBookingService.model;

import jakarta.persistence.*;

/**
 * @author : Dipayan Paul
 * @created : 8/19/2023, Saturday
 **/
@Entity(name = "ticket")
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketNumber;

    @Column(name = "cost")
    private Double ticketPrice;

    public Ticket(Long ticketNumber, Double ticketPrice) {
        this.ticketNumber = ticketNumber;
        this.ticketPrice = ticketPrice;
    }

    public Long getTicketNumber() {
        return this.ticketNumber;
    }

    public void setTicketNumber(Long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Double getTicketPrice() {
        return this.ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        Ticket ticket = (Ticket) objectRef;
        return (Objects.equals(getTicketNumber(), ticket.getTicketNumber())
                && Objects.equals(getTicketPrice(), ticket.getTicketPrice()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getTicketNumber(),
                getTicketPrice()));
    }

    @Override
    public String toString() {
        return ("Ticket{" +
                "TicketNumber=" + ticketNumber +
                ", TicketCost=" + ticketPrice +
                '}');
    }
}
