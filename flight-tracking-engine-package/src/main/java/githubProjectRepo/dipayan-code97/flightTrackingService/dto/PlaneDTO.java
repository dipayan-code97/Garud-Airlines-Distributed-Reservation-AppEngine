package githubProjectRepo.dipayan-code97.flightTrackingService.dto;

import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.Plane;

public class PlaneDTO {

    private String planeCallSign;
    private String model;
    private int seatCapacity;
    private int luxurySeats;
    private int speedKnots;
    private int rangeMiles;

    public PlaneDTO(Plane plane) {
        this.planeCallSign = plane.getCallSign();
        this.model = plane.getModel();
        this.seatCapacity = plane.getSeatingCapacity();
        this.luxurySeats = plane.getLuxurySeats();
        this.speedKnots = plane.getCruisingSpeedKnots();
        this.rangeMiles = plane.getRangeMiles();
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

    public int getSeatCapacity() {
        return this.seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public int getLuxurySeats() {
        return this.luxurySeats;
    }

    public void setLuxurySeats(int luxurySeats) {
        this.luxurySeats = luxurySeats;
    }

    public int getSpeedKnots() {
        return this.speedKnots;
    }

    public void setSpeedKnots(int speedKnots) {
        this.speedKnots = speedKnots;
    }

    public int getRangeMiles() {
        return this.rangeMiles;
    }

    public void setRangeMiles(int rangeMiles) {
        this.rangeMiles = rangeMiles;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        PlaneDTO planeDTO = (PlaneDTO) object;
        return (Objects.equals(getSeatCapacity() == planeDTO.getSeatCapacity()
                && Objects.equals(getLuxurySeats() == planeDTO.getLuxurySeats()
                && Objects.equals(getSpeedKnots() == planeDTO.getSpeedKnots()
                && Objects.equals(getRangeMiles() == planeDTO.getRangeMiles()
                && Objects.equals(getPlaneCallSign(), planeDTO.getPlaneCallSign())
                && Objects.equals(getModel(), planeDTO.getModel()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getPlaneCallSign(),
                getModel(), getSeatCapacity(),
                getLuxurySeats(), getSpeedKnots(),
                getRangeMiles()));
    }

    @Override
    public String toString() {
        return ("PlaneDTO{" +
                "PlaneCallSign='" + planeCallSign + '\'' +
                ", Model='" + model + '\'' +
                ", SeatCapacity=" + seatCapacity +
                ", LuxurySeats=" + luxurySeats +
                ", SpeedKnots=" + speedKnots +
                ", RangeMiles=" + rangeMiles +
                '}');
    }
}
