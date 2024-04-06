package tqs.hw1.bustickets.ControllerTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import tqs.hw1.bustickets.repositories.TripRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import tqs.hw1.bustickets.entities.Trip;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class TripRestControllerIT {
    
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TripRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void whenThereIsTrips_thenReturnTrips() throws Exception {
        Trip trip1 = new Trip();
        trip1.setOrigin("Aveiro");
        trip1.setDestination("Porto");
        trip1.setDepartureDateTime("2024-01-01 12:00:00");

        Trip trip2 = new Trip();
        trip2.setOrigin("Aveiro");
        trip2.setDestination("Lisboa");
        trip2.setDepartureDateTime("2024-01-01 13:00:00");

        repository.save(trip1);
        repository.save(trip2);

        mvc.perform(get("/api/trips")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void whenNoTrips_thenReturnEmpty() throws Exception {
        mvc.perform(get("/api/trips")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenValidTripId_thenReturnTrip() throws Exception {
        Trip trip = new Trip();
        trip.setOrigin("Aveiro");
        trip.setDestination("Porto");
        trip.setDepartureDateTime("2024-01-01 12:00:00");

        repository.save(trip);

        mvc.perform(get("/api/trips/" + trip.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.origin", is("Aveiro")))
                .andExpect(jsonPath("$.destination", is("Porto")))
                .andExpect(jsonPath("$.departureDateTime", is("2024-01-01 12:00:00")));
    }

    @Test
    void whenInvalidTripId_thenReturnNotFound() throws Exception {
        mvc.perform(get("/api/trips/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
