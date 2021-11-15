package ru.codinggym.unit.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {
    private final RestTemplate restTemplate;
    private final String apiSecret;
    private final String url;

    @Autowired
    public CurrencyService(RestTemplate restTemplate,
                           @Value("${exchange.api_secret}") String apiSecret,
                           @Value("${exchange.url}") String url) {
        this.restTemplate = restTemplate;
        this.apiSecret = apiSecret;
        this.url = url;
    }

    public String getLatestCurrency() {
        String forObject = restTemplate.getForObject(url + "/v1/latest?symbols=RUB&access_key=" + apiSecret, String.class);
        return getInfoFromJson(forObject);
    }

    private String getInfoFromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        return String.valueOf(jsonObject.getJSONObject("rates").getFloat("RUB"));
    }
}
