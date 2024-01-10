package flightArchitecturePackage.utility;

import flightArchitecturePackage.model.Airport;
import flightArchitecturePackage.model.Plane;
import flightArchitecturePackage.service.AirportService;
import flightArchitecturePackage.service.PlaneService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.util.stream.Collectors;

/*
A helper class for saving airport, plane, landmark
and route data from csv format to a MySQL database
 */
public class CSVConvertionDatabaseUtils {

    public static Set<Plane> savePlanesFromCsv(File planeDataFile) throws IOException {
        return (Files.lines(planeDataFile.toPath()).map(planeRef -> {
            planeRef = planeRef.replace("\"", "");
            String[] planeColumns = planeRef.split(",");
            return new Plane(
                    planeColumns[0],
                    planeColumns[4],
                    //save speed as integer
                    Integer.parseInt(planeColumns[1]),
                    //save range as integer
                    Integer.parseInt(planeColumns[2]),
                    planeColumns[3]);
        }).collect(Collectors.toSet()));
    }

    public static Set<Airport> convertCsvToAirports(File planeDataFile) throws IOException {
        return (Files.lines(planeDataFile.toPath())
                .map(airportRef -> {
                    airportRef = airportRef.replace("\"", "");
                    String[] airportColumns = airportRef.split(",");
                    return new Airport(
                            airportColumns[0],
                            airportColumns[1],
                            airportColumns[2],
                            //save latitude as double
                            Double.parseDouble(airportColumns[3]),
                            //save longitude as double
                            Double.parseDouble(airportColumns[4]),
                            airportColumns[5],
                            airportColumns[6]);
                }).collect(Collectors.toSet()));
    }
}