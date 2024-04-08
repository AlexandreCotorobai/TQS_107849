package tqs.hw1.bustickets.services;

import java.util.ArrayList;
import java.util.List;

import org.json.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

import tqs.hw1.bustickets.repositories.CurrencyRepository;
import tqs.hw1.bustickets.entities.Currency;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;
    private RestTemplate restTemplate;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, RestTemplateBuilder restTemplateBuilder) {
        this.currencyRepository = currencyRepository;
        this.restTemplate = restTemplateBuilder.build();
    }
    @Override
    public List<String> getCurrencyList() {
        List<String> currencyList = new ArrayList<>();
        String url = "https://twelve-data1.p.rapidapi.com/forex_pairs?currency_base=EUR&format=json";
    
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "3415486a66mshd23ed060781a945p1b90cajsnd77d659a2fa6");
        headers.set("X-RapidAPI-Host", "twelve-data1.p.rapidapi.com");
    
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
    
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        try {
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONArray dataArray = jsonObject.getJSONArray("data");
    
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject currencyObject = dataArray.getJSONObject(i);
                String currencySymbol = currencyObject.getString("symbol").split("/")[1];
                currencyList.add(currencySymbol);
            }
    
            return currencyList;
        } catch (JSONException e) {
            e.printStackTrace();
            return currencyList;
        }
    }

    @Override
    public double getCurrencyRate(String currencyTo) {
        Optional<Currency> currencyOptional = currencyRepository.findById(currencyTo);

        if (currencyOptional.isPresent()) {
            return currencyOptional.get().getEurRate();
        }

        String url = "https://twelve-data1.p.rapidapi.com/exchange_rate?symbol=EUR%2F"+ currencyTo;
    
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "3415486a66mshd23ed060781a945p1b90cajsnd77d659a2fa6");
        headers.set("X-RapidAPI-Host", "twelve-data1.p.rapidapi.com");
    
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
    
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        try {
            JSONObject jsonObject = new JSONObject(response.getBody());
            double rate = jsonObject.getDouble("rate");
    
            return rate;
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }
}