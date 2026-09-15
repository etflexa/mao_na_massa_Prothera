package dev.etflexa.iniflex.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatosTest {

    @Test
    @DisplayName("3.3 - formata a data como dd/MM/aaaa")
    void deveFormatarDataNoPadraoBrasileiro() {
        assertEquals("18/10/2000", Formatos.data(LocalDate.of(2000, 10, 18)));
        assertEquals("05/01/1995", Formatos.data(LocalDate.of(1995, 1, 5)));
        assertEquals("02/05/1961", Formatos.data(LocalDate.of(1961, 5, 2)));
    }

    @Test
    @DisplayName("3.3 - usa ponto como separador de milhar e vírgula como decimal")
    void deveFormatarNumeroNoPadraoBrasileiro() {
        assertEquals("2.009,44", Formatos.numero(new BigDecimal("2009.44")));
        assertEquals("1.582,72", Formatos.numero(new BigDecimal("1582.72")));
        assertEquals("19.119,88", Formatos.numero(new BigDecimal("19119.88")));
        assertEquals("1,82", Formatos.numero(new BigDecimal("1.82")));
    }

    @Test
    @DisplayName("3.3 - arredonda os valores com duas casas decimais, como no gabarito")
    void deveArredondarComDuasCasasDecimais() {

        assertEquals("2.210,38", Formatos.numero(new BigDecimal("2210.384")));
        assertEquals("1.740,99", Formatos.numero(new BigDecimal("1740.992")));
        assertEquals("3.319,20", Formatos.numero(new BigDecimal("3319.195")));
        assertEquals("1.767,54", Formatos.numero(new BigDecimal("1767.535")));
        assertEquals("10.819,75", Formatos.numero(new BigDecimal("10819.754")));
        assertEquals("50.906,82", Formatos.numero(new BigDecimal("50906.823")));
    }

    @Test
    @DisplayName("3.3 / 3.11 - formata valores monetários")
    void deveFormatarValorMonetario() {
        assertEquals("R$ 2.009,44", Formatos.moeda(new BigDecimal("2009.44")));
        assertEquals("R$ 50.906,82", Formatos.moeda(new BigDecimal("50906.823")));
    }
}
