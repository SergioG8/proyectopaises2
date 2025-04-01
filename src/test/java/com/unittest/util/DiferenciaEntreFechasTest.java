package com.unittest.util;
import org.junit.jupiter.api.Test;
import java.time.Period;
import static org.junit.jupiter.api.Assertions.assertEquals;
class DiferenciaEntreFechasTest {
    private final DiferenciaEntreFechas diferenciaEntreFechas = new DiferenciaEntreFechas();

    @Test
    void calculateYearsOfIndependency_shouldReturnCorrectPeriod() {
        String independenceDate = "20/07/1810"; 

        Period period = diferenciaEntreFechas.calculateYearsOfIndependency(independenceDate);

        assertEquals(214, period.getYears());
    }

    @Test
    void calculateYearsOfIndependency_shouldHandleDifferentDateFormats() {
        // Arrange
        String independenceDate = "1/1/2000";

        // Act
        Period period = diferenciaEntreFechas.calculateYearsOfIndependency(independenceDate);

        // Assert
        assertEquals(24, period.getYears());
    }
    @Test
    void calculateYearsOfIndependency_shouldHandleCurrentDate() {
        //Arrange
        String independenceDate = "20/12/2023";

        //Act
        Period period = diferenciaEntreFechas.calculateYearsOfIndependency(independenceDate);

        //Assert
        assertEquals(1, period.getYears());
    }
}