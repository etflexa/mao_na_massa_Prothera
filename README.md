# Teste Prático — Iniflex (Desenvolvedor Java Júnior)

Projeto em **Java 21** que resolve o teste prático da etapa *Mão na Massa*. Todos os
requisitos do item 3 estão implementados, com testes automatizados cobrindo cada regra.

## Requisitos × implementação

| Item | O que o enunciado pede | Onde está implementado |
| --- | --- | --- |
| 1 | Classe `Pessoa` com `nome` (String) e `data nascimento` (LocalDate) | `model/Pessoa.java` |
| 2 | Classe `Funcionário` estendendo `Pessoa`, com `salário` (BigDecimal) e `função` (String) | `model/Funcionario.java` |
| 3.1 | Inserir todos os funcionários, na mesma ordem e informações da tabela | `dados/FuncionariosIniciais.java` |
| 3.2 | Remover o funcionário "João" da lista | `service/FuncionarioService#removerPorNome` |
| 3.3 | Imprimir todos os funcionários com data em `dd/mm/aaaa` e números com ponto de milhar e vírgula decimal | `util/Formatos` + `Principal#imprimirTabela` |
| 3.4 | 10% de aumento, atualizando a lista com o novo valor | `model/Funcionario#aplicarAumento` + `service#aplicarAumento` |
| 3.5 | Agrupar os funcionários por função em um `MAP` (chave = função, valor = lista) | `service/FuncionarioService#agruparPorFuncao` |
| 3.6 | Imprimir os funcionários agrupados por função | `Principal` |
| 3.7 | **Não existe no enunciado** (a numeração salta de 3.6 para 3.8) | — |
| 3.8 | Funcionários que fazem aniversário nos meses 10 e 12 | `service#aniversariantesDosMeses(10, 12)` |
| 3.9 | Funcionário com a maior idade, exibindo nome e idade | `service#funcionarioMaisVelho` + `service#idade` |
| 3.10 | Lista de funcionários por ordem alfabética | `service#ordenadosPorNome` |
| 3.11 | Total dos salários dos funcionários | `service#totalSalarios` |
| 3.12 | Quantos salários mínimos ganha cada funcionário (R$ 1.212,00) | `service#quantidadeSalariosMinimos` |

## Como executar

### No VS Code

- **Code Runner** (`Ctrl+Alt+N`, ou botão ▶ *Run Code*): já está configurada em
  `.vscode/settings.json` para rodar a classe `Principal` via Maven.
- **Java extension** (`F5` / *Run Java*): abre direto o `Principal.java` e executa.

### No terminal

```bash
# executa pelo Maven
mvn exec:java

# compila, roda os testes e gera target/teste-pratico-iniflex.jar
mvn clean package

# executa o jar gerado
java -jar target/teste-pratico-iniflex.jar
```

Nesta máquina o JDK e o Maven não estão no `PATH` do sistema ainda (foram instalados
em `~/.local/opt`), então use os caminhos completos ou abra um terminal novo após novo
login:

```bash
~/.local/opt/maven/bin/mvn exec:java
```

## Resultado da execução

Números conferidos contra o gabarito do teste:

- **Total dos salários** (após remover João e aplicar 10%): `R$ 50.906,82`
- **Funcionário com a maior idade**: `Caio` — `65 anos`
- **Salários mínimos**: Alice 2,03 · Arthur 3,70 · Caio 8,93 · Heitor 1,44 · Helena 2,54 ·
  Heloísa 1,46 · Laura 2,74 · Maria 1,82 · Miguel 17,35

<details>
<summary>Saída completa do programa</summary>

```
==============================================================================
3.1 - Funcionários inseridos: 10
==============================================================================
3.2 - Funcionário "João" removido (registros removidos: 1)

==============================================================================
3.3 - Lista de funcionários (data dd/mm/aaaa e valores com ponto de milhar e vírgula decimal)
==============================================================================
Nome                 Data de Nascimento   Salário          Função
Maria                18/10/2000           R$ 2.009,44      Operador
Caio                 02/05/1961           R$ 9.836,14      Coordenador
Miguel               14/10/1988           R$ 19.119,88     Diretor
Alice                05/01/1995           R$ 2.234,68      Recepcionista
Heitor               19/11/1999           R$ 1.582,72      Operador
Arthur               31/03/1993           R$ 4.071,84      Contador
Laura                08/07/1994           R$ 3.017,45      Gerente
Heloísa              24/05/2003           R$ 1.606,85      Eletricista
Helena               02/09/1996           R$ 2.799,93      Gerente

==============================================================================
3.4 - Salários atualizados com aumento de 10%
==============================================================================
Nome                 Data de Nascimento   Salário          Função
Maria                18/10/2000           R$ 2.210,38      Operador
Caio                 02/05/1961           R$ 10.819,75     Coordenador
Miguel               14/10/1988           R$ 21.031,87     Diretor
Alice                05/01/1995           R$ 2.458,15      Recepcionista
Heitor               19/11/1999           R$ 1.740,99      Operador
Arthur               31/03/1993           R$ 4.479,02      Contador
Laura                08/07/1994           R$ 3.319,20      Gerente
Heloísa              24/05/2003           R$ 1.767,54      Eletricista
Helena               02/09/1996           R$ 3.079,92      Gerente

==============================================================================
3.5 / 3.6 - Funcionários agrupados por função
==============================================================================
Contador:
Arthur               31/03/1993           R$ 4.479,02      Contador
Coordenador:
Caio                 02/05/1961           R$ 10.819,75     Coordenador
Diretor:
Miguel               14/10/1988           R$ 21.031,87     Diretor
Eletricista:
Heloísa              24/05/2003           R$ 1.767,54      Eletricista
Gerente:
Laura                08/07/1994           R$ 3.319,20      Gerente
Helena               02/09/1996           R$ 3.079,92      Gerente
Operador:
Maria                18/10/2000           R$ 2.210,38      Operador
Heitor               19/11/1999           R$ 1.740,99      Operador
Recepcionista:
Alice                05/01/1995           R$ 2.458,15      Recepcionista

==============================================================================
3.8 - Aniversariantes dos meses 10 e 12
==============================================================================
Maria                18/10/2000           R$ 2.210,38      Operador
Miguel               14/10/1988           R$ 21.031,87     Diretor

==============================================================================
3.9 - Funcionário com a maior idade
==============================================================================
Nome: Caio | Idade: 65 anos

==============================================================================
3.10 - Funcionários em ordem alfabética
==============================================================================
Alice                05/01/1995           R$ 2.458,15      Recepcionista
Arthur               31/03/1993           R$ 4.479,02      Contador
Caio                 02/05/1961           R$ 10.819,75     Coordenador
Heitor               19/11/1999           R$ 1.740,99      Operador
Helena               02/09/1996           R$ 3.079,92      Gerente
Heloísa              24/05/2003           R$ 1.767,54      Eletricista
Laura                08/07/1994           R$ 3.319,20      Gerente
Maria                18/10/2000           R$ 2.210,38      Operador
Miguel               14/10/1988           R$ 21.031,87     Diretor

==============================================================================
3.11 - Total dos salários
==============================================================================
Total: R$ 50.906,82

==============================================================================
3.12 - Salários mínimos por funcionário (salário mínimo = R$ 1.212,00)
==============================================================================
Alice                2,03
Arthur               3,70
Caio                 8,93
Heitor               1,44
Helena               2,54
Heloísa              1,46
Laura                2,74
Maria                1,82
Miguel               17,35

Fim da execução do teste prático - Iniflex.
```
</details>

## Decisões técnicas

- **`BigDecimal` para dinheiro** (nunca `double`), evitando erros de arredondamento do
  ponto flutuante. O aumento de 10% é uma multiplicação pelo fator `1.10`.
- **Formatação brasileira** com `NumberFormat` e `Locale pt-BR`: ponto como separador de
  milhar e vírgula como decimal, sempre com 2 casas (item 3.3).
- **Arredondamento**: o `NumberFormat` usa `HALF_EVEN` por padrão, que reproduz
  exatamente os valores do gabarito (ex.: `3319,195` → `3.319,20` e `1767,535` → `1.767,54`).
- **Divisão pelo salário mínimo** com escala 2 e `RoundingMode.HALF_UP`, pois
  `BigDecimal#divide` sem escala/modo lança `ArithmeticException` em dízimas periódicas.
- **`TreeMap` no agrupamento por função** (item 3.5): além de atender o requisito de `MAP`,
  garante uma impressão determinística, com as funções em ordem alfabética.
- **Idade recebe a data de referência por parâmetro** (`idade(funcionario, dataReferencia)`),
  o que torna os testes independentes do dia em que são executados; a aplicação passa
  `LocalDate.now()`.
- **`Principal` só imprime; as regras ficam em `FuncionarioService`.** Essa separação
  permite testar cada requisito do item 3 automaticamente, sem capturar a saída do console.
- **`Pessoa` imutável** (nome e data de nascimento não mudam); no `Funcionario` apenas o
  salário é mutável, por causa do item 3.4.

## Testes

14 testes com **JUnit 5** (`mvn test`), cobrindo os 10 funcionários da tabela, remoção,
aumento, agrupamento, aniversariantes, mais velho/idade, ordenação, soma e salários mínimos,
além da formatação de datas e valores:

```
Tests run: 14, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Estrutura do projeto

```
mao_na_massa/
├── pom.xml
├── src/main/java/dev/etflexa/iniflex/
│   ├── Principal.java                     # item 3: executa as ações e imprime
│   ├── model/Pessoa.java                  # item 1
│   ├── model/Funcionario.java             # item 2
│   ├── dados/FuncionariosIniciais.java    # item 3.1: tabela do enunciado
│   ├── service/FuncionarioService.java    # itens 3.2 a 3.12 (regras)
│   └── util/Formatos.java                 # item 3.3: data e números em pt-BR
├── src/test/java/dev/etflexa/iniflex/
│   ├── service/FuncionarioServiceTest.java
│   └── util/FormatosTest.java
└── .vscode/                               # configuração do VS Code (JDK, Maven, Code Runner)
```

## Publicando para enviar o link

```bash
git remote add origin https://github.com/<seu-usuario>/<seu-repositorio>.git
git push -u origin main
```
