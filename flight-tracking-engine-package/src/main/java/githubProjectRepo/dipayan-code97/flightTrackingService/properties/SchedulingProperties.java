package githubProjectRepo.dipayan-code97.flighttrackingservice.properties;

public class SchedulingProperties {

    public final String RUN_AT_MIDNIGHT = "0 0 0 * * *";

    public final String RUN_EVERY_HOUR = "0 0 * * * *";

    public final long ONE_MINUTE = 60_000L;

    public SchedulingProperties() {
    }
    public String getRunAtMidnight() {
        return this.RUN_AT_MIDNIGHT;
    }

    public String getRunEveryHour() {
        return this.RUN_EVERY_HOUR;
    }

    public long getOneMinute() {
        return this.ONE_MINUTE;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if ((object == null) || (getClass() != object.getClass())) return false;
        if (!super.equals(object)) return false;
        SchedulingProperties that = (SchedulingProperties) object;
        return (Objects.equals(getOneMinute() == that.getOneMinute()
                && Objects.equals(getRunAtMidnight(), that.getRunAtMidnight())
                && Objects.equals(getRunEveryHour(), that.getRunEveryHour()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(),
                getRunAtMidnight(),
                getRunEveryHour(),
                getOneMinute()));
    }

    @Override
    public String toString() {
        return ("SchedulingProperties{" +
                "RUN_AT_MIDNIGHT='" + RUN_AT_MIDNIGHT + '\'' +
                ", RUN_EVERY_HOUR='" + RUN_EVERY_HOUR + '\'' +
                ", ONE_MINUTE=" + ONE_MINUTE +
                '}');
    }
}
