package ru.codinggym.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${exchange.api_secret}")
    private String apiSecret;

    public String getLatestCurrency(String currency) {
        String url = "http://api.exchangeratesapi.io/v1/latest?access_key=" + apiSecret + "&symbols=" + currency;
        String forObject = restTemplate.getForObject(url, String.class);
        return getInfoFromJson(forObject, currency);
    }

    private String getInfoFromJson(String json, String currency) {
        JSONObject jsonObject = new JSONObject(json);
        return String.valueOf(jsonObject.getJSONObject("rates").getFloat(currency));
    }
}
