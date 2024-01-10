package flightArchitecturePackage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "plane")
@Table(name = "plane")
public class Plane {

    @Id
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "speed_in_knots")
    private Integer speedInKnots;

    @Column(name= "range_in_miles")
    private Integer rangeInKnots;

    @Column(name = "standard_edition")
    private String standardEdition;

    public Plane(String name,
                 String type,
                 int speedInKnots,
                 int rangeInKnots,
                 String standardEdition) {
        this.name = name;
        this.type = type;
        this.speedInKnots = speedInKnots;
        this.rangeInKnots = rangeInKnots;
        this.standardEdition = standardEdition;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSpeedInKnots() {
        return this.speedInKnots;
    }

    public void setSpeedInKnots(Integer speedInKnots) {
        this.speedInKnots = speedInKnots;
    }

    public Integer getRangeInKnots() {
        return this.rangeInKnots;
    }

    public void setRangeInKnots(Integer rangeInKnots) {
        this.rangeInKnots = rangeInKnots;
    }

    public String getStandardEdition() {
        return this.standardEdition;
    }

    public void setStandardEdition(String standardEdition) {
        this.standardEdition = standardEdition;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        Plane plane = (Plane) objectRef;
        return (Objects.equals(getName(), plane.getName())
                && Objects.equals(getType(), plane.getType())
                && Objects.equals(getSpeedInKnots(), plane.getSpeedInKnots())
                && Objects.equals(getRangeInKnots(), plane.getRangeInKnots())
                && Objects.equals(getStandardEdition(), plane.getStandardEdition()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getName(),
                getType(), getSpeedInKnots(),
                getRangeInKnots(), getStandardEdition()));
    }

    @Override
    public String toString() {
        return ("Plane{" +
                "Name='" + name + '\'' +
                ", Type='" + type + '\'' +
                ", SpeedInKnots=" + speedInKnots +
                ", RangeInKnots=" + rangeInKnots +
                ", StandardEdition='" + standardEdition + '\'' +
                '}');
    }
}