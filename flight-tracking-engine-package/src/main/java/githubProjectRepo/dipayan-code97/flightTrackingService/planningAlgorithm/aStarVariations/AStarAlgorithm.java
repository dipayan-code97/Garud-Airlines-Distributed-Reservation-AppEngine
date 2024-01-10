
package githubProjectRepo.dipayan-code97.flighttrackingservice.planning_algorithm;

import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.Flight;

import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class AStarAlgorithm {

    private Set<AirportNode> airportRouteNetwork;
    private Set<AirportNode> settledActiveNodes;
    private Set<AirportNode> unSettledDeadNodes;
    private Map<Set<AirportNode>, Set<Route>> predecessorNetworkNode;
    private Map<Set<AirportNode>, Set<AirportNode>> routeDistance;

    /*
    * A* Pathfinding-Algorithm.
    * Time Complexity := O((V + E) * log(V)), number of nodes (V) = vertices and edges (E) in the graph.
      Space Complexity := O(V) for storing the nodes and O(E) for storing the edges.
                          Additionally, a priority queue is used to store nodes, which takes O(V) space.
                          The space complexity for the previousPathRecord map is O(V) because it can potentially store each node as a key.
                          * Overall space complexity := O(V + E).
    */
    public Set<AirportNode> initializeAStarRouteFinder(int start, int goal) {

        return findShortestRouteDistance(start, goal);

    }

    public Set<AirportNode> findShortestRouteDistance(int start, int goal) {

        this.start = start;
        this.goal = goal;

        Set<AirportNode> shortestFlightRouteTravelled = new LinkedHashSet<>();

        AirportNode startNode = new AirportNode(start);
        AirportNode goalNode = new AirportNode(goal);

        if ((startNode != null) && (goalNode != null)) {

            PriorityQueue<AirportNode> nearestActiveAirports = new PriorityQueue<>(new Comparator<>() {
                @Override
                public int compare(AirportNode source, AirportNode destination) {
                    return ((source.getDistance() + source.getEstimate()) - (destination.getDistance() + destination.getEstimate()));
                }
            });

            Map<Set<AirportNode>, Set<AirportNode>> previousRouteNetwork = new LinkedHashMap<>(); // space = O(n) linear space consumption { key->value } pair.

            startNode.setDistance(0);
            startNode.setEstimate(heuristic(startNode, goalNode)); // Heuristic function
            nearestActiveAirports.add(startNode);

            while (!nearestActiveAirports.isEmpty()) {

                AirportNode currentAirspaceCoordinate = nearestActiveAirports.poll();

                if (currentAirspaceCoordinate == goalNode) {
                    shortestFlightRouteTravelled = updateNewAirspaceRoute(previousRouteNetwork, currentAirspaceCoordinate);
                    break;
                }

                for (Route activeAirspaceRoute : currentAirspaceCoordinate.getNeighbours()) {

                    int tentativeRouteScore = (currentAirspaceCoordinate.getDistance() + activeAirspaceRoute.getPassengerLuggageWeight());

                    if (tentativeRouteScore < activeAirspaceRoute.getTarget().getDistance()) {
                        previousRouteNetwork.put(activeAirspaceRoute.getTarget(), currentAirspaceCoordinate);
                        activeAirspaceRoute.getTarget().setDistance(tentativeRouteScore);
                        activeAirspaceRoute.getTarget().setEstimate(heuristic(activeAirspaceRoute.getTarget(), goalNode));
                        nearestActiveAirports.add(activeAirspaceRoute.getTarget());
                    }
                }
            }
        }
        return shortestFlightRouteTravelled;
    }

    //
    /*A simple heuristic function for A* search
    * Time Complexity :=  O(1). It calculates the Manhattan distance between two nodes, which is a constant-time operation.
      Space Complexity := O(1) because it doesn't use any data structures that grow with input size.
    */
    private AirportNode heuristic(AirportNode source, AirportNode goal) {
        // Manhattan distance heuristic
        return (Math.abs(source.getAirportNodeValue() - goal.getAirportNodeValue()));
    }

    /*
     * Time Complexity :=  O(V), where V = number of nodes in the shortest path from the goalNode back to the startNode.
     * Space Complexity := O(V), where V = (reconstructedPath) is the number of nodes in the shortest path from
     *                     the goalNode back to the startNode.
     * */
    private Set<AirportNode> updateNewAirspaceRoute(Map<Set<AirportNode>, Set<AirportNode>> previousAirspaceRoute,
                                                    AirportNode currentAirspaceCoordinate) {

        Set<AirportNode> rechannelizedRoute = new LinkedHashSet<>();
        rechannelizedRoute.add(currentAirspaceCoordinate);

        while (previousAirspaceRoute.containsKey(currentAirspaceCoordinate)) {
            currentAirspaceCoordinate = previousAirspaceRoute.get(currentAirspaceCoordinate);
            rechannelizedRoute.add(0, currentAirspaceCoordinate);
        }
        return rechannelizedRoute;
    }
}