package tqs.hw1.bustickets.ControllerTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import tqs.hw1.bustickets.entities.Currency;
import tqs.hw1.bustickets.repositories.CurrencyRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
 class CurrencyRestControllerTemplateIT {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CurrencyRepository repository;

    @AfterEach
     void resetDb() {
        repository.deleteAll();
    }

    @Test
     void whenGetCurrencies_thenStatus200() {
        ResponseEntity<List<String>> response = restTemplate.exchange(
                "/api/currencies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
     void whenGetCurrency_thenStatus200() {
        Currency currency = new Currency("USD", 1.0);
        repository.save(currency);

        ResponseEntity<Currency> response = restTemplate
                .getForEntity("/api/currency/USD", Currency.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo("USD");
        assertThat(response.getBody().getEurRate()).isEqualTo(1.0);
    }

    @Test
     void whenGetStats_thenStatus200() {
        ResponseEntity<Map<String, Integer>> response = restTemplate.exchange(
                "/api/stats",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Integer>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
