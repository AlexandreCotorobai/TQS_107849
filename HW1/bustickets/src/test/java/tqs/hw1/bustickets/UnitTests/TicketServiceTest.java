package tqs.hw1.bustickets.UnitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.reset;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import tqs.hw1.bustickets.entities.Ticket;
import tqs.hw1.bustickets.repositories.TicketRepository;
import tqs.hw1.bustickets.services.TicketServiceImpl;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @BeforeEach
    void setUp() {
        reset(ticketRepository);
    }

    @Test
    @DisplayName("Test buying Ticket")
    void whenValidTicket_thenTicketShouldBeSaved() {
        Ticket ticket = new Ticket();
        ticket.setTripId(1);
        ticket.setIssueDateTime("2021-06-01 12:00:00");
        ticket.setName("John Doe");
        ticket.setEmail("john.doe@example.com");
        ticket.setPhone("1234567890");
        ticket.setCreditCardNumber(12345678);
        ticket.setCvv(123);
        ticket.setExpirationDate("2021-06-01 12:00:00");

        ticketService.buyTicket(ticket);

        Mockito.verify(ticketRepository, Mockito.times(1)).save(ticket);
    }

    @Test
    @DisplayName("Test get ticket by id")
    void whenValidId_thenTicketShouldBeReturned() {
        Ticket ticket = new Ticket();
        ticket.setId(1);
        Mockito.when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        Ticket found = ticketService.getTicketById(1);

        assertEquals(ticket.getId(), found.getId());
        Mockito.verify(ticketRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    @DisplayName("Test get ticket by id not found")
    void whenInvalidId_thenNullShouldBeReturned() {
        Mockito.when(ticketRepository.findById(99L)).thenReturn(Optional.empty());

        Ticket found = ticketService.getTicketById(99);

        assertEquals(null, found);
        Mockito.verify(ticketRepository, Mockito.times(1)).findById(99L);
    }

    @Test
    @DisplayName("Test get all tickets")
    void whenGetTickets_thenAllTicketsShouldBeReturned() {
        List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket());
        Mockito.when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> found = ticketService.getTickets();

        assertEquals(tickets.size(), found.size());
        Mockito.verify(ticketRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Test get reservations by email")
    void whenValidEmail_thenReservationsShouldBeReturned() {
        List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket());
        Mockito.when(ticketRepository.findByEmail("john.doe@example.com")).thenReturn(tickets);

        List<Ticket> found = ticketService.getReservations("john.doe@example.com");

        assertEquals(tickets.size(), found.size());
        Mockito.verify(ticketRepository, Mockito.times(1)).findByEmail("john.doe@example.com");
    }
}