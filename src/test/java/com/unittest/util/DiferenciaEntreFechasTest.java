package com.unittest.util;

import com.unittest.util.DiferenciaEntreFechas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Period;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

class DiferenciaEntreFechasTest {

    @Autowired
    DiferenciaEntreFechas diferenciaEntreFechas;

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    void calculateYearsOfIndependency() {
        diferenciaEntreFechas = new DiferenciaEntreFechas();
        String fechaIndependencia = "27/02/1844";
        LocalDate today = LocalDate.now();
        Period resultado = diferenciaEntreFechas.calculateYearsOfIndependency(fechaIndependencia);
        //System.out.println(resultado.getYears());
        //System.out.println(resultado.getMonths());
        //System.out.println(resultado.getDays());
        
        Assertions.assertEquals(0,resultado.getMonths() );
        Assertions.assertEquals(24,resultado.getDays() );
        Assertions.assertEquals(181,resultado.getYears() );
    }

    /**
     * Test of calculateYearsOfIndependency method, of class DiferenciaEntreFechas.
     */
    @Test
    public void testCalculateYearsOfIndependency() {
        System.out.println("calculateYearsOfIndependency");
        String independenceDay = "";
        DiferenciaEntreFechas instance = new DiferenciaEntreFechas();
        Period expResult = null;
        Period result = instance.calculateYearsOfIndependency(independenceDay);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}