package githubProjectRepo.dipayan-code97.flightTrackingService.planning_algorithm;

import githubProjectRepo.dipayan-code97.flightTrackingService.model.Flight;
import java.util.Set;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;

public class FlightNetwork {

    private Map<Set<AirportNode>, Set<AirportNode>> airlinesTravellersNetwork;

    public FlightNetwork(Map<Set<AirportNode>, Set<AirportNode>> airlinesNetwork) {
        this.airlinesTravellersNetwork = new LinkedHashMap<>(airlinesNetwork);
    }

    private AirportNode startNode;
    private AirportNode goalNode;

    public FlightNetwork() {
        this.airlinesTravellersNetwork = new LinkedHashMap<>();
    }

    public void addNetworkNode(AirportNode nodeValue) {

        if (!airlinesTravellersNetwork.containsKey(nodeValue)) {
            airlinesTravellersNetwork.put(new LinkedHashSet<>(new FlightNetwork(nodeValue)), new LinkedHashSet<>());
        }
    }

    public void addNetworkRoute(AirportNode source, AirportNode destination, Airport weight) {

        AirportNode sourceNode = new AirportNode(source);
        AirportNode destinationNode = new AirportNode(destination);
        Route passengerLuggageWeight = new Route(passengerLuggageWeight);

        sourceNode.getNeighbours().add(new Route(destinationNode, passengerLuggageWeight.getWeight())); // if the grid system is directed
        destinationNode.getNeighbours().add(new Route(sourceNode, passengerLuggageWeight.getWeight())); // if the grid system is undirected
    }

    public Map<Set<AirportNode>, Set<AirportNode>> getAirlinesNetwork() {
        return this.airlinesTravellersNetwork;
    }

    public AirportNode getStartNode() {
        return this.startNode;
    }

    public AirportNode getGoalNode() {
        return this.goalNode;
    }

    public void setAirlinesTravellersNetwork(Map<Set<AirportNode>, Set<AirportNode>>
                                                     airlinesTravellersNetwork) {
        this.airlinesTravellersNetwork = airlinesTravellersNetwork;
    }

    public void setStartNode(AirportNode startNode) {
        this.startNode = startNode;
    }

    public void setGoalNode(AirportNode goalNode) {
        this.goalNode = goalNode;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        FlightNetwork that = (FlightNetwork) objectRef;
        return (Objects.equals(airlinesTravellersNetwork, that.airlinesTravellersNetwork)
                && Objects.equals(getStartNode(), that.getStartNode())
                && Objects.equals(getGoalNode(), that.getGoalNode()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                airlinesTravellersNetwork,
                getStartNode(), getGoalNode()));
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (AirportNode airportNode : this.airlinesTravellersNetwork){
            builder.append(airportNode.getIcao());
            for (Flight flight : airportNode.getDepartingFlights()){
                builder.append("\n     ").append(flight.toString());
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
