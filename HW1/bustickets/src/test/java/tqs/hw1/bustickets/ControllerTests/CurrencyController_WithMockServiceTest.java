package tqs.hw1.bustickets.ControllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

import tqs.hw1.bustickets.controllers.CurrencyRestController;
import tqs.hw1.bustickets.services.CurrencyService;
import tqs.hw1.bustickets.entities.Currency;

@WebMvcTest(CurrencyRestController.class)
 class CurrencyController_WithMockServiceTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CurrencyService service;

    @BeforeEach
     void setUp() throws Exception {
    }

    @Test
     void testGetAllCurrencies() throws Exception {
        List<String> currencies = Arrays.asList("USD", "EUR", "GBP");
        when(service.getCurrencyList()).thenReturn(currencies);

        mvc.perform(get("/api/currencies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", is("USD")))
                .andExpect(jsonPath("$[1]", is("EUR")))
                .andExpect(jsonPath("$[2]", is("GBP")));

        verify(service, times(1)).getCurrencyList();
    }

    @Test
     void testGetCurrency() throws Exception {
        Currency currency = new Currency("USD", 1.0);
        when(service.getCurrencyRate("USD")).thenReturn(currency);

        mvc.perform(get("/api/currency/USD")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("USD")))
                .andExpect(jsonPath("$.eurRate", is(1.0)));

        verify(service, times(1)).getCurrencyRate("USD");
    }

    @Test
     void testGetAllStats() throws Exception {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("apiCalls", 10);
        stats.put("cacheHits", 5);
        stats.put("apiMisses", 3);
        stats.put("cacheMisses", 2);
        when(service.getAllStats()).thenReturn(stats);

        mvc.perform(get("/api/stats")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.apiCalls", is(10)))
                .andExpect(jsonPath("$.cacheHits", is(5)))
                .andExpect(jsonPath("$.apiMisses", is(3)))
                .andExpect(jsonPath("$.cacheMisses", is(2)));

        verify(service, times(1)).getAllStats();
    }
}
