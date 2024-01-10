package githubProjectRepo.dipayan-code97.flighttrackingservice.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity(name = "scheduledRoute")
@Table(name = "scheduled_routes")
public class FlightRouteScheduler {

    @Id
    @Column(name = "call_sign_id")
    private String callSign;

    @OneToOne
    @JoinColumn(name = "call_sign")
    private Plane plane;

    @Embedded
    private Route route;

    @Column(name = "time_of_flight")
    private LocalTime time;

    public FlightRouteScheduler(Plane plane, Route route, LocalTime time){
        this.callSign = plane.getCallSign();
        this.plane = plane;
        this.route = route;
        this.time = time;
    }

    public String getCallSign() {
        return this.callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
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

    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isPlaneScheduled(List<ScheduledRoute> currentRoutes){
        return currentRoutes.stream().anyMatch(route -> route.getPlane().equals(this.getPlane()));
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        ScheduledRoute that = (ScheduledRoute) objectRef;
        return (Objects.equals(getCallSign(), that.getCallSign())
                && Objects.equals(getPlane(), that.getPlane())
                && Objects.equals(getRoute(), that.getRoute())
                && Objects.equals(getTime(), that.getTime()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getCallSign(),
                getPlane(), getRoute(), getTime()));
    }

    @Override
    public String toString() {
        return ("ScheduledRoute{" +
                "CallSign='" + callSign + '\'' +
                ", Plane=" + plane +
                ", Route=" + route +
                ", Time=" + time +
                '}');
    }
}
