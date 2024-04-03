package tqs.hw1.bustickets.ControllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import tqs.hw1.bustickets.controllers.TicketRestController;
import tqs.hw1.bustickets.services.TicketService;
import tqs.hw1.bustickets.entities.Ticket;


@WebMvcTest(TicketRestController.class)
class TicketController_WithMockServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TicketService service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenPostTicket_thenBuyTicket() throws Exception {
        Ticket ticket = new Ticket(/* initialize fields here */);

        when(service.buyTicket(Mockito.any())).thenReturn(ticket);

        mvc.perform(
                post("/api/tickets").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(ticket)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(ticket.getName())));

        verify(service, times(1)).buyTicket(Mockito.any());
    }

    @Test
    void givenManyTickets_whenGetTickets_thenReturnJsonArray() throws Exception {
        Ticket ticket1 = new Ticket(/* initialize fields here */);
        Ticket ticket2 = new Ticket(/* initialize fields here */);
        Ticket ticket3 = new Ticket(/* initialize fields here */);

        List<Ticket> allTickets = Arrays.asList(ticket1, ticket2, ticket3);

        when(service.getTickets()).thenReturn(allTickets);

        mvc.perform(
                get("/api/tickets").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(ticket1.getName())))
                .andExpect(jsonPath("$[1].name", is(ticket2.getName())))
                .andExpect(jsonPath("$[2].name", is(ticket3.getName())));

        verify(service, times(1)).getTickets();
    }

    @Test
    void whenGetTicketByID_thenReturnJson() throws Exception {
        Ticket ticket = new Ticket(/* initialize fields here */);

        when(service.getTicketById(ticket.getId())).thenReturn(ticket);

        mvc.perform(
                get("/api/tickets/" + ticket.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(ticket.getName())));

        verify(service, times(1)).getTicketById(ticket.getId());
    }

    @Test
    void whenGetTicketByID_thenReturnNotFound() throws Exception {
        when(service.getTicketById(1L)).thenReturn(null);

        mvc.perform(
                get("/api/tickets/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(1)).getTicketById(1L);
    }
}