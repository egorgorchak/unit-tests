package ru.codinggym.unit.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import ru.codinggym.util.TestUtility;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CurrencyServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8888);
    @Autowired
    private CurrencyService currencyService;


    @Test
    void getLatestCurrency() throws IOException {
        wireMockRule.start();
        wireMockRule.stubFor(get(urlEqualTo("/v1/latest?symbols=RUB&access_key=someAppId"))
                .willReturn(aResponse()
                        .withBody(TestUtility.getFileFromClasspath("classpath:exchange.json"))
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        String latestCurrency = currencyService.getLatestCurrency();
        MatcherAssert.assertThat(latestCurrency, Is.is("83.41958"));
        wireMockRule.stop();
    }
}