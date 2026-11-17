package model;

public class FuncionarioComissionado extends Funcionario implements Tributavel {
    private double percentualComissao;
    private double totalVendas;

    public FuncionarioComissionado(int codigo, String nome, String cpf, double salarioBase,
                                   double percentualComissao, double totalVendas) {
        super(codigo, nome, cpf, salarioBase);
        if (percentualComissao < 0 || percentualComissao > 100) {
            throw new IllegalArgumentException("O percentual de comissão deve estar entre 0 e 100");
        }
        if (totalVendas < 0) {
            throw new IllegalArgumentException("O total de vendas não pode ser negativo");
        }
        this.percentualComissao = percentualComissao;
        this.totalVendas = totalVendas;
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(double percentualComissao) {
        if (percentualComissao < 0 || percentualComissao > 100) {
            throw new IllegalArgumentException("O percentual de comissão deve estar entre 0 e 100");
        }
        this.percentualComissao = percentualComissao;
    }

    public double getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(double totalVendas) {
        if (totalVendas < 0) {
            throw new IllegalArgumentException("O total de vendas não pode ser negativo");
        }
        this.totalVendas = totalVendas;
    }

    @Override
    public double calcularRemuneracao() {
        return getSalarioBase() + totalVendas * (percentualComissao / 100);
    }

    @Override
    public double calcularTributos() {
        return calcularRemuneracao() * 0.10;
    }
}
