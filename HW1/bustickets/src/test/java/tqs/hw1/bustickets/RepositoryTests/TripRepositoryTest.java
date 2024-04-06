package tqs.hw1.bustickets.RepositoryTests;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tqs.hw1.bustickets.entities.Trip;
import tqs.hw1.bustickets.repositories.TripRepository;

@DataJpaTest
class TripRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TripRepository tripRepository;


    @Test
    void whenFindById_thenReturnTrip() {
        Trip trip = new Trip();
        trip.setOrigin("Test Origin");
        trip.setDestination("Test Destination");
        trip.setDepartureDateTime("Test Departure");
        entityManager.persist(trip);
        entityManager.flush();

        Trip found = tripRepository.findById(trip.getId()).orElse(null);

        assertThat(found.getOrigin()).isEqualTo(trip.getOrigin());
        assertThat(found.getDestination()).isEqualTo(trip.getDestination());
        assertThat(found.getDepartureDateTime()).isEqualTo(trip.getDepartureDateTime());
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Trip fromDb = tripRepository.findById(-11L).orElse(null);

        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfTrips_whenFindAll_thenReturnAllTrips() {
        Trip trip1 = new Trip();
        trip1.setOrigin("Test Origin 1");
        trip1.setDestination("Test Destination 1");
        trip1.setDepartureDateTime("Test Departure 1");

        Trip trip2 = new Trip();
        trip2.setOrigin("Test Origin 2");
        trip2.setDestination("Test Destination 2");
        trip2.setDepartureDateTime("Test Departure 2");

        Trip trip3 = new Trip();
        trip3.setOrigin("Test Origin 3");
        trip3.setDestination("Test Destination 3");
        trip3.setDepartureDateTime("Test Departure 3");

        entityManager.persist(trip1);
        entityManager.persist(trip2);
        entityManager.persist(trip3);
        entityManager.flush();

        List<Trip> allTrips = tripRepository.findAll();

        assertThat(allTrips).hasSize(3).extracting(Trip::getOrigin).containsOnly(trip1.getOrigin(), trip2.getOrigin(), trip3.getOrigin());
        assertThat(allTrips).hasSize(3).extracting(Trip::getDestination).containsOnly(trip1.getDestination(), trip2.getDestination(), trip3.getDestination());
        assertThat(allTrips).hasSize(3).extracting(Trip::getDepartureDateTime).containsOnly(trip1.getDepartureDateTime(), trip2.getDepartureDateTime(), trip3.getDepartureDateTime());
    }

}
