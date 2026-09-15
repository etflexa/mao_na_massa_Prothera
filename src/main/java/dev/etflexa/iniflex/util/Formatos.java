package dev.etflexa.iniflex.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Formatos {

    public static final Locale PT_BR = Locale.of("pt", "BR");

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Formatos() {

    }

    public static String data(LocalDate data) {
        return data == null ? "-" : FORMATO_DATA.format(data);
    }

    public static String numero(BigDecimal valor) {
        return formatadorNumerico().format(valor);
    }

    public static String moeda(BigDecimal valor) {
        return "R$ " + numero(valor);
    }

    private static NumberFormat formatadorNumerico() {
        NumberFormat formato = NumberFormat.getNumberInstance(PT_BR);
        formato.setMinimumFractionDigits(2);
        formato.setMaximumFractionDigits(2);
        formato.setGroupingUsed(true);
        return formato;
    }
}
