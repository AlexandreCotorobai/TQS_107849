package tqs.hw1.bustickets.ControllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import tqs.hw1.bustickets.controllers.TripRestController;
import tqs.hw1.bustickets.services.TripService;
import tqs.hw1.bustickets.entities.Trip;

@WebMvcTest(TripRestController.class)
class TripController_WithMockServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TripService service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenGetTrips_thenReturnTrips() throws Exception {

        Trip trip1 = new Trip();
        trip1.setOrigin("Aveiro");
        trip1.setDestination("Porto");
        trip1.setDepartureDateTime("2024-01-01 12:00:00");

        Trip trip2 = new Trip();
        trip2.setOrigin("Aveiro");
        trip2.setDestination("Lisboa");
        trip2.setDepartureDateTime("2024-01-01 13:00:00");

        List<Trip> allTrips = Arrays.asList(trip1, trip2);

        when(service.getTrips(null, null, null)).thenReturn(allTrips);

        mvc.perform(get("/api/trips")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(service, times(1)).getTrips(null, null, null);
    }

    @Test
    void whenNoTrips_thenReturnEmpty() throws Exception {

        when(service.getTrips("NotValidOrigin", null, null)).thenReturn(new ArrayList<>());

        mvc.perform(get("/api/trips")
                .param("startLocation", "NotValidOrigin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(1)).getTrips("NotValidOrigin", null, null);
    }

    @Test
    void whenValidId_thenTripShouldBeFound() throws Exception {
        Trip trip = new Trip();
        trip.setOrigin("Aveiro");
        trip.setDestination("Porto");
        trip.setDepartureDateTime("2024-01-01 12:00:00");

        when(service.getTripById(1L)).thenReturn(trip);

        mvc.perform(get("/api/trips/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.origin", is(trip.getOrigin())))
                .andExpect(jsonPath("$.destination", is(trip.getDestination())));

        verify(service, times(1)).getTripById(1L);
    }

    @Test
    void whenInValidId_thenTripShouldNotBeFound() throws Exception {
        when(service.getTripById(1L)).thenReturn(null);

        mvc.perform(get("/api/trips/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(1)).getTripById(1L);
    }
}
