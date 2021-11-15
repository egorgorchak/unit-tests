package ru.codinggym.integration;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.codinggym.unit.service.CurrencyService;
import ru.codinggym.util.TestUtility;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CurrencyServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8888);
    @Autowired
    private CurrencyService currencyService;

    @Test
    public void getLatestCurrency() throws IOException {
        wireMockRule.start();
        wireMockRule.stubFor(get(urlEqualTo("/v1/latest?symbols=RUB&access_key=someAppId"))
                .willReturn(aResponse()
                        .withBody(TestUtility.getFileFromClasspath("classpath:exchange.json"))
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        String latestCurrency = currencyService.getLatestCurrency();
        assertThat(latestCurrency, is("83.41958"));
        wireMockRule.stop();
    }
}