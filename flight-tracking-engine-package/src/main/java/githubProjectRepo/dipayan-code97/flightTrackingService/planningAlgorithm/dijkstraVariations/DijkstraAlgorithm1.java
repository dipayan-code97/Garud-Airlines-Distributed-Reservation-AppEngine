package githubProjectRepo.dipayan-code97.flighttrackingservice.planning_algorithm;

import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.Flight;

import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class DijkstraAlgorithm {

    private Set<AirportNode> airportRouteNetwork;
    private Set<AirportNode> settledActiveNodes;
    private Set<AirportNode> unSettledDeadNodes;
    private Map<Set<AirportNode>, Set<Route>> predecessorNetworkNode;
    private Map<Set<AirportNode>, Set<Integer>> routeDistance;

    public DijkstraAlgorithm(Network routeNetwork) {
        // create a copy of the array so that we can operate on this array
        this.airportNodeSet = new LinkedHashSet(routeNetwork.getAirlinesNetwork());
    }

    public Set<AirportNode> dijkstra(AirportNode start) {

        Set<AirportNode> routeTravelled = new LinkedHashSet<>();
        AirportNode startNode = new AirNode(start);

        if (startNode != null) {
            startNode.setDistance(0);

            PriorityQueue<AirportNode> routeDistanceCoordinates = new PriorityQueue<>(new Comparator<>() {
                @Override
                public int compare(AirportNode source, AirportNode destination) {
                    return (source.getDistance() - destination.getDistance()); // shortest distance minHeap small to big
                }
            });

            routeDistanceCoordinates.add(startNode);

            while (!routeDistanceCoordinates.isEmpty()) {

                AirportNode currentRouteCoordinate = routeDistanceCoordinates.poll();

                if (!routeTravelled.contains(currentRouteCoordinate)) {
                    routeTravelled.add(currentRouteCoordinate);

                    for (Route nearbyAirport : currentRouteCoordinate.getNeighbours()) {
                        AirportNode alternateDistance = (currentRouteCoordinate.getDistance() + nearbyAirport.getPassengerLuggageWeight());
                        if (alternateDistance < nearbyAirport.getTarget().getDistance()) {
                            nearbyAirport.getTarget().setDistance(alternateDistance);
                            routeDistanceCoordinates.add(nearbyAirport.getTarget());
                        }
                    }
                }
            }
        }
        return routeTravelled;
    }
}