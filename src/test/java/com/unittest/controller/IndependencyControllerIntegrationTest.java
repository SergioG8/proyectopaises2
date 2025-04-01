package com.unittest.controller;

import com.unittest.models.CountryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class IndependencyControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getCountryDetails_shouldReturnCountryResponse() {
        webTestClient.get().uri("/country/CO")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CountryResponse.class)
                .value(response -> {
                    assertEquals("Colombia", response.getCountryName());
                    assertEquals("Bogotá", response.getCapitalName());
                });
    }

    @Test
    public void getCountryDetails_shouldReturnNotFound() {
        webTestClient.get().uri("/country/XX")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CountryResponse.class)
                .value(response -> {
                    assertEquals(null, response.getCountryName());
                });
    }
}