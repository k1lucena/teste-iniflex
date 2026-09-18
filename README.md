# Teste Prático

Implementação completa e modernizada em **Java 21**

---

## Tabela de Funcionários da Indústria

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

## Requisitos Atendidos

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

## Estrutura do Projeto

```text
teste-iniflex/
├── .mvn/wrapper/              # Configurações do Maven Wrapper
├── src/
│   ├── main/java/iniflex/
│   │   ├── Pessoa.java        # Modelo base de Pessoa
│   │   ├── Funcionario.java   # Modelo Funcionario estendendo Pessoa
│   │   └── Principal.java     # Fluxo principal e lógica de negócio
│   └── test/java/iniflex/
│       └── PrincipalTest.java # 11 testes unitários cobrindo todos os requisitos
├── mvnw                       # Script Maven Wrapper para Unix/Linux/macOS
├── mvnw.cmd                   # Script Maven Wrapper para Windows
├── pom.xml                    # Configurações do projeto e dependências Maven
└── README.md                  # Documentação do projeto
```

---

## Tecnologias

- **Java 21**
- **JUnit 5**
- **Maven**

---

## Como Executar

### Pré-requisitos
- **Java JDK 21**

---

### Opção 1: Via Maven

```
# Executar os testes unitários
mvn clean test

# Executar a classe Principal
mvn exec:java
```

---

### Opção 2: Via IDE

1. Abra a pasta raiz do projeto na sua IDE favorita como um projeto Maven.
2. Aguarde a importação das dependências.
3. Para rodar a aplicação: abra o arquivo `src/main/java/iniflex/Principal.java` e execute o método `main()`.
4. Para rodar os testes: abra `src/test/java/iniflex/PrincipalTest.java` e execute a classe de testes.
