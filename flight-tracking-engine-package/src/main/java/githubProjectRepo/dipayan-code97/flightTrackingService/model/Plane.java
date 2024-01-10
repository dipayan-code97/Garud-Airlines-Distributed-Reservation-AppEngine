import githubProjectRepo.dipayan-code97.flightTrackingService.model;

public class Plane {

    private String model;
    private int seatingCapacity;
    private int luxurySeats;
    private int cruisingSpeedKnots;
    private int rangeMiles;

    public Plane(String model, int seatingCapacity,
                 int luxurySeats, int cruisingSpeedKnots,
                 int rangeMiles) {
        this.model = model;
        this.seatingCapacity = seatingCapacity;
        this.luxurySeats = luxurySeats;
        this.cruisingSpeedKnots = cruisingSpeedKnots;
        this.rangeMiles = rangeMiles;
    }

    public Plane() {

    }
    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeatingCapacity() {
        return this.seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public int getLuxurySeats() {
        return this.luxurySeats;
    }

    public void setLuxurySeats(int luxurySeats) {
        this.luxurySeats = luxurySeats;
    }

    public int getCruisingSpeedKnots() {
        return this.cruisingSpeedKnots;
    }

    public void setCruisingSpeedKnots(int cruisingSpeedKnots) {
        this.cruisingSpeedKnots = cruisingSpeedKnots;
    }

    public int getRangeMiles() {
        return this.rangeMiles;
    }

    public void setRangeMiles(int rangeMiles) {
        this.rangeMiles = rangeMiles;
    }


    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        PlaneModel that = (PlaneModel) objectRef;
        return (Objects.equals(getSeatingCapacity() == that.getSeatingCapacity()
                && Objects.equals(getLuxurySeats() == that.getLuxurySeats())
                && Objects.equals(getCruisingSpeedKnots() == that.getCruisingSpeedKnots())
                && Objects.equals(getRangeMiles() == that.getRangeMiles()
                && Objects.equals(getModel(), that.getModel()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getModel(),
                getSeatingCapacity(), getLuxurySeats(),
                getCruisingSpeedKnots(), getRangeMiles()));
    }

    @Override
    public String toString() {
        return ("PlaneModel{" +
                "model='" + model + '\'' +
                ", SeatingCapacity=" + seatingCapacity +
                ", LuxurySeats=" + luxurySeats +
                ", CruisingSpeedKnots=" + cruisingSpeedKnots +
                ", RangeMiles=" + rangeMiles +
                '}');
    }
}