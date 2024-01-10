package githubProjectRepo.dipayan-code97.flightTrackingService.dto;

import githubProjectRepo.dipayan-code97.flightTrackingService.entity.Plane;

public class PlaneSummaryDTO {

    private String callSign;
    private String model;

    public PlaneSummaryDTO(Plane plane){
        this.callSign = plane.getCallSign();
        this.model = plane.getModel();
    }

    public String getCallSign() {
        return this.callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        PlaneSummaryDTO that = (PlaneSummaryDTO) objectRef;
        return (Objects.equals(getCallSign(), that.getCallSign())
                && Objects.equals(getModel(), that.getModel()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getCallSign(), getModel()));
    }

    @Override
    public String toString() {
        return ("PlaneSummaryDTO{" +
                "CallSign='" + callSign + '\'' +
                ", Model='" + model + '\'' +
                '}');
    }
}
