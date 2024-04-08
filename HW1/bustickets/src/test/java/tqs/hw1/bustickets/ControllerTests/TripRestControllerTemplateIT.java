package tqs.hw1.bustickets.ControllerTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import tqs.hw1.bustickets.entities.Trip;
import tqs.hw1.bustickets.repositories.TripRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class TripRestControllerTemplateIT {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TripRepository repository;

    @AfterEach
    public void resetDb() {
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

        ResponseEntity<List<Trip>> response = restTemplate.exchange("/api/trips",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Trip>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
    }

    @Test
    void whenNoTrips_thenReturnEmpty() throws Exception {
        ResponseEntity<List<Trip>> response = restTemplate.exchange("/api/trips",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Trip>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void whenValidTripId_thenReturnTrip() throws Exception {
        Trip trip = new Trip();
        trip.setOrigin("Aveiro");
        trip.setDestination("Porto");
        trip.setDepartureDateTime("2024-01-01 12:00:00");

        repository.save(trip);

        ResponseEntity<Trip> response = restTemplate.exchange("/api/trips/" + trip.getId(),
                HttpMethod.GET, null, Trip.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getOrigin()).isEqualTo("Aveiro");
    }

    @Test
    void whenInvalidTripId_thenReturnNotFound() throws Exception {
        ResponseEntity<Trip> response = restTemplate.exchange("/api/trips/999",
                HttpMethod.GET, null, Trip.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
