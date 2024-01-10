package githubProjectRepo.dipayan-code97.flightTrackingService.model;

import jakarta.persistence.*;

@Embeddable
public class Route {

    @ManyToOne
    @JoinColumn(name = "departure_icao")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "destination_icao")
    private Airport destinationAirport;

    @Column(name = "flight_duration_hours")
    private Double flightDurationInHours;

    @Column(name = "flight_distance_miles")
    private Double flightDistanceInMiles;

    public Route() {

    }
    public Route(Airport departure, Airport destination, Plane plane) {
        this.departureAirport = departure;
        this.destinationAirport = destination;
        this.flightDistanceInMiles = getFlightMiles();
        this.flightDurationInHours = getFlightHours(plane);
    }

    public Airport getDepartureAirport() {
        return this.departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getDestinationAirport() {
        return this.destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Double getFlightDurationInHours() {
        return this.flightDurationInHours;
    }

    public void setFlightDurationInHours(Double flightDurationInHours) {
        this.flightDurationInHours = flightDurationInHours;
    }

    public Double getFlightDistanceInMiles() {
        return this.flightDistanceInMiles;
    }

    public void setFlightDistanceInMiles(Double flightDistanceInMiles) {
        this.flightDistanceInMiles = flightDistanceInMiles;
    }

    //uses haversine formula to find distance between coordinates
    private double getFlightMiles() {
        double departureLatitude = Math.toRadians(this.departureAirport.getLatitude());
        double departureLongitude = Math.toRadians(this.departureAirport.getLongitude());
        double destinationtLatitude = Math.toRadians(this.destinationAirport.getLatitude());
        double destinationLongitude = Math.toRadians(this.destinationAirport.getLongitude());
        double tangentialLatitudinalDistance = (departureLatitude - destinationtLatitude);
        double tangentialLongitudinalDistance = (departureLongitude - destinationLongitude);

        double airSpaceCoverage = (Math.pow(Math.sin(tangentialLatitudinalDistance / 2), 2))
                                    + Math.cos(departureLatitude) * Math.cos(destinationtLatitude)
                                    * (Math.pow(Math.sin(tangentialLongitudinalDistance / 2), 2));
        double coverage = (2 * Math.asin(Math.sqrt(airSpaceCoverage)));
        return Double.parseDouble(String.format("%.02f", coverage * 3958.8));
    }

    private double getFlightHours(Plane plane) {
        if (plane.getCruisingSpeedKnots() == 0) {
            throw new RuntimeException("Plane must have positive cruise speed");
        }
        double flightTime = (this.flightDistanceInMiles / knotsToMPH(plane.getCruisingSpeedKnots()));
        return Double.parseDouble(String.format("%.02f", flightTime));
    }

    private static double knotsToMPH(int knots) {
        return Double.parseDouble(String.format("%.02f", knots * 1.15078));
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        Route route = (Route) objectRef;
        return (Objects.equals(getDepartureAirport(), route.getDepartureAirport())
                && Objects.equals(getDestinationAirport(), route.getDestinationAirport())
                && Objects.equals(getFlightDurationInHours(), route.getFlightDurationInHours())
                && Objects.equals(getFlightDistanceInMiles(), route.getFlightDistanceInMiles());
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getDepartureAirport(),
                getDestinationAirport(), getFlightDurationInHours(),
                getFlightDistanceInMiles()));
    }

    @Override
    public String toString() {
        return ("Route{" +
                "DepartureAirport=" + departureAirport +
                ", DestinationAirport=" + destinationAirport +
                ", FlightDurationInHours=" + flightDurationInHours +
                ", FlightDistanceInMiles=" + flightDistanceInMiles +
                '}');
    }
}
