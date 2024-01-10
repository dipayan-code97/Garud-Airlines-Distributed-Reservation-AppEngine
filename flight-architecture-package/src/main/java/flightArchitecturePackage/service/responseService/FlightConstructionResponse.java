package flightArchitecturePackage.model.request;

import flightArchitecturePackage.entity.PlaneCategory;
/*
This class is used to parse the JSON returned from flightGenController class when
receiving a flight that includes specific parameters
 */

public class FlightConstructionRequest {

    private String maxFlightHours;
    private String planeType;

    public FlightConstructionRequest(String maxFlightHours, String planeType) {
        this.maxFlightHours = maxFlightHours;
        this.planeType = planeType;
    }

    public String getMaxFlightHours() {
        return this.maxFlightHours;
    }

    public void setMaxFlightHours(String maxFlightHours) {
        this.maxFlightHours = maxFlightHours;
    }

    public String getPlaneType() {
        return this.planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public boolean isAValidPlaneType() {
        try {
            PlaneType.valueOf(this.planeType.toUpperCase());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isValidFlight(double maxFlightHours) {
        if (this.maxFlightHours.equals("any")) {
            return true;
        }
        return (Double.parseDouble(this.maxFlightHours) > maxFlightHours);
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        FlightConstructionRequest that = (FlightConstructionRequest) objectRef;
        return (Objects.equals(getMaxFlightHours(), that.getMaxFlightHours())
                && Objects.equals(getPlaneType(), that.getPlaneType()));
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getMaxFlightHours(), getPlaneType());
    }

    @Override
    public String toString() {
        return ("FlightConstructionRequest{" +
                "MaxFlightHours='" + maxFlightHours + '\'' +
                ", PlaneType='" + planeType + '\'' +
                '}');
    }
}