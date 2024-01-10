package flightArchitecturePackage.controller;

import flightArchitecturePackage.exception.FlightGeneratorException;
import flightArchitecturePackage.model.Airport;
import flightArchitecturePackage.model.Plane;
import flightArchitecturePackage.service.requestService.FlightRequest;
import flightArchitecturePackage.service.responseService.FlightResponse;
import flightArchitecturePackage.service.AirportService;
import flightArchitecturePackage.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5501","http://127.0.0.1:5500"})
@RequestMapping(value = "/api/v1/flight")
public class FlightConstructionController {

    @Autowired
    private final PlaneService PLANE_SERVICE;
    @Autowired
    private final AirportService AIRPORT_SERVICE;

    public FlightConstructionController(PlaneService planeService,
                                        AirportService airportService) {
        this.PLANE_SERVICE = planeService;
        this.AIRPORT_SERVICE = airportService;
    }

    public PlaneService getPlaneService() {
        return this.PLANE_SERVICE;
    }

    public AirportService getAirportService() {
        return this.AIRPORT_SERVICE;
    }

    @GetMapping(value = "/random")
    public FlightResponse createRandomFlight() {
        return new FlightResponse(
                AIRPORT_SERVICE.findRandomAirport(),
                AIRPORT_SERVICE.findRandomAirport(),
                PLANE_SERVICE.findRandomPlane());
    }
    
    //Flight Request parameter includes max time and type of plane included in flight
    @PostMapping(value = "/custom")
    public FlightResponse createFlightWithParameters(@RequestBody FlightRequest flightRequest) {
        if (!flightRequest.isAValidPlaneType()){
            throw new FlightGeneratorException("Plane Type Does Not Exist");
        }
        Plane plane = PLANE_SERVICE.findRandomPlaneByType(flightRequest.getPlaneType());
        Set<Airport> airports = AIRPORT_SERVICE.findAirportsWithinMaxHours(
                flightRequest,
                plane.getSpeedInKnots().toSet());
        //this constructor automatically calculates flight time/distance
        return new FlightResponse(airports.get(0), airports.get(1), plane);
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        FlightConstructionController that = (FlightConstructionController) objectRef;
        return (Objects.equals(getPlaneService(), that.getPlaneService())
                && Objects.equals(getAirportService(), that.getAirportService()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getPlaneService(), getAirportService()));
    }

    @Override
    public String toString() {
        return ("FlightConstructionController{" +
                "PLANE_SERVICE=" + PLANE_SERVICE +
                ", AIRPORT_SERVICE=" + AIRPORT_SERVICE +
                '}');
    }
}