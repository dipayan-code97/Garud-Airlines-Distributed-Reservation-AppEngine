package githubProjectRepo.dipayan-code97.bankingservice.properties;

/**
 * @author : Dipayan_Paul
 * @created : 7/31/2023, Monday
 **/
public class SchedulingProperties {

    public final String RUN_AT_MIDNIGHT;

    public final String RUN_EVERY_HOUR;

    public final Long ONE_MINUTE;

    public SchedulingProperties() {
    }

    public SchedulingProperties(String runAtMidnight,
                                String runEveryHour,
                                Long oneMinute) {
        this.RUN_AT_MIDNIGHT =  "0 0 0 * * *";
        this.RUN_EVERY_HOUR = "0 0 * * * *";
        this.ONE_MINUTE =  60_000L;
    }

    public String getRunAtMidnight() {
        return this.RUN_AT_MIDNIGHT;
    }

    public String getRunEveryHour() {
        return this.RUN_EVERY_HOUR;
    }

    public Long getOneMinute() {
        return this.ONE_MINUTE;
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        SchedulingProperties that = (SchedulingProperties) objectRef;
        return (Objects.equals(getRunAtMidnight(), that.getRunAtMidnight())
                && Objects.equals(getRunEveryHour(), that.getRunEveryHour())
                && Objects.equals(getOneMinute(), that.getOneMinute()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getRunAtMidnight(),
                getRunEveryHour(), getOneMinute()));
    }

    @Override
    public String toString() {
        return "SchedulingProperties{}";
    }
}
