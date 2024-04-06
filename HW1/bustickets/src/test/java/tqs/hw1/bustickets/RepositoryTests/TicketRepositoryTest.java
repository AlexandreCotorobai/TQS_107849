package tqs.hw1.bustickets.RepositoryTests;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tqs.hw1.bustickets.entities.Ticket;
import tqs.hw1.bustickets.repositories.TicketRepository;

@DataJpaTest
class TicketRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void whenFindById_thenReturnTicket() {
        Ticket ticket = new Ticket(1, "Test Ticket", "test@email.com", "1234567890", 12345678, 123, "2021-06-01 12:00:00");
        entityManager.persist(ticket);
        entityManager.flush();

        Ticket found = ticketRepository.findById(ticket.getId()).orElse(null);

        assertThat(found.getName()).isEqualTo(ticket.getName());
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Ticket fromDb = ticketRepository.findById(-11L).orElse(null);

        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfTickets_whenFindAll_thenReturnAllTickets() {
        Ticket ticket1 = new Ticket(1, "Test Ticket 1", "test@email.com", "1234567890", 12345678, 123, "2021-06-01 12:00:00");
        Ticket ticket2 = new Ticket(1, "Test Ticket 2", "test@email.com", "1234567890", 12345678, 123, "2021-06-01 12:00:00");
        Ticket ticket3 = new Ticket(1, "Test Ticket 3", "test@email.com", "1234567890", 12345678, 123, "2021-06-01 12:00:00");


        entityManager.persist(ticket1);
        entityManager.persist(ticket2);
        entityManager.persist(ticket3);
        entityManager.flush();

        List<Ticket> allTickets = ticketRepository.findAll();

        assertThat(allTickets).hasSize(3).extracting(Ticket::getName).containsOnly(ticket1.getName(), ticket2.getName(), ticket3.getName());
    }
    
    
    
}
