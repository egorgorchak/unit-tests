package ru.codinggym.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final RestTemplate restTemplate;

    @Value("${exchange.api_secret}")
    private String apiSecret;

    public String getLatestCurrency() {
        String url = "http://api.exchangeratesapi.io/v1/latest?access_key=" + apiSecret + "&symbols=RUB";
        String forObject = restTemplate.getForObject(url, String.class);
        return getInfoFromJson(forObject);
    }

    private String getInfoFromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        return String.valueOf(jsonObject.getJSONObject("rates").getFloat("RUB"));
    }
}
