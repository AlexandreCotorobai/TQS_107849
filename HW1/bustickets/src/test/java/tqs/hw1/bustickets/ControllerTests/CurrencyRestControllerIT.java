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

import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import tqs.hw1.bustickets.entities.Currency;
import tqs.hw1.bustickets.repositories.CurrencyRepository;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
 class CurrencyRestControllerIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CurrencyRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
     void whenGetCurrencies_thenStatus200() throws Exception {
        mvc.perform(get("/api/currencies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
     void whenGetCurrency_thenStatus200() throws Exception {
        Currency currency = new Currency("USD", 1.0);
        repository.save(currency);

        mvc.perform(get("/api/currency/USD")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("USD")))
                .andExpect(jsonPath("$.eurRate", is(1.0)));
    }

}
