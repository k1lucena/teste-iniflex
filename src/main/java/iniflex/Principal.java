package iniflex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

// Requisito 3
public class Principal {

    private static final Locale LOCALE_PT_BR = Locale.of("pt", "BR");
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
    private static final BigDecimal FATOR_AUMENTO = new BigDecimal("1.1");

    private final List<Funcionario> funcionarios;

    public Principal(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Principal() {
        funcionarios = criarListaFuncionarios();
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    // Requisito 3.1
    private List<Funcionario> criarListaFuncionarios() {
        List<Funcionario> lista = new ArrayList<>();

        lista.add(new Funcionario(
                "Maria", LocalDate.of(2000, 10, 18),
                new BigDecimal("2009.44"), "Operador"));

        lista.add(new Funcionario(
                "João", LocalDate.of(1990, 5, 12),
                new BigDecimal("2284.38"), "Operador"));

        lista.add(new Funcionario(
                "Caio", LocalDate.of(1961, 5, 2),
                new BigDecimal("9836.14"), "Coordenador"));

        lista.add(new Funcionario(
                "Miguel", LocalDate.of(1988, 10, 14),
                new BigDecimal("19119.88"), "Diretor"));

        lista.add(new Funcionario(
                "Alice", LocalDate.of(1995, 1, 5),
                new BigDecimal("2234.68"), "Recepcionista"));

        lista.add(new Funcionario(
                "Heitor", LocalDate.of(1999, 11, 19),
                new BigDecimal("1582.72"), "Operador"));

        lista.add(new Funcionario(
                "Arthur", LocalDate.of(1993, 3, 31),
                new BigDecimal("4071.84"), "Contador"));

        lista.add(new Funcionario(
                "Laura", LocalDate.of(1994, 7, 8),
                new BigDecimal("3017.45"), "Gerente"));

        lista.add(new Funcionario(
                "Heloísa", LocalDate.of(2003, 5, 24),
                new BigDecimal("1606.85"), "Eletricista"));

        lista.add(new Funcionario(
                "Helena", LocalDate.of(1996, 9, 2),
                new BigDecimal("2799.93"), "Gerente"));

        return lista;
    }

    // Requisito 3.2
    public boolean removerFuncionario(String nome) {
        return funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase(nome)
        );
    }

    // Requisito 3.3
    public void imprimirFuncionarios() {
        imprimirTitulo("Impressão de todos os funcionarios: ");
        funcionarios.forEach(System.out::println);
    }

    // Requisito 3.4
    public void aplicarAumentoDezPorcento() {
        funcionarios.forEach(funcionario -> {
            BigDecimal novoSalario = funcionario.getSalario()
                    .multiply(FATOR_AUMENTO)
                    .setScale(2, RoundingMode.HALF_UP);

            funcionario.setSalario(novoSalario);
        });
    }

    // Requisito 3.5
    public Map<String, List<Funcionario>> agruparPorFuncao() {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(
                        Funcionario::getFuncao,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));
    }

    // Requisito 3.6
    public void imprimirAgrupadosPorFuncao() {
        imprimirTitulo("Funcionários agrupados por função: ");
        agruparPorFuncao().forEach((funcao, lista) -> {
            System.out.println("\n" + funcao + ":");
            lista.forEach(funcionario -> System.out.println("  " + funcionario));
        });
    }

    // Requisito 3.8
    public List<Funcionario> obterAniversariantes(int... meses) {
        return funcionarios.stream()
                .filter(funcionario -> {
                    int mesNascimento = funcionario.getDataNascimento().getMonthValue();
                    for(int mes : meses) {
                        if (mesNascimento == mes) return true;
                    }
                    return false;
                }).toList();
    }

    public void imprimirAniversariantes() {
        imprimirTitulo("Aniversáriantes de out e dez");

        obterAniversariantes(10, 12).forEach(System.out::println);
    }

    // Requisito 3.9
    public Funcionario obterFuncionarioMaisVelho() {
        return funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);
    }

    public int calcularIdade(LocalDate dataNasc) {
        return Period.between(dataNasc, LocalDate.now()).getYears();
    }

    public void imprimirFuncionarioMaisVelho() {
        imprimirTitulo("Funcionário mais velho: ");

        Funcionario maisVelho = obterFuncionarioMaisVelho();
        if (maisVelho != null) {
            int idade = calcularIdade(maisVelho.getDataNascimento());
            System.out.printf("Nome: %s | Idade: %d anos%n", maisVelho.getNome(), idade);
        }
    }

    // Requisito 3.10
    public List<Funcionario> obterFuncionariosOrdemAlfabetica() {
        return funcionarios.stream().sorted().toList();
    }

    public void imprimirOrdemAlfabetica() {
        imprimirTitulo("Lista por ordem alfabética");
        obterFuncionariosOrdemAlfabetica().forEach(System.out::println);
    }

    //Requisito 3.11
    public BigDecimal calcularTotalSalarios() {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public void imprimirTotalSalarios() {
        imprimirTitulo("Total Salários dos Funcionários");
        BigDecimal total = calcularTotalSalarios();
        System.out.printf(LOCALE_PT_BR, "Total dos Salários: R$ %,.2f%n", total);
    }

    // Requisito 3.12
    public BigDecimal calcularQuantidadeSalariosMinimos(BigDecimal salario) {
        return salario.divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
    }

    public void imprimirSalariosMinimos() {
        imprimirTitulo("Quantidade de salários minimos por funcionário: ");

        funcionarios.forEach(funcionario -> {
            BigDecimal quantidade = calcularQuantidadeSalariosMinimos(funcionario.getSalario());
            System.out.printf(
                    LOCALE_PT_BR,
                    "%s ganha %,.2f salários mínimos%n",
                    funcionario.getNome(),
                    quantidade
            );
        });
    }

    public void imprimirTitulo(String titulo) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println(titulo);
        System.out.println("=".repeat(80));
    }

    public static void main(String[] args) {
        Principal principal = new Principal();

        // 3.1 – Inserir todos os funcionários (realizado no construtor)
        principal.imprimirFuncionarios();

        // 3.2 – Remover o funcionário da lista
        principal.imprimirTitulo("Remove João");
        principal.removerFuncionario("João");

        // 3.3 – Imprimir todos os funcionários
        principal.imprimirFuncionarios();

        // 3.4 – Aplicar aumento de 10%
        principal.imprimirTitulo("Aplica aumento 10%");
        principal.aplicarAumentoDezPorcento();

        // 3.5 e 3.6 – Agrupar por função em um MAP e imprimir agrupados
        principal.imprimirAgrupadosPorFuncao();

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12
        principal.imprimirAniversariantes();

        // 3.9 – Imprimir o funcionário com a maior idade
        principal.imprimirFuncionarioMaisVelho();

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética
        principal.imprimirOrdemAlfabetica();

        // 3.11 – Imprimir o total dos salários dos funcionários
        principal.imprimirTotalSalarios();

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário
        principal.imprimirSalariosMinimos();
    }
}
