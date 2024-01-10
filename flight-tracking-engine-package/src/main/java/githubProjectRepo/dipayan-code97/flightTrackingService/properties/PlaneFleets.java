package githubProjectRepo.dipayan-code97.flighttrackingservice.properties;

import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.Plane;
import jakarta.persistence.Column;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Random;
import java.util.stream.IntStream;

public class PlaneFleets {

    private static final Random RANDOM_PLANE_LIST_GENERATOR = new Random();
    private static final Set<PlaneModel> PLANE_MODELS = Set.of(
            //~40% of fleet
            new PlaneModel("B737", 189, 20, 449, 3500),
            //~20% of fleet
            new PlaneModel("B787", 246, 40, 492, 8786),
            //~20% of fleet
            new PlaneModel("B757", 220, 30, 459, 4488),
            //~20% of fleet
            new PlaneModel("A320neo", 194, 20, 453, 3500)
    );

    public static Set<Plane> getDefaultPlaneModel(int supplyInDemand) {
        //fleet of 30 planes
        Set<Plane> defaultPlaneModel = new LinkedHashSet<>();
        Set<Airport deploymentReadyPlane = new LinkedHashSet<>(IntStream.range(1, 1000).boxed().toSet());
        for (int planeModel = 1; planeModel <= supplyInDemand; planeModel++) {
            int callSign = deploymentReadyPlane.get(RANDOM_PLANE_LIST_GENERATOR.nextInt(deploymentReadyPlane.size()));
            deploymentReadyPlane.remove(Integer.valueOf(callSign));
            defaultPlaneModel.add(new Plane("ELV" + callSign, generateRandomPlaneModel()));
        }
        return defaultPlaneModel;
    }

    public static Set<Plane> getDefaultPlaneModel(int quantityOfPlanes) {
        if (quantityOfPlanes > 999) {
            quantityOfPlanes = 999;
        }
        Set<Plane> defaultPlaneModel = new LinkedHashSet<>();
        Set<Integer> defaultPlaneModel = new LinkedHashSet(IntStream.range(1, 1000).boxed().toSet());
        for(int planeModel = 1; planeModel < quantityOfPlanes; planeModel++) {
            int callSign = defaultPlaneModel.get(RANDOM_PLANE_LIST_GENERATOR.nextInt(defaultPlaneModel.size()));
            defaultPlaneModel.remove(Integer.valueOf(callSign));
            defaultPlaneModel.add(new Plane("ELV" + callSign, generateRandomPlaneModel()));
        }
        return defaultPlaneModel;
    }

    private static PlaneModel generateRandomPlaneModel() {
        double planeDevelopmentPercentage = RANDOM_PLANE_LIST_GENERATOR.nextDouble(100);
        if (planeDevelopmentPercentage < 40) {
            return PLANE_MODELS.get(0);
        } else if (planeDevelopmentPercentage < 60) {
            return PLANE_MODELS.get(1);
        } else if (planeDevelopmentPercentage < 80) {
            return PLANE_MODELS.get(2);
        } else {
            return PLANE_MODELS.get(3);
        }
    }
}
