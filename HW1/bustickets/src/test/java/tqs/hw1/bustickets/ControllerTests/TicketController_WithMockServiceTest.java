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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
        Ticket ticket = new Ticket(1, "Test Ticket", "test@email.com", "1234567890", 12345678, 123,
                "2021-06-01 12:00:00");

        when(service.buyTicket(Mockito.any())).thenReturn(ticket);

        mvc.perform(
                post("/api/tickets").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(ticket)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(ticket.getName())));

        verify(service, times(1)).buyTicket(Mockito.any());
    }

    @Test
    void whenInvalidTicket_thenBadRequest() throws Exception {
        Ticket ticket = new Ticket(1, "T", "test@email.com", "1234567890", 12345678, 123, "2021-06-01 12:00:00");

        when(service.buyTicket(Mockito.any())).thenReturn(ticket);

        mvc.perform(
                post("/api/tickets").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(ticket)))
                .andExpect(status().isBadRequest());

        verify(service, times(0)).buyTicket(Mockito.any());
    }

    @Test
    void whenGetTicketByID_thenReturnJson() throws Exception {
        Ticket ticket = new Ticket(1, "Test Ticket", "test@email.com", "1234567890", 12345678, 123,
                "2021-06-01 12:00:00");

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

    @Test
    void whenValidEmail_thenStatus200() throws Exception {
        Ticket ticket = new Ticket(1, "Test Ticket", "test@email.com", "1234567890", 12345678, 123,
                "2021-06-01 12:00:00");

        when(service.getReservations("test1")).thenReturn(List.of(ticket));

        mvc.perform(
                get("/api/reservations?email=test1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(ticket.getName())));

        verify(service, times(1)).getReservations("test1");
    }
}