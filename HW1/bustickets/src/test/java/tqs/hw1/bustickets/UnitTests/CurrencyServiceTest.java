package tqs.hw1.bustickets.UnitTests;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import tqs.hw1.bustickets.services.CurrencyServiceImpl;
import tqs.hw1.bustickets.repositories.CurrencyRepository;
import tqs.hw1.bustickets.entities.Currency;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock(lenient = true)
    private CurrencyRepository currencyRepository;

    @Mock(lenient = true)
    private RestTemplateBuilder restTemplateBuilder;

    @Mock(lenient = true)
    private RestTemplate restTemplate;

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        Currency currency1 = new Currency("AED", 1.0);
        Currency currency2 = new Currency("AFN", 0.85);
        Currency currency3 = new Currency("ALL", 1.15);

        List<Currency> allCurrencies = List.of(currency1, currency2, currency3);

        ResponseEntity<String> mockResponse = new ResponseEntity<>(CurrencyAPIResponse.resList, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                eq("https://twelve-data1.p.rapidapi.com/forex_pairs?currency_base=EUR&format=json"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class))).thenReturn(mockResponse);

        ResponseEntity<String> mockResponse2 = new ResponseEntity<>(CurrencyAPIResponse.resRate, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                Mockito.matches("https://twelve-data1.p.rapidapi.com/exchange_rate\\?symbol=EUR/.*"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class))).thenReturn(mockResponse2);

        Mockito.when(restTemplateBuilder.build()).thenReturn(restTemplate);
        Mockito.when(currencyRepository.findAll()).thenReturn(allCurrencies);
        Mockito.when(currencyRepository.findById(currency1.getId())).thenReturn(Optional.of(currency1));
        Mockito.when(currencyRepository.findById(currency2.getId())).thenReturn(Optional.of(currency2));
        Mockito.when(currencyRepository.findById(currency3.getId())).thenReturn(Optional.of(currency3));
    }

    @Test
    @DisplayName("Test getCurrencyList")
    void testGetCurrencyList() {
        List<String> expectedCurrencyList = List.of("AED", "AFN", "ALL");
        List<String> actualCurrencyList = currencyService.getCurrencyList();
        assertEquals(expectedCurrencyList, actualCurrencyList,
                "The actual currency list does not match the expected currency list");
    }

    @Test
    @DisplayName("Test getCurrencyById")
    void testGetCurrencyById() {
        Currency currency = currencyService.getCurrencyRate("AED");
        assertEquals(1.0, currency.getEurRate());
        assertEquals("AED", currency.getId());
    }

    @Test
    @DisplayName("Test getCurrencyById when cached")
    void testGetCurrencyByIdCached() {
        Currency currency = currencyService.getCurrencyRate("AED");

        Mockito.when(restTemplateBuilder.build()).thenReturn(null);

        currency = currencyService.getCurrencyRate("AED");
        assertEquals(1.0, currency.getEurRate());
        assertEquals("AED", currency.getId());
    }

}
