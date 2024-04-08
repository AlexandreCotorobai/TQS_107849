package tqs.hw1.bustickets.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import tqs.hw1.bustickets.entities.Ticket;
import tqs.hw1.bustickets.services.TicketService;

@RestController
@RequestMapping("/api")
public class TicketRestController {
    private final TicketService ticketService;

    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/tickets/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable long id) {
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticket, HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/tickets")
    public ResponseEntity<Ticket> buyTicket(@Valid @RequestBody Ticket ticket) {
        try {
            return new ResponseEntity<>(ticketService.buyTicket(ticket), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/reservations")
    public ResponseEntity<List<Ticket>> getReservationsByUser(
            @RequestParam(name = "email", required = false) String email) {
        return new ResponseEntity<>(ticketService.getReservations(email), HttpStatus.OK);
    }

}
