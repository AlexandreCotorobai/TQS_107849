package tqs.hw1.bustickets.UnitTests;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.*;
import java.io.IOException;
import java.util.*;

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
        currencyService.resetStats();
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

    @Test
    @DisplayName("Test getCurrencyRate when currency is not in cache")
    void testGetCurrencyRateNotInCache() {
        // Mock the HTTP response from the external API
        String responseBody = "{\"rate\": 1.0}";
        ResponseEntity<String> mockResponse = new ResponseEntity<>(responseBody, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(mockResponse);

        // Call getCurrencyRate with a currency that's not in the cache
        Currency currency = currencyService.getCurrencyRate("USD");

        // Verify that the returned currency has the expected values
        assertEquals("USD", currency.getId());
        assertEquals(1.0, currency.getEurRate());

        // Verify that the currency was saved in the repository
        Mockito.verify(currencyRepository).save(any(Currency.class));
    }

    @Test
    @DisplayName("Test getCurrencyRate when currency is in cache")
    void testGetCurrencyRateInCache() {
        // Call getCurrencyRate with a currency that's in the cache
        Currency currency = currencyService.getCurrencyRate("AED");

        // Verify that the returned currency has the expected values
        assertEquals("AED", currency.getId());
        assertEquals(1.0, currency.getEurRate());

        // Verify that the external API was not called
        Mockito.verify(restTemplate, never()).exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class),
                eq(String.class));
    }

    @Test
    @DisplayName("Test getAllStats")
    void testGetAllStats() {
        // Call getAllStats
        Map<String, Integer> stats = currencyService.getAllStats();

        // Verify that the returned stats have the expected values
        assertEquals(0, stats.get("apiCalls"));
        assertEquals(0, stats.get("cacheHits"));
        assertEquals(0, stats.get("apiMisses"));
    }
    
    @Test
    @DisplayName("Test getCurrencyRate when API response is not valid JSON")
    void testGetCurrencyRateInvalidJson() {
        // Mock the HTTP response from the external API
        String responseBody = "This is not valid JSON";
        ResponseEntity<String> mockResponse = new ResponseEntity<>(responseBody, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
            .thenReturn(mockResponse);
    
        // Call getCurrencyRate with a currency that's not in the cache
        Currency currency = currencyService.getCurrencyRate("USD");
        List<String> currencyList = currencyService.getCurrencyList();

        // Verify that the returned currency is null
        assertNull(currency);
        assertTrue(currencyList.isEmpty());

        // Call getAllStats and verify that apiMisses is 1
        Map<String, Integer> stats = currencyService.getAllStats();
        assertEquals(2, stats.get("apiMisses"));
    }
    
}
