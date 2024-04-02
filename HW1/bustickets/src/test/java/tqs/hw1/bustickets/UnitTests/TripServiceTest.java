package tqs.hw1.bustickets.UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.reset;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.hw1.bustickets.entities.Trip;
import tqs.hw1.bustickets.repositories.TripRepository;
import tqs.hw1.bustickets.services.TripServiceImpl;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {

    static Stream<Arguments> provideTripsForTesting() {
        return Stream.of(
                Arguments.of("NonExistentOrigin", "NonExistentDestination", "NonExistentCompany",
                        "NonExistentDateTime"),
                Arguments.of("Origin1", "NonExistentDestination", "NonExistentCompany", "NonExistentDateTime"),
                Arguments.of("Origin1", "Destination1", "NonExistentCompany", "NonExistentDateTime"),
                Arguments.of("Origin1", "Destination1", "Company1", "NonExistentDateTime"));
    }

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripServiceImpl tripService;

    @BeforeEach
    void setUp() {
        reset(tripRepository);

    }

    @Test
    void testGetTrips() {
        Trip trip1 = new Trip("Origin1", "Destination1", "2022-01-01T10:00:00",
                "2022-01-01T12:00:00", "Company1",
                100.0, "USD", 50);
        Trip trip2 = new Trip("Origin2", "Destination2", "2022-01-01T10:00:00",
                "2022-01-01T12:00:00", "Company2",
                200.0, "EUR", 60);
        List<Trip> allTrips = Arrays.asList(trip1, trip2);

        Mockito.when(tripRepository.findAll()).thenReturn(allTrips);

        List<Trip> trips = tripService.getTrips("Origin1", "Destination1", null,
                "2022-01-01T10:00:00");

        assertThat(trips).hasSize(1).contains(trip1);
        Mockito.verify(tripRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testGetTrips_AllParameters() {
        Mockito.when(tripRepository.findAll()).thenReturn(Arrays.asList(
                new Trip("Origin1", "Destination1", "2022-01-01T10:00:00",
                        "2022-01-01T12:00:00", "Company1", 100.0,
                        "USD", 50)));

        List<Trip> trips = tripService.getTrips("Origin1", "Destination1",
                "Company1", "2022-01-01T10:00:00");
        assertEquals(1, trips.size());
        assertEquals("Origin1", trips.get(0).getOrigin());
        assertEquals("Destination1", trips.get(0).getDestination());
        assertEquals("Company1", trips.get(0).getCompany());
        Mockito.verify(tripRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testGetTrips_AllNull() {
        Mockito.when(tripRepository.findAll()).thenReturn(Arrays.asList(
                new Trip("Origin1", "Destination1", "2022-01-01T10:00:00",
                        "2022-01-01T12:00:00", "Company1", 100.0,
                        "USD", 50),
                new Trip("Origin2", "Destination2", "2022-01-01T10:00:00",
                        "2022-01-01T12:00:00", "Company2", 200.0,
                        "EUR", 60)));

        List<Trip> trips = tripService.getTrips(null, null, null, null);
        assertEquals(2, trips.size());
        Mockito.verify(tripRepository, Mockito.times(1)).findAll();
    }

    @ParameterizedTest
    @MethodSource("provideTripsForTesting")
    void testGetTrips_NoMatch(String startLocation, String endLocation, String company, String dateTime) {
        Mockito.when(tripRepository.findAll()).thenReturn(Arrays.asList(
                new Trip("Origin1", "Destination1", "2022-01-01T10:00:00",
                        "2022-01-01T12:00:00", "Company1", 100.0,
                        "USD", 50)));

        List<Trip> trips = tripService.getTrips(startLocation, endLocation, company, dateTime);

        assertTrue(trips.isEmpty());
        Mockito.verify(tripRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testGetTripById() {
        Trip trip1 = new Trip("Origin1", "Destination1", "2022-01-01T10:00:00", "2022-01-01T12:00:00", "Company1",
                100.0, "USD", 50);
        Mockito.when(tripRepository.findById(1L)).thenReturn(Optional.of(trip1));
        Trip trip = tripService.getTripById(1L);

        assertEquals("Origin1", trip.getOrigin());
        assertEquals("Destination1", trip.getDestination());
        Mockito.verify(tripRepository, Mockito.times(1)).findById(1L);
    }
}