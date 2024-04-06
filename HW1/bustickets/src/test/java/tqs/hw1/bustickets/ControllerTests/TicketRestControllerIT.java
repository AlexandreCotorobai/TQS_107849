package tqs.hw1.bustickets.ControllerTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import tqs.hw1.bustickets.repositories.TicketRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import tqs.hw1.bustickets.entities.Ticket;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class TicketRestControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TicketRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void whenValidInput_thenStatus201() throws Exception {
        Ticket ticket = new Ticket(1, "Test Ticket", "test@email.com", "1234567890", 12345678, 123,
                "2021-06-01 12:00:00");
        mvc.perform(post("/api/tickets").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(ticket)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Test Ticket")));

        List<Ticket> found = repository.findAll();
        assertThat(found).extracting(Ticket::getName).containsOnly("Test Ticket");
    }

    @Test
    void whenInvalidInput_thenStatus400() throws Exception {
        String status = "{\"name\":\"Test\",\"email\":\"test@email.com\",\"phone\":\"1234567890\"";
        mvc.perform(post("/api/tickets").contentType(MediaType.APPLICATION_JSON)
                .content(status))
                .andExpect(status().isBadRequest());

    }

    @Test
    void whenValidId_thenStatus200() throws Exception {
        Ticket ticket = new Ticket(1, "Test Ticket", "test@email.com", "1234567890", 12345678, 123,
                "2021-06-01 12:00:00");
        repository.save(ticket);

        mvc.perform(get("/api/tickets/" + ticket.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test Ticket")));
    }

    @Test
    void whenInvalidId_thenStatus404() throws Exception {
        mvc.perform(get("/api/tickets/999").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenValidEmail_thenStatus200() throws Exception {
        Ticket ticket1 = new Ticket(1, "Test Ticket 1", "test1@email.com", "1234567890", 12345678, 123,
                "2021-06-01 12:00:00");
        repository.save(ticket1);

        mvc.perform(get("/api/reservations?email=test1@email.com").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Test Ticket 1")));
    }

    @Test
    void whenInvalidEmail_thenStatus200AndEmptyList() throws Exception {
        mvc.perform(get("/api/reservations?email=nonexistent@email.com").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
