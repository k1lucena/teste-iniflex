package iniflex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

// Requisito 2
public class Funcionario extends Pessoa implements Comparable<Funcionario> {

    private static final Locale LOCALE_PT_BR = Locale.of("pt", "BR");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    // Requisito 3.3
    public String getDataNascFormatada() {
        return getDataNascimento().format(DATE_FORMATTER);
    }

    // Requisito 3.3
    public String getSalarioFormatado() {
        return String.format(LOCALE_PT_BR, "%,.2f", salario);
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(salario, that.salario) && Objects.equals(funcao, that.funcao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salario, funcao);
    }

    @Override
    public String toString() {
        return String.format(
                "Nome: %s | Data de Nascimento: %s | Salário: %s | Função: %s",
                getNome(),
                getDataNascFormatada(),
                getSalarioFormatado(),
                getFuncao()
        );
    }

    @Override
    public int compareTo(Funcionario o) {
        return this.getNome().compareToIgnoreCase(o.getNome());
    }
}
