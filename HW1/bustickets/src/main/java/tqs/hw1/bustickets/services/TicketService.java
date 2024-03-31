package tqs.hw1.bustickets.services;

import java.util.List;

import tqs.hw1.bustickets.entities.Ticket;

public interface TicketService {
    public Ticket buyTicket(Ticket ticket);
    public Ticket getTicketById(long id);
    public List<Ticket> getTickets();
    // public void getTicketsByTripId(int tripId); // REVIEW LATER
    public List<Ticket> getReservations(String email);
    
}
