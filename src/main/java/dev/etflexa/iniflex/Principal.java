package dev.etflexa.iniflex;

import dev.etflexa.iniflex.dados.FuncionariosIniciais;
import dev.etflexa.iniflex.model.Funcionario;
import dev.etflexa.iniflex.service.FuncionarioService;
import dev.etflexa.iniflex.util.Formatos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Item 3 do teste prático: classe Principal, que executa todas as ações pedidas,
 * na mesma ordem do enunciado.
 *
 * <p>Observação: o enunciado não possui o item 3.7 — a numeração salta de 3.6 para
 * 3.8 —, portanto não há nada implementado para ele.</p>
 */
public class Principal {

    private static final int LARGURA_TITULO = 78;
    private static final BigDecimal PERCENTUAL_AUMENTO = new BigDecimal("10");
    private static final int MESES_ANIVERSARIO_1 = 10;
    private static final int MESES_ANIVERSARIO_2 = 12;

    public static void main(String[] args) {
        LocalDate hoje = LocalDate.now();

        // 3.1 – inserir todos os funcionários, na mesma ordem e com as informações da tabela
        FuncionarioService service = new FuncionarioService(FuncionariosIniciais.criar());
        titulo("3.1 - Funcionários inseridos: " + service.listar().size());

        // 3.2 – remover o funcionário "João" da lista
        int removidos = service.removerPorNome("João");
        System.out.println("3.2 - Funcionário \"João\" removido (registros removidos: " + removidos + ")");

        // 3.3 – imprimir todos os funcionários com todas as informações
        titulo("3.3 - Lista de funcionários (data dd/mm/aaaa e valores com ponto de milhar e vírgula decimal)");
        imprimirTabela(service.listar());

        // 3.4 – os funcionários receberam 10% de aumento; atualizar a lista com o novo valor
        service.aplicarAumento(PERCENTUAL_AUMENTO);
        titulo("3.4 - Salários atualizados com aumento de 10%");
        imprimirTabela(service.listar());

        // 3.5 e 3.6 – agrupar os funcionários por função em um MAP e imprimir o agrupamento
        Map<String, List<Funcionario>> funcionariosPorFuncao = service.agruparPorFuncao();
        titulo("3.5 / 3.6 - Funcionários agrupados por função");
        funcionariosPorFuncao.forEach((funcao, funcionarios) -> {
            System.out.println(funcao + ":");
            imprimirTabela(funcionarios);
        });

        // 3.8 – imprimir os funcionários que fazem aniversário nos meses 10 e 12
        titulo("3.8 - Aniversariantes dos meses " + MESES_ANIVERSARIO_1 + " e " + MESES_ANIVERSARIO_2);
        imprimirTabela(service.aniversariantesDosMeses(MESES_ANIVERSARIO_1, MESES_ANIVERSARIO_2));

        // 3.9 – imprimir o funcionário com a maior idade (nome e idade)
        titulo("3.9 - Funcionário com a maior idade");
        service.funcionarioMaisVelho().ifPresent(maisVelho -> System.out.printf(
                "Nome: %s | Idade: %d anos%n", maisVelho.getNome(), service.idade(maisVelho, hoje)));

        // 3.10 – imprimir a lista de funcionários por ordem alfabética
        titulo("3.10 - Funcionários em ordem alfabética");
        imprimirTabela(service.ordenadosPorNome());

        // 3.11 – imprimir o total dos salários dos funcionários
        titulo("3.11 - Total dos salários");
        System.out.println("Total: " + Formatos.moeda(service.totalSalarios()));

        // 3.12 – imprimir quantos salários mínimos ganha cada funcionário
        titulo("3.12 - Salários mínimos por funcionário (salário mínimo = "
                + Formatos.moeda(FuncionarioService.SALARIO_MINIMO) + ")");
        service.ordenadosPorNome().forEach(funcionario -> System.out.printf("%-20s %s%n",
                funcionario.getNome(), Formatos.numero(service.quantidadeSalariosMinimos(funcionario))));

        System.out.println();
        System.out.println("Fim da execução do teste prático - Iniflex.");
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("=".repeat(LARGURA_TITULO));
        System.out.println(texto);
        System.out.println("=".repeat(LARGURA_TITULO));
    }

    private static void imprimirTabela(List<Funcionario> funcionarios) {
        System.out.printf("%-20s %-20s %-16s %-15s%n", "Nome", "Data de Nascimento", "Salário", "Função");
        funcionarios.forEach(funcionario -> System.out.printf("%-20s %-20s %-16s %-15s%n",
                funcionario.getNome(),
                Formatos.data(funcionario.getDataNascimento()),
                Formatos.moeda(funcionario.getSalario()),
                funcionario.getFuncao()));
    }
}
