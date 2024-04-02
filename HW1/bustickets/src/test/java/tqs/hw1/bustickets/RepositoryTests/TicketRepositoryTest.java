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
public class TicketRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void whenFindById_thenReturnTicket() {
        Ticket ticket = new Ticket(1, "2021-06-01 12:00:00", "Test Ticket", "test@email.com", "1234567890", 12345678, 123, "2021-06-01 12:00:00");
        entityManager.persist(ticket);
        entityManager.flush();

        Ticket found = ticketRepository.findById(ticket.getId()).orElse(null);

        assertThat(found.getName()).isEqualTo(ticket.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Ticket fromDb = ticketRepository.findById(-11L).orElse(null);

        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfTickets_whenFindAll_thenReturnAllTickets() {
        Ticket ticket1 = new Ticket();
        ticket1.setName("Test Ticket 1");

        Ticket ticket2 = new Ticket();
        ticket2.setName("Test Ticket 2");

        Ticket ticket3 = new Ticket();
        ticket3.setName("Test Ticket 3");

        entityManager.persist(ticket1);
        entityManager.persist(ticket2);
        entityManager.persist(ticket3);
        entityManager.flush();

        List<Ticket> allTickets = ticketRepository.findAll();

        assertThat(allTickets).hasSize(3).extracting(Ticket::getName).containsOnly(ticket1.getName(), ticket2.getName(), ticket3.getName());
    }
    
    
    
}
