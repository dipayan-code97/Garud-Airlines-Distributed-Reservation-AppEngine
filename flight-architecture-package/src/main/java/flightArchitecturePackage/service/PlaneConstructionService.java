package flightArchitecturePackage.service;

import flightArchitecturePackage.model.Plane;
import flightArchitecturePackage.repository.PlaneConstructionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class PlaneConstructionService {

    @Autowired
    private final PlaneRepository PLANE_REPOSITORY;

    public PlaneConstructionService(PlaneRepository planeRepository) {
        this.PLANE_REPOSITORY = planeRepository;
    }

    public PlaneRepository getPLANE_REPOSITORY() {
        return this.PLANE_REPOSITORY;
    }

    public Set<Plane> findAllPlanes(){
        return PLANE_REPOSITORY.findAll();
    }

    public Plane findRandomPlane(){
        return PLANE_REPOSITORY.getRandomPlane();
    }

    public Plane findRandomPlaneByType(String planeType) {
        if (planeType.equals("any")){
            return PLANE_REPOSITORY.getRandomPlane();
        }
        return PLANE_REPOSITORY.getRandomPlaneByType(planeType);
    }

    public void saveAllPlanes(Collection<Plane> planes) {
        PLANE_REPOSITORY.saveAll(planes);
    }

    public Long findDatabaseRowCount() {
        return PLANE_REPOSITORY.count();
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        PlaneConstructionService that = (PlaneConstructionService) objectRef;
        return (Objects.equals(getPLANE_REPOSITORY(), that.getPLANE_REPOSITORY()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getPLANE_REPOSITORY());
    }

    @Override
    public String toString() {
        return ("PlaneConstructionService{" +
                "PLANE_REPOSITORY=" + PLANE_REPOSITORY +
                '}');
    }
}