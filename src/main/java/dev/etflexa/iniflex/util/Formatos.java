package dev.etflexa.iniflex.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Formatação exigida pelo item 3.3 do teste:
 * <ul>
 *   <li>data no formato {@code dd/MM/yyyy};</li>
 *   <li>valor numérico com ponto como separador de milhar e vírgula como separador
 *       decimal (padrão brasileiro).</li>
 * </ul>
 */
public final class Formatos {

    /** Localidade brasileira: define os separadores de milhar e decimal. */
    public static final Locale PT_BR = Locale.of("pt", "BR");

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Formatos() {
        // classe utilitária: não deve ser instanciada
    }

    /** Formata a data como {@code dd/MM/yyyy} (ex.: {@code 18/10/2000}). */
    public static String data(LocalDate data) {
        return data == null ? "-" : FORMATO_DATA.format(data);
    }

    /** Formata o número com 2 casas decimais (ex.: {@code 19.119,88}). */
    public static String numero(BigDecimal valor) {
        return formatadorNumerico().format(valor);
    }

    /** Formata o valor monetário (ex.: {@code R$ 19.119,88}). */
    public static String moeda(BigDecimal valor) {
        return "R$ " + numero(valor);
    }

    /**
     * {@link NumberFormat} não é thread-safe, por isso um novo formatador é criado a
     * cada chamada — o custo é irrelevante para o volume deste programa.
     *
     * <p>O arredondamento padrão do {@code NumberFormat} é HALF_EVEN, o que produz
     * exatamente os valores esperados no enunciado (por exemplo, 3319,195 vira
     * 3.319,20 e 1767,535 vira 1.767,54).</p>
     */
    private static NumberFormat formatadorNumerico() {
        NumberFormat formato = NumberFormat.getNumberInstance(PT_BR);
        formato.setMinimumFractionDigits(2);
        formato.setMaximumFractionDigits(2);
        formato.setGroupingUsed(true);
        return formato;
    }
}
