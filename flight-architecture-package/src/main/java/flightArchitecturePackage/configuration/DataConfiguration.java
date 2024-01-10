package flightArchitecturePackage.configuration;

import flightArchitecturePackage.service.AirportService;
import flightArchitecturePackage.service.PlaneService;
import flightArchitecturePackage.utils.CsvToDatabaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Date;

@Configuration
public class DataConfiguration {

    private final Logger DATA_CONFIG_LOGGER = LoggerFactory.getLogger(DataConfiguration.class);
    private static final File AIRPORT_DATA_FILE = new File("Data/airports.csv");
    private static final File PLANE_DATA_FILE = new File("Data/planes.csv");

    @Autowired
    private final AirportService AIRPORT_SERVICE;
    @Autowired
    private final PlaneService PLANE_SERVICE;

    public DataConfiguration(AirportService airportService,
                             PlaneService planeService) {
        this.AIRPORT_SERVICE = airportService;
        this.PLANE_SERVICE = planeService;
    }

    public Logger getDataConfigLogger() {
        return this.DATA_CONFIG_LOGGER;
    }

    public AirportService getAirportService() {
        return this.AIRPORT_SERVICE;
    }

    public PlaneService getPlaneService() {
        return this.PLANE_SERVICE;
    }


    /*
                This method tests the row count of all database items on startup.
                If the row count doesn't match the csv file length, it saves the csv data to the DB
             */
    @PostConstruct
    public void findDatabaseSizeOnStartup() {
        if (AIRPORT_SERVICE.findDatabaseRowCount() < 9000) {
            DATA_CONFIG_LOGGER.info("Saving Airports");
            try {
                AIRPORT_SERVICE.saveAllAirports(CsvToDatabaseUtils.ConvertCsvToAirports(AIRPORT_DATA_FILE));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if ((PLANE_SERVICE.findDatabaseRowCount()) < 30) {
            DATA_CONFIG_LOGGER.info("Saving Planes");
            try {
                PLANE_SERVICE.saveAllPlanes(CsvToDatabaseUtils.savePlanesFromCsv(PLANE_DATA_FILE));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        DATA_CONFIG_LOGGER.info("Airport DB Row Count: " + getAirportRowCount());
        DATA_CONFIG_LOGGER.info("Plane DB Row Count: " + getPlaneRowCount());
    }

    //these methods are used mainly for testing
    private Long getAirportRowCount() {
        return (AIRPORT_SERVICE.findDatabaseRowCount());
    }

    private Long getPlaneRowCount() {
        return (PLANE_SERVICE.findDatabaseRowCount());
    }

    public boolean equals(Object objectRef) {
        if (this == objectRef) return true;
        if ((objectRef == null) || (getClass() != objectRef.getClass())) return false;
        if (!super.equals(objectRef)) return false;
        DataConfiguration that = (DataConfiguration) objectRef;
        return (Objects.equals(getDataConfigLogger(), that.getDataConfigLogger())
                && Objects.equals(getAirportService(), that.getAirportService())
                && Objects.equals(getPlaneService(), that.getPlaneService()));
    }

    public int hashCode() {
        return (Objects.hash(super.hashCode(), getDataConfigLogger(),
                getAirportService(), getPlaneService()));
    }

    @Override
    public String toString() {
        return ("DataConfiguration{" +
                "DATA_CONFIG_LOGGER=" + DATA_CONFIG_LOGGER +
                ", AIRPORT_SERVICE=" + AIRPORT_SERVICE +
                ", PLANE_SERVICE=" + PLANE_SERVICE +
                '}');
    }
}