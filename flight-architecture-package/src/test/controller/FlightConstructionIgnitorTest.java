package flightArchitecturePackage.controller;

import flightArchitecturePackage.service.request.FlightRequest;
import flightArchitecturePackage.service.response.FlightResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FlightConstructionIgnitorTest {

    @Autowired
    private final MockMvc MVC;

    private static final ObjectMapper FLIGHT_CONSTRUCTION_IGNITOR_MAPPER = new ObjectMapper();

    @Test
    public void randomFlightResponseIsNotNull() throws Exception {
        MockHttpServletResponse response = MVC.perform(
                        get("/api/v1/flight/random")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        FlightResponse flight = FLIGHT_CONSTRUCTION_IGNITOR_MAPPER.readValue(
                response.getContentAsString(),
                FlightResponse.class);
        assertNotNull(flight);
    }

    @Test
    void customFlightFollowsParameters() throws Exception {
        MockHttpServletResponse response =
                MVC.perform(post("/api/v1/flight/custom")
                                .content(toJsonString(new FlightRequest("10", "jet")))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        FlightResponse flight = FLIGHT_CONSTRUCTION_IGNITOR_MAPPER.readValue(
                response.getContentAsString(),
                FlightResponse.class);
        assertEquals("Jet", flight.getPlane().getType());
        assertTrue(isHHMMFormatIsLessThanHours(flight.getFlightHours(), 10));
    }

    @Test
    void customFlightFollowsParametersTwo() throws Exception {
        MockHttpServletResponse response =
                (MVC.perform(post("/api/v1/flight/custom")
                                .content(toJsonString(new FlightRequest("2", "propeller")))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse());

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        FlightResponse flight = FLIGHT_CONSTRUCTION_IGNITOR_MAPPER.readValue(
                response.getContentAsString(),
                FlightResponse.class);
        assertEquals("Propeller", flight.getPlane().getType());
        assertTrue(isHHMMFormatIsLessThanHours(flight.getFlightHours(), 2));
    }

    private static String toJsonString(FlightRequest flightRequest) {
        try {
            return FLIGHT_CONSTRUCTION_IGNITOR_MAPPER.writeValueAsString(flightRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isHHMMFormatIsLessThanHours(String formattedData, double hours) {
        String[] formattedArray = formattedData.split(":");
        return (Double.parseDouble(formattedArray[0]) <= hours);
    }
}