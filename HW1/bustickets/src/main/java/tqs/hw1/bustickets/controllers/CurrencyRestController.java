package tqs.hw1.bustickets.controllers;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tqs.hw1.bustickets.services.CurrencyService;
import tqs.hw1.bustickets.entities.Currency;

@RestController
@RequestMapping("/api")
public class CurrencyRestController {
    private final CurrencyService currencyService;

    public CurrencyRestController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/currencies")
    public ResponseEntity<List<String>> getAllCurrencies() {
        List<String> currencies = currencyService.getCurrencyList();
        if (currencies == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/currency/{currency}")
    public ResponseEntity<Currency> getCurrency(@PathVariable String currency) {
        Currency c = currencyService.getCurrencyRate(currency);
        if (c == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Integer>> getAllStats() {
        Map<String, Integer> stats = currencyService.getAllStats();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
