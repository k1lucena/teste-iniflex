# Teste Prático de Programação - Iniflex

Implementação completa e modernizada em **Java 21 (LTS)** para o processo de avaliação técnica da Iniflex.

---

## 📋 Tabela de Funcionários da Indústria

| Nome | Data Nascimento | Salário | Função |
| :--- | :---: | :---: | :--- |
| **Maria** | 18/10/2000 | 2009.44 | Operador |
| **João** | 12/05/1990 | 2284.38 | Operador |
| **Caio** | 02/05/1961 | 9836.14 | Coordenador |
| **Miguel** | 14/10/1988 | 19119.88 | Diretor |
| **Alice** | 05/01/1995 | 2234.68 | Recepcionista |
| **Heitor** | 19/11/1999 | 1582.72 | Operador |
| **Arthur** | 31/03/1993 | 4071.84 | Contador |
| **Laura** | 08/07/1994 | 3017.45 | Gerente |
| **Heloísa** | 24/05/2003 | 1606.85 | Eletricista |
| **Helena** | 02/09/1996 | 2799.93 | Gerente |

---

## 🎯 Requisitos Atendidos

- **1.** **Classe `Pessoa`**: atributos `nome` (`String`) e `dataNascimento` (`LocalDate`), encapsulamento e validações de não-nulidade.
- **2.** **Classe `Funcionario`**: estende `Pessoa`, com atributos `salario` (`BigDecimal`) e `funcao` (`String`). Implementa `Comparable<Funcionario>` e formatações no padrão brasileiro.
- **3.** **Classe `Principal`**: orquestra e executa todas as ações:
  - **3.1.** Inserção de todos os funcionários na mesma ordem e informações da tabela acima.
  - **3.2.** Remoção do funcionário **"João"** da lista.
  - **3.3.** Impressão de todos os funcionários com:
    - Data no formato `dd/mm/aaaa` (`18/10/2000`);
    - Valores numéricos formatados com ponto no milhar e vírgula no decimal (`2.009,44`).
  - **3.4.** Aplicação de **10% de aumento de salário**, com atualização da lista de funcionários (`RoundingMode.HALF_UP`).
  - **3.5.** Agrupamento dos funcionários por função em um `Map<String, List<Funcionario>>` preservando a ordem via `LinkedHashMap`.
  - **3.6.** Impressão dos funcionários agrupados por função.
  - **3.8.** Impressão dos funcionários que fazem aniversário nos meses **10 (outubro)** e **12 (dezembro)**.
  - **3.9.** Impressão do funcionário com a **maior idade**, exibindo os atributos **nome e idade**.
  - **3.10.** Impressão da lista de funcionários em **ordem alfabética**.
  - **3.11.** Impressão do **total dos salários** dos funcionários.
  - **3.12.** Impressão de **quantos salários mínimos** ganha cada funcionário, considerando o salário mínimo de **R$ 1.212,00**.

---

## 🛠️ Tecnologias e Decisões de Arquitetura

- **Java 21 (LTS)**: Utilização de recursos modernos da linguagem, incluindo métodos de fábrica `Locale.of("pt", "BR")` (evitando APIs depreciadas).
- **`BigDecimal` & `RoundingMode.HALF_UP`**: Precisão monetária sem perdas de ponto flutuante binário.
- **Thread-Safety na Formatação**: Formatação com `String.format(Locale.of("pt", "BR"), ...)` eliminando instâncias estáticas mutáveis inseguras.
- **Java Streams & Collections API**: Agrupamento eficiente via `Collectors.groupingBy` e filtragens idiomáticas.
- **JUnit 5**: Suíte de testes unitários automatizados validando 100% dos requisitos.
- **Maven Wrapper (`mvnw` / `mvnw.cmd`)**: Build reprodutível sem dependência de instalação prévia do Maven.

---

## 🚀 Como Executar

### Pré-requisitos
- JDK 21 instalado e configurado no ambiente.

### Opção 1: Via Maven Wrapper (Recomendado)

**No Windows (PowerShell / CMD):**
```powershell
# Executar todos os testes unitários
.\mvnw.cmd clean test

# Executar a classe Principal
.\mvnw.cmd exec:java
```

**No Linux / macOS:**
```bash
# Executar todos os testes unitários
./mvnw clean test

# Executar a classe Principal
./mvnw exec:java
```

### Opção 2: Via IDE (IntelliJ IDEA / Eclipse / VS Code)
Basta abrir o projeto na IDE e executar o método `main()` da classe:
`src/main/java/br/com/iniflex/Principal.java`
E para rodar os testes:
`src/test/java/br/com/iniflex/PrincipalTest.java`
