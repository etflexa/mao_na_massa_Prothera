package dev.etflexa.iniflex;

import dev.etflexa.iniflex.dados.FuncionariosIniciais;
import dev.etflexa.iniflex.model.Funcionario;
import dev.etflexa.iniflex.service.FuncionarioService;
import dev.etflexa.iniflex.util.Formatos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Principal {

    private static final int LARGURA_TITULO = 78;
    private static final BigDecimal PERCENTUAL_AUMENTO = new BigDecimal("10");
    private static final int MESES_ANIVERSARIO_1 = 10;
    private static final int MESES_ANIVERSARIO_2 = 12;

    public static void main(String[] args) {
        LocalDate hoje = LocalDate.now();

        FuncionarioService service = new FuncionarioService(FuncionariosIniciais.criar());
        titulo("3.1 - Funcionários inseridos: " + service.listar().size());

        int removidos = service.removerPorNome("João");
        System.out.println("3.2 - Funcionário \"João\" removido (registros removidos: " + removidos + ")");

        titulo("3.3 - Lista de funcionários (data dd/mm/aaaa e valores com ponto de milhar e vírgula decimal)");
        imprimirTabela(service.listar());

        service.aplicarAumento(PERCENTUAL_AUMENTO);
        titulo("3.4 - Salários atualizados com aumento de 10%");
        imprimirTabela(service.listar());

        Map<String, List<Funcionario>> funcionariosPorFuncao = service.agruparPorFuncao();
        titulo("3.5 / 3.6 - Funcionários agrupados por função");
        funcionariosPorFuncao.forEach((funcao, funcionarios) -> {
            System.out.println(funcao + ":");
            imprimirTabela(funcionarios);
        });

        titulo("3.8 - Aniversariantes dos meses " + MESES_ANIVERSARIO_1 + " e " + MESES_ANIVERSARIO_2);
        imprimirTabela(service.aniversariantesDosMeses(MESES_ANIVERSARIO_1, MESES_ANIVERSARIO_2));

        titulo("3.9 - Funcionário com a maior idade");
        service.funcionarioMaisVelho().ifPresent(maisVelho -> System.out.printf(
                "Nome: %s | Idade: %d anos%n", maisVelho.getNome(), service.idade(maisVelho, hoje)));

        titulo("3.10 - Funcionários em ordem alfabética");
        imprimirTabela(service.ordenadosPorNome());

        titulo("3.11 - Total dos salários");
        System.out.println("Total: " + Formatos.moeda(service.totalSalarios()));

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
