package dev.etflexa.iniflex.service;

import dev.etflexa.iniflex.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Regras de negócio do item 3 do teste prático.
 *
 * <p>A classe foi separada da {@code Principal} para que cada requisito possa ser
 * testado automaticamente (ver {@code FuncionarioServiceTest}) — a saída no console
 * fica apenas na classe Principal.</p>
 */
public class FuncionarioService {

    /** Item 3.12: salário mínimo considerado no teste. */
    public static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    /** Item 3.9: quem nasceu primeiro é o mais velho. */
    private static final Comparator<Funcionario> MAIS_VELHO_PRIMEIRO =
            Comparator.comparing(Funcionario::getDataNascimento);

    private final List<Funcionario> funcionarios;

    public FuncionarioService(List<Funcionario> funcionarios) {
        Objects.requireNonNull(funcionarios, "lista de funcionários é obrigatória");
        this.funcionarios = new ArrayList<>(funcionarios);
    }

    /** Cópia imutável da lista, na ordem atual. */
    public List<Funcionario> listar() {
        return List.copyOf(funcionarios);
    }

    public Optional<Funcionario> buscarPorNome(String nome) {
        return funcionarios.stream()
                .filter(funcionario -> funcionario.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    /**
     * Item 3.2: remove o funcionário com o nome informado (sem diferenciar
     * maiúsculas de minúsculas, para não depender de acentuação/capitalização).
     *
     * @return quantos funcionários foram removidos
     */
    public int removerPorNome(String nome) {
        int quantidadeAntes = funcionarios.size();
        funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase(nome));
        return quantidadeAntes - funcionarios.size();
    }

    /**
     * Item 3.4: aplica o aumento percentual a todos os funcionários, atualizando a
     * lista com os novos valores.
     */
    public void aplicarAumento(BigDecimal percentual) {
        funcionarios.forEach(funcionario -> funcionario.aplicarAumento(percentual));
    }

    /**
     * Item 3.5: agrupa os funcionários por função.
     *
     * <p>O {@link TreeMap} mantém as chaves em ordem alfabética, o que torna a
     * impressão do item 3.6 determinística e previsível.</p>
     */
    public Map<String, List<Funcionario>> agruparPorFuncao() {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao, TreeMap::new, Collectors.toList()));
    }

    /**
     * Item 3.8: funcionários que fazem aniversário em qualquer um dos meses
     * informados, mantendo a ordem atual da lista.
     */
    public List<Funcionario> aniversariantesDosMeses(int... meses) {
        Set<Integer> mesesAlvo = Arrays.stream(meses).boxed().collect(Collectors.toSet());
        return funcionarios.stream()
                .filter(funcionario -> mesesAlvo.contains(funcionario.getDataNascimento().getMonthValue()))
                .toList();
    }

    /**
     * Item 3.9: funcionário com a maior idade (data de nascimento mais antiga).
     *
     * <p>Retorna {@link Optional} porque a lista pode estar vazia. Em caso de empate,
     * devolve o primeiro encontrado na ordem da lista.</p>
     */
    public Optional<Funcionario> funcionarioMaisVelho() {
        return funcionarios.stream().min(MAIS_VELHO_PRIMEIRO);
    }

    /**
     * Item 3.9: idade em anos completos na data de referência.
     *
     * @param dataReferencia data usada no cálculo (na aplicação, a data de hoje);
     *                       recebida por parâmetro para permitir testes determinísticos
     */
    public int idade(Funcionario funcionario, LocalDate dataReferencia) {
        return Period.between(funcionario.getDataNascimento(), dataReferencia).getYears();
    }

    /** Item 3.10: funcionários em ordem alfabética pelo nome. */
    public List<Funcionario> ordenadosPorNome() {
        return funcionarios.stream().sorted(Funcionario.POR_NOME).toList();
    }

    /** Item 3.11: soma dos salários (após o aumento, pois usa a lista atualizada). */
    public BigDecimal totalSalarios() {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Item 3.12: quantos salários mínimos o funcionário recebe, com 2 casas decimais.
     *
     * <p>A divisão precisa informar escala e modo de arredondamento, caso contrário
     * {@link BigDecimal#divide(BigDecimal)} lança {@link ArithmeticException} quando
     * o resultado é uma dízima periódica.</p>
     */
    public BigDecimal quantidadeSalariosMinimos(Funcionario funcionario) {
        return funcionario.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
    }
}
