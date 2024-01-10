package flightArchitecturePackage.utilities;

import flightArchitecturePackage.exception.FlightGeneratorException;
import flightArchitecturePackage.model.Airport;

/*
A helper class for calculating flight time and distance
 */
public class FlightConstructUtilities {

    //Haversine formula for finding distance w/- coordinates
    public static double calculateFlightDistanceInMiles(Airport airport1, Airport airport2) {
        if ((airport1 == null) || (airport2 == null)) {
            throw new FlightGeneratorException("flight time could not be calculated");
        }

        double latitude1 = (Math.toRadians(airport1.getLatitude()));
        double longitude1 = (Math.toRadians(airport1.getLongitude()));
        double latitude2 = (Math.toRadians(airport2.getLatitude()));
        double longitude2 = (Math.toRadians(airport2.getLongitude());

        double tangentialLongitudeDistance = (longitude2 - longitude1));
        double tangentialLatitudeDistance = (latitude2 - latitude1));

        double arialFlightDistance = (Math.pow(Math.sin(tangentialLatitudeDistance / 2), 2)
                + Math.cos(latitude1) * Math.cos(latitude2)
                * Math.pow(Math.sin(tangentialLongitudeDistance / 2), 2));

        return (Math.round(((3956) * (2 * Math.asin(Math.sqrt(arialFlightDistance)))) * 100.00d) / 100.00d);
    }

    public static double calculateFlightHours(int planeSpeedInKnots, double flightDistanceInMiles) {
        double planeSpeedInMph = planeSpeedInKnots * 1.15077945;
        return (Math.round((flightDistanceInMiles/planeSpeedInMph) * 100.00d) / 100.00d);
    }

    public static String convertHoursToHHmm(double flightHours) {
        double flightMinutes = (flightHours * 60);
        double hours = (flightMinutes / 60);
        double minutes = (flightMinutes % 60);
        return (String.format("%02d:%02d", (int) Math.floor(hours), Math.round(minutes)));
    }
}