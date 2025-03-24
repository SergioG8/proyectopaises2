package com.unittest.controller;

import com.unittest.TutorialUnitTestApplication;
import com.unittest.models.CountryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TutorialUnitTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")  // Usa configuración de pruebas
public class IndependencyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String buildUrl(String path) {
        return "http://localhost:" + port + path;
    }

    @Test
    void getCountryDetailsWithValidCountryCode() {
        ResponseEntity<CountryResponse> response = restTemplate.getForEntity(
                buildUrl("/api/country/DO"), CountryResponse.class);
        
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Republica Dominicana", response.getBody().getCountryName());
    }

    @Test
    void getCountryDetailsWithInvalidCountryCode() {
        ResponseEntity<CountryResponse> response = restTemplate.getForEntity(
                buildUrl("/api/country/XX"), CountryResponse.class); // Código inválido de prueba
        
        assertEquals(404, response.getStatusCodeValue()); // Esperamos que el endpoint retorne un 404
        
        // Si la API devuelve un cuerpo con mensaje de error
        assertNotNull(response.getBody());
        assertNull(response.getBody().getCountryName()); // Asegurar que no haya un nombre de país
    }
}
