package br.ce.wcaquino.taskbackend.utils;


import org.junit.Test;

import java.time.LocalDate;

import static br.ce.wcaquino.taskbackend.utils.DateUtils.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateUtilsTest {


    @Test
    public void deveRetornarTrueParaDataFutura() {
        LocalDate date = LocalDate.now().plusDays(1);
        assertTrue(isEqualOrFutureDate(date));

    }
    @Test
    public void deveRetornarFalseParaDataFutura() {
        LocalDate date = LocalDate.now().plusDays(-1);
        assertFalse(isEqualOrFutureDate(date));

    }
}
