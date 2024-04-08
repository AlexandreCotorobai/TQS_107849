package tqs.hw1.bustickets.services;

import java.util.List;

public interface CurrencyService {
    List<String> getCurrencyList();
    double getCurrencyRate(String currencyTo);
}
