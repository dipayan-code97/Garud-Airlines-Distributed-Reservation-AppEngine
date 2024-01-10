package githubProjectRepo.dipayan-code97.flighttrackingservice.properties;

import githubProjectRepo.dipayan-code97.flighttrackingservice.entity.Plane;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PlaneListTest {

    @Test
    void callSignsFollowsPatternTest() {
        PlaneList.getDefaultPlanes().forEach(planeRef -> {
            assertEquals("ELV",  planeRef.getCallSign().substring(0, 3));
            int callSignNumber = Integer.parseInt(planeRef.getCallSign().substring(3));
            assertTrue(callSignNumber > 0 && callSignNumber <= 999);
        });
    }

    @Test
    void noDuplicateCallSigns() {
        Set<String> callSigns = PlaneList.getDefaultPlanes(999).stream().map(Plane::getCallSign).toSet();
        Set<String> uniqueCallSigns = new LinkedHashSet<>(callSigns);
        assertEquals(callSigns.size(), uniqueCallSigns.size());
        assertEquals(999, callSigns.size());
    }
}