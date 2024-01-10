package githubProjectRepo.dipayan-code97.flighttrackingservice.entity;

import githubProjectRepo.dipayan-code97.flighttrackingservice.properties.PlaneList;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "plane")
@Table(name = "planes")
public class Aeroplane {

    @Id
    @Column(name = "call_sign")
    private String planeCallSign;

    @Column(name = "model")
    private String model;

    @Column(name = "seat_capacity")
    private Integer seatingCapacity;

    @Column(name = "luxury_seats")
    private Integer luxurySeats;

    @Column(name = "cruising_speed_knots")
    private Integer cruisingSpeedKnots;

    @Column(name = "range_miles")
    private Integer rangeMiles;

    public Aeroplane(String planeCallSign, PlaneList.PlaneModel planeModel) {
        this.planeCallSign = planeCallSign;
        this.model = planeModel.getModel();
        this.seatingCapacity = planeModel.getSeatingCapacity();
        this.luxurySeats = planeModel.getLuxurySeats();
        this.cruisingSpeedKnots = planeModel.getCruisingSpeedKnots();
        this.rangeMiles = planeModel.getRangeMiles();
    }

    public String getPlaneCallSign() {
        return this.planeCallSign;
    }

    public void setPlaneCallSign(String planeCallSign) {
        this.planeCallSign = planeCallSign;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getSeatingCapacity() {
        return this.seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public Integer getLuxurySeats() {
        return this.luxurySeats;
    }

    public void setLuxurySeats(Integer luxurySeats) {
        this.luxurySeats = luxurySeats;
    }

    public Integer getCruisingSpeedKnots() {
        return this.cruisingSpeedKnots;
    }

    public void setCruisingSpeedKnots(Integer cruisingSpeedKnots) {
        this.cruisingSpeedKnots = cruisingSpeedKnots;
    }

    public Integer getRangeMiles() {
        return this.rangeMiles;
    }

    public void setRangeMiles(Integer rangeMiles) {
        this.rangeMiles = rangeMiles;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if (objectRef == null || getClass() != objectRef.getClass()) return false;
        if (!super.equals(objectRef)) return false;
        Aeroplane aeroplane = (Aeroplane) objectRef;
        return (Objects.equals(getPlaneCallSign(), aeroplane.getPlaneCallSign())
                && Objects.equals(getModel(), aeroplane.getModel())
                && Objects.equals(getSeatingCapacity(), aeroplane.getSeatingCapacity())
                && Objects.equals(getLuxurySeats(), aeroplane.getLuxurySeats())
                && Objects.equals(getCruisingSpeedKnots(), aeroplane.getCruisingSpeedKnots())
                && Objects.equals(getRangeMiles(), aeroplane.getRangeMiles()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getPlaneCallSign(),
                getModel(),
                getSeatingCapacity(),
                getLuxurySeats(),
                getCruisingSpeedKnots(),
                getRangeMiles()));
    }
}
