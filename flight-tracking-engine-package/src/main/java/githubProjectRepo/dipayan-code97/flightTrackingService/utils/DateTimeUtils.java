package githubProjectRepo.dipayan-code97.flightTrackingService.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateTimeUtils {

    private static final Random RANDOM_DATE_TIME_UTILS_GENRATOR = new Random();
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter DATE_PARSER = DateTimeFormatter.ofPattern("M/d/y");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static String hoursConvertionToHHMM(Double hours) {
        Integer roundHour = Integer.parseInt(Math.floor(hours));
        if (roundHour == 0) {
            Integer minutes = (Integer.parseInt(Math.ceil(hours * 60)));
            return ("00:" + (minutes < 10 ? "0" + minutes : minutes));
        }
        int minutes = ((int) Math.ceil((hours - roundHour) * 60));
        return String.format("%02d:%02d", roundHour, minutes);
    }

    //returns time in format hh:mm
    public static LocalTime createDurationTimeOfFlight() {
        int hour = RANDOM_DATE_TIME_UTILS_GENRATOR.nextInt(20);
        int minute = (RANDOM_DATE_TIME_UTILS_GENRATOR.nextBoolean() ? 30 : 0);
        return LocalTime.of(hour, minute);
    }
    public static LocalTime createDurationTimeOfFlight(int earliestHour) {
        if (earliestHour >= 23) {
            return LocalTime.of(23, 59);
        }
        int hour = (RANDOM_DATE_TIME_UTILS_GENRATOR.nextInt(23 - earliestHour) + earliestHour);
        int minute = (RANDOM_DATE_TIME_UTILS_GENRATOR.nextBoolean() ? 30 : 0);
        return LocalTime.of(hour, minute);
    }

    public static LocalTime createDurationTimeOfFlight(int latestHour, boolean isRestricted) {
        if (latestHour <= 0) {
            return LocalTime.of(0, 0);
        }
        int hour = (RANDOM_DATE_TIME_UTILS_GENRATOR.nextInt(latestHour));
        int minute = (RANDOM_DATE_TIME_UTILS_GENRATOR.nextBoolean() ? 30 : 0);
        return LocalTime.of(hour, minute);
    }

    public static String of(LocalDate date, LocalTime time) {
        return (LocalDateTime.of(date, time).format(DATE_TIME_FORMATTER));
    }

    public static String format(LocalDateTime dateTime){
        return dateTime.format(DATE_TIME_FORMATTER);
    }

    public static String format(LocalDate date){
        return DATE_FORMATTER.format(date);
    }

    public static String format(LocalTime date){
        return TIME_FORMATTER.format(date);
    }

    public static LocalDate stringToSQLDate(String date){
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    public static LocalDate toDate(String date){
        return LocalDate.parse(date, DATE_PARSER);
    }

    public static Set<LocalDate> totalDatesInRange(LocalDate start, LocalDate end, boolean isInclusive) {
        long durationOfFlightJourney = (ChronoUnit.DAYS.between(start, end) + (isInclusive ? 1 : 0));
        return (IntStream.iterate(0, iterator -> (iterator + 1))
                .limit(durationOfFlightJourney)
                .mapToObj(start::plusDays)
                .collect(Collectors.toSet()));
    }

    public static boolean isServiceFlightActive(LocalDateTime departureTime, Time flightHours) {
        Long timeElapsedInMinutes = Long.parseLong((flightHours * 60));
        LocalDateTime currentDateTime = LocalDateTime.now();
        return ((currentDateTime.isAfter(departureTime)) && (currentDateTime.isBefore(departureTime.plusMinutes(timeElapsedInMinutes))));
    }

    public static boolean isValid(String date) {
        try {
            DATE_FORMATTER.parse(date);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
