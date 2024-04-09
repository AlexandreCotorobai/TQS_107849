package tqs.hw1.bustickets.services;

import java.util.*;
import tqs.hw1.bustickets.entities.Currency;

public interface CurrencyService {
    List<String> getCurrencyList();
    Currency getCurrencyRate(String currencyTo);
    Map<String,Integer> getAllStats();

}
