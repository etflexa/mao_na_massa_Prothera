package dev.etflexa.iniflex.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

/**
 * Item 2 do teste prático: funcionário da indústria, que estende {@link Pessoa}
 * acrescentando salário e função.
 */
public class Funcionario extends Pessoa {

    /** Ordem alfabética pelo nome, usada no item 3.10. */
    public static final Comparator<Funcionario> POR_NOME = Comparator.comparing(Funcionario::getNome);

    private static final BigDecimal CEM = new BigDecimal("100");

    private BigDecimal salario;
    private final String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = Objects.requireNonNull(salario, "salário é obrigatório");
        this.funcao = Objects.requireNonNull(funcao, "função é obrigatória");
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = Objects.requireNonNull(salario, "salário é obrigatório");
    }

    public String getFuncao() {
        return funcao;
    }

    /**
     * Item 3.4: aplica um aumento percentual ao salário.
     *
     * <p>Usa {@link BigDecimal} em vez de {@code double} para evitar os erros de
     * arredondamento da aritmética de ponto flutuante — obrigatório em valores
     * monetários.</p>
     *
     * @param percentual percentual de aumento (por exemplo, {@code 10} para 10%)
     */
    public void aplicarAumento(BigDecimal percentual) {
        Objects.requireNonNull(percentual, "percentual é obrigatório");
        BigDecimal fator = BigDecimal.ONE.add(percentual.divide(CEM));
        this.salario = this.salario.multiply(fator);
    }

    @Override
    public String toString() {
        return getNome() + " (" + funcao + ")";
    }
}
