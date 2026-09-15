package dev.etflexa.iniflex.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public class Funcionario extends Pessoa {

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
