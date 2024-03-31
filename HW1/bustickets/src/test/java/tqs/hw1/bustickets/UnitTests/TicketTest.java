package tqs.hw1.bustickets.UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.Date;

import tqs.hw1.bustickets.entities.Ticket;

class TicketTest {
    @Test
    void testId() {
        Ticket ticket = new Ticket();
        int id = 1;
        ticket.setId(id);
        assertEquals(id, ticket.getId());
    }

    @Test
    void testTripId() {
        Ticket ticket = new Ticket();
        int tripId = 2;
        ticket.setTripId(tripId);
        assertEquals(tripId, ticket.getTripId());
    }

    @Test
    void testIssueDateTime() {
        Ticket ticket = new Ticket();
        Date issueDateTime = new Date();
        ticket.setIssueDateTime(issueDateTime);
        assertEquals(issueDateTime, ticket.getIssueDateTime());
    }

    @Test
    void testName() {
        Ticket ticket = new Ticket();
        String name = "Test Name";
        ticket.setName(name);
        assertEquals(name, ticket.getName());
    }

    @Test
    void testEmail() {
        Ticket ticket = new Ticket();
        String email = "test@example.com";
        ticket.setEmail(email);
        assertEquals(email, ticket.getEmail());
    }

    @Test
    void testPhone() {
        Ticket ticket = new Ticket();
        String phone = "1234567890";
        ticket.setPhone(phone);
        assertEquals(phone, ticket.getPhone());
    }

    @Test
    void testCreditCardNumber() {
        Ticket ticket = new Ticket();
        int creditCardNumber = 12345678;
        ticket.setCreditCardNumber(creditCardNumber);
        assertEquals(creditCardNumber, ticket.getCreditCardNumber());
    }

    @Test
    void testCvv() {
        Ticket ticket = new Ticket();
        int cvv = 123;
        ticket.setCvv(cvv);
        assertEquals(cvv, ticket.getCvv());
    }

    @Test
    void testExpirationDate() {
        Ticket ticket = new Ticket();
        Date expirationDate = new Date();
        ticket.setExpirationDate(expirationDate);
        assertEquals(expirationDate, ticket.getExpirationDate());
    }

    @Test
    void testAllArgsConstructor() {
        int id = 1;
        int tripId = 2;
        Date issueDateTime = new Date();
        String name = "Test Name";
        String email = "test@example.com";
        String phone = "1234567890";
        int creditCardNumber = 12345678;
        int cvv = 123;
        Date expirationDate = new Date();

        Ticket ticket = new Ticket(id, tripId, issueDateTime, name, email, phone, creditCardNumber, cvv,
                expirationDate);

        assertEquals(id, ticket.getId());
        assertEquals(tripId, ticket.getTripId());
        assertEquals(issueDateTime, ticket.getIssueDateTime());
        assertEquals(name, ticket.getName());
        assertEquals(email, ticket.getEmail());
        assertEquals(phone, ticket.getPhone());
        assertEquals(creditCardNumber, ticket.getCreditCardNumber());
        assertEquals(cvv, ticket.getCvv());
        assertEquals(expirationDate, ticket.getExpirationDate());
    }
}
