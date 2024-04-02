package tqs.hw1.bustickets.UnitTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import tqs.hw1.bustickets.entities.Trip;

class TripTest {
    @Test
    void testId() {
        Trip trip = new Trip();
        int id = 1;
        trip.setId(id);
        assertEquals(id, trip.getId());
    }

    @Test
    void testOrigin() {
        Trip trip = new Trip();
        String origin = "Origin";
        trip.setOrigin(origin);
        assertEquals(origin, trip.getOrigin());
    }

    @Test
    void testDestination() {
        Trip trip = new Trip();
        String destination = "Destination";
        trip.setDestination(destination);
        assertEquals(destination, trip.getDestination());
    }

    @Test
    void testDepartureDateTime() {
        Trip trip = new Trip();
        String departureDateTime = "2022-01-01T10:00:00";
        trip.setDepartureDateTime(departureDateTime);
        assertEquals(departureDateTime, trip.getDepartureDateTime());
    }

    @Test
    void testArrivalDateTime() {
        Trip trip = new Trip();
        String arrivalDateTime = "2022-01-01T12:00:00";
        trip.setArrivalDateTime(arrivalDateTime);
        assertEquals(arrivalDateTime, trip.getArrivalDateTime());
    }

    @Test
    void testCompany() {
        Trip trip = new Trip();
        String company = "Company";
        trip.setCompany(company);
        assertEquals(company, trip.getCompany());
    }

    @Test
    void testPrice() {
        Trip trip = new Trip();
        double price = 100.0;
        trip.setPrice(price);
        assertEquals(price, trip.getPrice());
    }

    @Test
    void testCurrency() {
        Trip trip = new Trip();
        String currency = "USD";
        trip.setCurrency(currency);
        assertEquals(currency, trip.getCurrency());
    }

    @Test
    void testAvailableSeats() {
        Trip trip = new Trip();
        int availableSeats = 50;
        trip.setAvailableSeats(availableSeats);
        assertEquals(availableSeats, trip.getAvailableSeats());
    }

    @Test
    void testAllArgsConstructor() {
        // int id = 1;
        String origin = "Origin";
        String destination = "Destination";
        String departureDateTime = "2022-01-01T10:00:00";
        String arrivalDateTime = "2022-01-01T12:00:00";
        String company = "Company";
        double price = 100.0;
        String currency = "USD";
        int availableSeats = 50;

        Trip trip = new Trip(origin, destination, departureDateTime, arrivalDateTime, company, price, currency, availableSeats);

        // assertEquals(id, trip.getId());
        assertEquals(origin, trip.getOrigin());
        assertEquals(destination, trip.getDestination());
        assertEquals(departureDateTime, trip.getDepartureDateTime());
        assertEquals(arrivalDateTime, trip.getArrivalDateTime());
        assertEquals(company, trip.getCompany());
        assertEquals(price, trip.getPrice());
        assertEquals(currency, trip.getCurrency());
        assertEquals(availableSeats, trip.getAvailableSeats());
    }
}