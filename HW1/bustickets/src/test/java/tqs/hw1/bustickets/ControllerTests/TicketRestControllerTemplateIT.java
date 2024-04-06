package tqs.hw1.bustickets.ControllerTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import tqs.hw1.bustickets.entities.Ticket;
import tqs.hw1.bustickets.repositories.TicketRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class TicketRestControllerTemplateIT {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TicketRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("POST /tickets CREATED")
    void whenValidInput_thenStatus201() throws Exception {
        String status = "{\"tripId\":1,\"name\":\"Test Ticket\",\"email\":\"test@email.com\",\"phone\":\"1234567890\",\"creditCardNumber\":12345678,\"cvv\":123,\"expirationDate\":\"2021-06-01 12:00:00\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(status, headers);
        ResponseEntity<Object> response = restTemplate.postForEntity("/api/tickets", entity, Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    // @Test
    // @DisplayName("POST /tickets BAD REQUEST")
    // void whenInvalidInput_thenStatus400() throws Exception {
    //     String status = "{\"tripId\":1,\"name\":\"T\",\"email\":\"test@email.com\",\"phone\":\"1234567890\",\"creditCardNumber\":12345678,\"cvv\":123,\"expirationDate\":\"2021-06-01 12:00:00\"}";
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_JSON);

    //     HttpEntity<String> entity = new HttpEntity<>(status, headers);
    //     ResponseEntity<Object> response = restTemplate.postForEntity("/api/tickets",
    //             entity, Object.class);

    //     System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + response.getBody());
    //     assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    // }

    @Test
    @DisplayName("GET /tickets/{id} OK")
    void whenValidId_thenStatus200() throws Exception {
        String status = "{\"tripId\":1,\"name\":\"Test Ticket\",\"email\":\"test@email.com\",\"phone\":\"1234567890\",\"creditCardNumber\":12345678,\"cvv\":123,\"expirationDate\":\"2021-06-01 12:00:00\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(status, headers);
        restTemplate.postForEntity("/api/tickets", entity,
                Object.class);

        ResponseEntity<Ticket> response = restTemplate.getForEntity("/api/tickets/3", Ticket.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("GET /tickets/{id} NOT FOUND")
    void whenInvalidId_thenStatus404() throws Exception {
        ResponseEntity<Ticket> response = restTemplate.getForEntity("/api/tickets/1",
                Ticket.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("GET /reservations OK")
    void whenValidEmail_thenStatus200() throws Exception {
        String status = "{\"tripId\":1,\"name\":\"Test Ticket\",\"email\":\"test@email.com\",\"phone\":\"1234567890\",\"creditCardNumber\":12345678,\"cvv\":123,\"expirationDate\":\"2021-06-01 12:00:00\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(status, headers);
        restTemplate.postForEntity("/api/tickets", entity,
                Object.class);

        ResponseEntity<List<Ticket>> response = restTemplate.exchange("/api/reservations?email=test@email.com",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ticket>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);

    }

    @Test
    @DisplayName("GET /reservations EMPTY LIST")
    void whenInvalidEmail_thenStatus200AndEmptyList() throws Exception {
        ResponseEntity<List<Ticket>> response = restTemplate.exchange("/api/reservations?email=nonexistentemail.com",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ticket>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(0);
    }

}
