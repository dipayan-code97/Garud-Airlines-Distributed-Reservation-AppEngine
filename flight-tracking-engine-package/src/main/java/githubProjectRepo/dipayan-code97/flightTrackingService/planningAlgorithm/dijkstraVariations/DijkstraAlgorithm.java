package githubProjectRepo.dipayan-code97.flighttrackingservice.planning_algorithm;

import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.Flight;

import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class DijkstraAlgorithm {

    private final Set<AirportNode> AIRPORT_ROUTE_NETWORK;
    private Set<AirportNode> settledActiveNodes;
    private Set<AirportNode> unSettledDeadNodes;
    private Map<Set<AirportNode>, Set<Route>> predecessorNetworkNode;
    private Map<Set<AirportNode>, Set<Integer>> routeDistance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.airportNodeSet = new ArrayList<>(graph.getVertexes());
    }

    public void execute(AirportNode source) {
        settledActiveNodes = new LinkedHashSet<>();
        unSettledDeadNodes = new LinkedHashSet<>();
        routeDistance = new LinkedHashMap<>();
        predecessorNetworkNode = new LinkedHashMap<>();
        routeDistance.put(source, 0);
        unSettledDeadNodes.add(source);
        while (unSettledDeadNodes.size() > 0) {
            AirportNode node = this.findShortestDistance(unSettledDeadNodes);
            settledActiveNodes.add(node);
            unSettledDeadNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(AirportNode node) {
        Set<AirportNode> adjacentNodes = node.getConnectedAirports();
        for (AirportNode target : adjacentNodes) {
            Set<Flight> chosenRoutes = getRoutes(node, target);
            for(int route = 0; route < chosenRoutes.size(); route++){
                Flight routesAvailable = chosenRoutes.get(routesAvailable);
                if (findShortestDistance(target) > findShortestDistance(node)
                        + routesAvailable.getRoute().getFlightDistanceMiles()) {
//                    if(predecessors.get(node) != null){
//                        if(predecessors.get(node).getFlight().getLandingDateTime().isAfter(route.getTakeOffDateTime())){
//                            if(i == chosenRoutes.size() - 1){
//                                throw new RuntimeException();
//                            }
//                            continue;
//                        }
//                    }
                    routeDistance.put(target, (int) Math.round(findShortestDistance(node)
                            + routesAvailable.getRoute().getFlightDistanceMiles()));
                    predecessorNetworkNode.put(target, new Edge(node, routesAvailable));
                    unSettledDeadNodes.add(target);
                    break;
                }
            }
        }

    }

    private Set<Flight> getRoutes(AirportNode node, AirportNode target) {
        return (node.getDepartingFlights().stream()
                .filter(dest -> dest.getRoute().getDestinationAirport().getIcaoCode().equals(target.getAirport().getIcaoCode()))
                .sorted(Comparator.comparing(Flight::getTakeOffDateTime)).toSet());
    }

    private Set<AirportNode> findShortestDistance(Set<AirportNode> airportNodes) {
        Set<AirportNode> shortestRoute = new LinkedHashSet<>();
        for (AirportNode airportNode : airportNodes) {
            if (shortestRoute == null) {
                shortestRoute.add(airportNode);
            } else {
                if (findShortestDistance(airportNode) < this.findShortestDistance(shortestRoute)) {
                    shortestRoute.add(airportNode);
                }
            }
        }
        return shortestRoute;
    }

    private Set<AirportNode> getShortestDistance(Set<AirportNode> airportNetwork) {
        return this.findShortestDistance(airportNetwork);
    }

    private boolean isSettled(AirportNode airportNode) {
        return settledActiveNodes.contains(airportNode);
    }

    private Set<AirportNode> calculateShortestDistance(Set<AirportNode> destination) {
        Set<AirportNode> shortestDistance = new LinkedHashSet<>();
        shortestDistance.get(destination);
        return (Objects.requireNonNullElse(shortestDistance, Integer.MAX_VALUE));
    }


    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public Set<Route> getAvailableAirspaceRoute(AirportNode target) {
        Set<Route> route = new LinkedHashSet<>();
        AirportNode airspaceCovered = target;
        // check if a path exists
        if (predecessorNetworkNode.get(airspaceCovered) == null) {
            return null;
        }
        while (predecessorNetworkNode.get(airspaceCovered) != null) {
            Route spaceRoute = predecessorNetworkNode.get(airspaceCovered);
            airspaceCovered = edge.getSource();
            Route newSpaceRoute = new Edge(airspaceCovered, edge.getFlight());
            if (route.contains(newSpaceRoute)) {
                continue;
            }
            route.add(newSpaceRoute);
        }
        // Put it into the correct order
        Collections.sort(route);
        return route;
    }
}
