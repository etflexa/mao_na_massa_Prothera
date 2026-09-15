package dev.etflexa.iniflex.service;

import dev.etflexa.iniflex.dados.FuncionariosIniciais;
import dev.etflexa.iniflex.model.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FuncionarioServiceTest {

    private static final LocalDate DATA_REFERENCIA = LocalDate.of(2026, 9, 14);

    private FuncionarioService service;

    @BeforeEach
    void iniciar() {
        service = new FuncionarioService(FuncionariosIniciais.criar());
    }

    @Test
    @DisplayName("3.1 - insere os 10 funcionários na ordem da tabela")
    void deveInserirOsFuncionariosNaOrdemDaTabela() {
        List<String> nomes = service.listar().stream().map(Funcionario::getNome).toList();

        assertEquals(
                List.of("Maria", "João", "Caio", "Miguel", "Alice", "Heitor", "Arthur", "Laura", "Heloísa", "Helena"),
                nomes);
    }

    @Test
    @DisplayName("3.2 - remove o funcionário João")
    void deveRemoverOJoao() {
        assertEquals(1, service.removerPorNome("João"));
        assertEquals(9, service.listar().size());
        assertTrue(service.buscarPorNome("João").isEmpty());
    }

    @Test
    @DisplayName("3.2 - remover um nome inexistente não altera a lista")
    void naoDeveRemoverNinguemQuandoONomeNaoExiste() {
        assertEquals(0, service.removerPorNome("Inexistente"));
        assertEquals(10, service.listar().size());
    }

    @Test
    @DisplayName("3.4 - aplica 10% de aumento atualizando os salários da lista")
    void deveAplicarAumentoDeDezPorCento() {
        service.aplicarAumento(new BigDecimal("10"));

        assertEquals(0, new BigDecimal("2210.384").compareTo(service.buscarPorNome("Maria").orElseThrow().getSalario()));
        assertEquals(0, new BigDecimal("21031.868").compareTo(service.buscarPorNome("Miguel").orElseThrow().getSalario()));
        assertEquals(0, new BigDecimal("1740.992").compareTo(service.buscarPorNome("Heitor").orElseThrow().getSalario()));
    }

    @Test
    @DisplayName("3.5 / 3.6 - agrupa por função em um MAP")
    void deveAgruparPorFuncao() {
        service.removerPorNome("João");

        Map<String, List<Funcionario>> porFuncao = service.agruparPorFuncao();

        assertEquals(
                Set.of("Contador", "Coordenador", "Diretor", "Eletricista", "Gerente", "Operador", "Recepcionista"),
                porFuncao.keySet());
        assertEquals(List.of("Maria", "Heitor"), nomesDe(porFuncao.get("Operador")));
        assertEquals(List.of("Laura", "Helena"), nomesDe(porFuncao.get("Gerente")));
    }

    @Test
    @DisplayName("3.8 - lista aniversariantes dos meses 10 e 12")
    void deveListarAniversariantesDeOutubroEDezembro() {
        assertEquals(List.of("Maria", "Miguel"), nomesDe(service.aniversariantesDosMeses(10, 12)));
    }

    @Test
    @DisplayName("3.9 - identifica o funcionário mais velho e sua idade")
    void deveIdentificarOMaisVelhoEASuaIdade() {
        Funcionario maisVelho = service.funcionarioMaisVelho().orElseThrow();

        assertEquals("Caio", maisVelho.getNome());
        assertEquals(65, service.idade(maisVelho, DATA_REFERENCIA));

        assertEquals(64, service.idade(maisVelho, LocalDate.of(2026, 5, 1)));
    }

    @Test
    @DisplayName("3.10 - ordena a lista por nome")
    void deveOrdenarPorNome() {
        assertEquals(
                List.of("Alice", "Arthur", "Caio", "Heitor", "Helena", "Heloísa", "João", "Laura", "Maria", "Miguel"),
                nomesDe(service.ordenadosPorNome()));
    }

    @Test
    @DisplayName("3.11 - soma os salários após a remoção do João e o aumento de 10%")
    void deveSomarOsSalarios() {
        service.removerPorNome("João");
        service.aplicarAumento(new BigDecimal("10"));

        assertEquals(0, new BigDecimal("50906.823").compareTo(service.totalSalarios()));
    }

    @Test
    @DisplayName("3.12 - calcula quantos salários mínimos cada funcionário recebe")
    void deveCalcularAQuantidadeDeSalariosMinimos() {
        service.removerPorNome("João");
        service.aplicarAumento(new BigDecimal("10"));

        assertEquals(0, new BigDecimal("1.82").compareTo(salariosMinimosDe("Maria")));
        assertEquals(0, new BigDecimal("8.93").compareTo(salariosMinimosDe("Caio")));
        assertEquals(0, new BigDecimal("17.35").compareTo(salariosMinimosDe("Miguel")));
        assertEquals(0, new BigDecimal("2.74").compareTo(salariosMinimosDe("Laura")));
        assertEquals(0, new BigDecimal("3.70").compareTo(salariosMinimosDe("Arthur")));
    }

    private BigDecimal salariosMinimosDe(String nome) {
        return service.quantidadeSalariosMinimos(service.buscarPorNome(nome).orElseThrow());
    }

    private static List<String> nomesDe(List<Funcionario> funcionarios) {
        return funcionarios.stream().map(Funcionario::getNome).toList();
    }
}
