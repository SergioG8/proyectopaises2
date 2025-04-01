package com.unittest.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.unittest.models.Country;
import com.unittest.models.CountryResponse;
import com.unittest.repositories.CountryRepository;
import com.unittest.util.DiferenciaEntreFechas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.time.Period;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IndependencyControllerTest {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private DiferenciaEntreFechas diferenciaEntreFechas;

    @InjectMocks
    private IndependencyController independencyController;

    @BeforeEach
    void setUp() {
        
    }

    @Test
    void getCountryDetails_shouldReturnCountryResponse() {
        String countryId = "CO";
        Country country = new Country();
        country.setCountryName("Colombia");
        country.setCountryCapital("Bogotá");
        country.setCountryIdependenceDate("20/07/1810");

        when(countryRepository.findCountryByIsoCode(countryId)).thenReturn(country);
        when(diferenciaEntreFechas.calculateYearsOfIndependency(country.getCountryIdependenceDate()))
                .thenReturn(Period.of(213, 8, 20));

        ResponseEntity<CountryResponse> response = independencyController.getCountryDetails(countryId);
        CountryResponse countryResponse = response.getBody();

        assertEquals("Colombia", countryResponse.getCountryName());
        assertEquals("Bogotá", countryResponse.getCapitalName());
        assertEquals(213, countryResponse.getYearsOfIndependency());
    }

    @Test
    void getCountryDetails_shouldReturnEmptyResponse_whenCountryNotFound() {
        String countryId = "US";
        when(countryRepository.findCountryByIsoCode(countryId)).thenReturn(null);

        ResponseEntity<CountryResponse> response = independencyController.getCountryDetails(countryId);

        assertEquals(new CountryResponse(), response.getBody());
    }
}