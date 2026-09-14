package dev.etflexa.iniflex.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Item 1 do teste prático: pessoa da indústria.
 *
 * <p>Os atributos são imutáveis porque a identidade de uma pessoa (nome e data de
 * nascimento) não muda ao longo do processamento; apenas o salário do funcionário
 * é atualizado (item 3.4).</p>
 */
public class Pessoa {

    private final String nome;
    private final LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = Objects.requireNonNull(nome, "nome é obrigatório");
        this.dataNascimento = Objects.requireNonNull(dataNascimento, "data de nascimento é obrigatória");
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Duas pessoas são consideradas a mesma quando têm o mesmo nome e a mesma data
     * de nascimento.
     */
    @Override
    public boolean equals(Object outro) {
        if (this == outro) {
            return true;
        }
        if (outro == null || getClass() != outro.getClass()) {
            return false;
        }
        Pessoa pessoa = (Pessoa) outro;
        return nome.equals(pessoa.nome) && dataNascimento.equals(pessoa.dataNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, dataNascimento);
    }
}
