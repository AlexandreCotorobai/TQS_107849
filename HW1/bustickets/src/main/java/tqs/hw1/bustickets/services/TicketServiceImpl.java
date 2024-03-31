package tqs.hw1.bustickets.services;

import org.springframework.stereotype.Service;
import java.util.List;

import tqs.hw1.bustickets.entities.Ticket;
import tqs.hw1.bustickets.repositories.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket buyTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getReservations(String email) {
        return ticketRepository.findByEmail(email);
    }

    
}
