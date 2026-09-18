package iniflex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de Validação")
public class PrincipalTest {


    @Test
    @DisplayName("3.1 – Deve inserir todos os 10 funcionários na mesma ordem e com as informações da tabela")
    void deveInserirTodosFuncionariosNaOrdemDaTabela_Requisito3_1() {
        Principal principal = new Principal();
        List<Funcionario> lista = principal.getFuncionarios();

        assertEquals(10, lista.size());

        // Verificação dos funcionários conforme tabela original
        assertFuncionario(lista.get(0), "Maria", LocalDate.of(2000, 10, 18), "2009.44", "Operador");
        assertFuncionario(lista.get(1), "João", LocalDate.of(1990, 5, 12), "2284.38", "Operador");
        assertFuncionario(lista.get(2), "Caio", LocalDate.of(1961, 5, 2), "9836.14", "Coordenador");
        assertFuncionario(lista.get(3), "Miguel", LocalDate.of(1988, 10, 14), "19119.88", "Diretor");
        assertFuncionario(lista.get(4), "Alice", LocalDate.of(1995, 1, 5), "2234.68", "Recepcionista");
        assertFuncionario(lista.get(5), "Heitor", LocalDate.of(1999, 11, 19), "1582.72", "Operador");
        assertFuncionario(lista.get(6), "Arthur", LocalDate.of(1993, 3, 31), "4071.84", "Contador");
        assertFuncionario(lista.get(7), "Laura", LocalDate.of(1994, 7, 8), "3017.45", "Gerente");
        assertFuncionario(lista.get(8), "Heloísa", LocalDate.of(2003, 5, 24), "1606.85", "Eletricista");
        assertFuncionario(lista.get(9), "Helena", LocalDate.of(1996, 9, 2), "2799.93", "Gerente");
    }

    @Test
    @DisplayName("3.2 – Deve remover o funcionário João da lista")
    void deveRemoverFuncionarioJoao_Requisito3_2() {
        Principal principal = new Principal();

        boolean removido = principal.removerFuncionario("João");

        assertTrue(removido);
        assertEquals(9, principal.getFuncionarios().size());
        assertFalse(principal.getFuncionarios().stream()
                .anyMatch(f -> f.getNome().equalsIgnoreCase("João")));
    }

    @Test
    @DisplayName("3.3 – Deve formatar data como dd/mm/aaaa e salário com ponto no milhar e vírgula no decimal")
    void deveFormatarDataESalarioNoPadraoBrasileiro_Requisito3_3() {
        Funcionario funcionario = new Funcionario(
                "Maria",
                LocalDate.of(2000, 10, 18),
                new BigDecimal("2009.44"),
                "Operador"
        );

        assertEquals("18/10/2000", funcionario.getDataNascFormatada());
        assertEquals("2.009,44", funcionario.getSalarioFormatado());
        assertEquals(
                "Nome: Maria | Data de Nascimento: 18/10/2000 | Salário: 2.009,44 | Função: Operador",
                funcionario.toString()
        );
    }

    @Test
    @DisplayName("3.4 – Deve aplicar 10% de aumento de salário a todos os funcionários")
    void deveAplicarDezPorCentoDeAumentoDeSalario_Requisito3_4() {
        Principal principal = new Principal();

        principal.aplicarAumentoDezPorcento();

        // Maria: 2009.44 * 1.10 = 2210.384 -> 2210.38
        assertEquals(new BigDecimal("2210.38"), principal.getFuncionarios().get(0).getSalario());
        // Caio: 9836.14 * 1.10 = 10819.754 -> 10819.75
        assertEquals(new BigDecimal("10819.75"), principal.getFuncionarios().get(2).getSalario());
    }

    @Test
    @DisplayName("3.5 e 3.6 – Deve agrupar os funcionários por função em um MAP")
    void deveAgruparFuncionariosPorFuncaoEmMap_Requisito3_5_e_3_6() {
        Principal principal = new Principal();

        Map<String, List<Funcionario>> grupos = principal.agruparPorFuncao();

        assertEquals(7, grupos.size());
        assertEquals(3, grupos.get("Operador").size());
        assertEquals(2, grupos.get("Gerente").size());
        assertEquals(1, grupos.get("Coordenador").size());
        assertEquals(1, grupos.get("Diretor").size());
        assertEquals(1, grupos.get("Recepcionista").size());
        assertEquals(1, grupos.get("Contador").size());
        assertEquals(1, grupos.get("Eletricista").size());
    }

    @Test
    @DisplayName("3.8 – Deve identificar os aniversariantes dos meses 10 (outubro) e 12 (dezembro)")
    void deveFiltrarAniversariantesDosMeses10e12_Requisito3_8() {
        Principal principal = new Principal();

        List<Funcionario> aniversariantes = principal.obterAniversariantes(10, 12);

        assertEquals(2, aniversariantes.size());
        assertTrue(aniversariantes.stream().anyMatch(f -> f.getNome().equals("Maria")));
        assertTrue(aniversariantes.stream().anyMatch(f -> f.getNome().equals("Miguel")));
    }

    @Test
    @DisplayName("3.9 – Deve identificar o funcionário com a maior idade (Caio)")
    void deveIdentificarFuncionarioComMaiorIdadeExibindoNomeEIdade_Requisito3_9() {
        Principal principal = new Principal();

        Funcionario maisVelho = principal.obterFuncionarioMaisVelho();

        assertNotNull(maisVelho);
        assertEquals("Caio", maisVelho.getNome());
        assertEquals(LocalDate.of(1961, 5, 2), maisVelho.getDataNascimento());
        assertTrue(principal.calcularIdade(maisVelho.getDataNascimento()) >= 63);
    }

    @Test
    @DisplayName("3.10 – Deve retornar os funcionários ordenados em ordem alfabética")
    void deveOrdenarFuncionariosPorOrdemAlfabetica_Requisito3_10() {
        Principal principal = new Principal();
        principal.removerFuncionario("João");

        List<Funcionario> ordenados = principal.obterFuncionariosOrdemAlfabetica();

        assertEquals("Alice", ordenados.get(0).getNome());
        assertEquals("Arthur", ordenados.get(1).getNome());
        assertEquals("Caio", ordenados.get(2).getNome());
        assertEquals("Heitor", ordenados.get(3).getNome());
        assertEquals("Helena", ordenados.get(4).getNome());
        assertEquals("Heloísa", ordenados.get(5).getNome());
        assertEquals("Laura", ordenados.get(6).getNome());
        assertEquals("Maria", ordenados.get(7).getNome());
        assertEquals("Miguel", ordenados.get(8).getNome());
    }

    @Test
    @DisplayName("3.11 – Deve calcular o total dos salários dos funcionários corretamente")
    void deveCalcularTotalDosSalariosDosFuncionarios_Requisito3_11() {
        Principal principal = new Principal();

        principal.removerFuncionario("João");
        principal.aplicarAumentoDezPorcento();

        BigDecimal total = principal.calcularTotalSalarios();

        // 2210.38 + 10819.75 + 21031.87 + 2458.15 + 1740.99 + 4479.02 + 3319.20 + 1767.54 + 3079.92 = 50906.82
        assertEquals(new BigDecimal("50906.82"), total);
    }

    @Test
    @DisplayName("3.12 – Deve calcular a quantidade de salários mínimos (R$ 1212.00) que cada funcionário ganha")
    void deveCalcularQuantidadeDeSalariosMinimos_Requisito3_12() {
        Principal principal = new Principal();

        // Maria ganha R$ 2009.44 / 1212.00 = 1.66
        BigDecimal qtdInicial = principal.calcularQuantidadeSalariosMinimos(new BigDecimal("2009.44"));
        assertEquals(new BigDecimal("1.66"), qtdInicial);

        // Após aumento: R$ 2210.38 / 1212.00 = 1.82
        BigDecimal qtdComAumento = principal.calcularQuantidadeSalariosMinimos(new BigDecimal("2210.38"));
        assertEquals(new BigDecimal("1.82"), qtdComAumento);
    }

    @Test
    @DisplayName("Requisitos 1 e 2 – Deve validar igualdade e contratos de Pessoa e Funcionario")
    void deveGarantirContratoEqualsHashCodeETags() {
        Funcionario f1 = new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");
        Funcionario f2 = new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");
        Funcionario f3 = new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador");

        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
        assertNotEquals(f1, f3);
        assertTrue(f1.compareTo(f3) > 0); // "Maria" vem depois de "João"
    }

    private void assertFuncionario(Funcionario f, String nome, LocalDate dataNasc, String salario, String funcao) {
        assertEquals(nome, f.getNome());
        assertEquals(dataNasc, f.getDataNascimento());
        assertEquals(new BigDecimal(salario), f.getSalario());
        assertEquals(funcao, f.getFuncao());
    }

}
