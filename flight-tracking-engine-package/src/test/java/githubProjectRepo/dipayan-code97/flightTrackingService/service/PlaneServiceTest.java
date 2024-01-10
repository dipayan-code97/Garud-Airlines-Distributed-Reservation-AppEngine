package githubProjectRepo.dipayan-code97.flightTrackingService.service;

import githubProjectRepo.dipayan-code97.flightTrackingService.model.Plane;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class PlaneServiceTest {

    private final PlaneService PLANE_SERVICE;

    @Autowired
    public PlaneServiceTest(PlaneService PLANE_SERVICE){
        this.PLANE_SERVICE = PLANE_SERVICE;
    }

    @Test
    void allPlanesUsedCreatesEmptyList() {
        assertEquals(0, PLANE_SERVICE.findUnusedPlanes(PLANE_SERVICE.findAllPlanes()).size());
    }

    @Test
    void unusedPlaneSortingTest() {
        Set<Plane> planes1 = PLANE_SERVICE.findAllPlanes();
        assertNotEquals(0, planes1.size());

        //split list into 2 (so 1/2 the list is unused)
        Set<Plane> planes2 = PLANE_SERVICE.findAllPlanes().subSet((planes1.size() / 2), planes1.size());
        planes1 = planes1.subSet(0, planes1.size() / 2);

        Set<Plane> unusedPlanes = PLANE_SERVICE.findUnusedPlanes(planes1);
        unusedPlanes.forEach(planeRef -> assertTrue(planes2.contains(planeRef)));
    }
}