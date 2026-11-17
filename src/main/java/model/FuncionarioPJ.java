package model;

public class FuncionarioPJ extends Funcionario implements Tributavel {
    private double valorPorProjeto;
    private int quantidadeProjetos;

    public FuncionarioPJ(int codigo, String nome, String cpf, double valorPorProjeto, int quantidadeProjetos) {
        super(codigo, nome, cpf, 0);
        if (valorPorProjeto < 0) {
            throw new IllegalArgumentException("O valor por projeto não pode ser negativo");
        }
        if (quantidadeProjetos < 0) {
            throw new IllegalArgumentException("A quantidade de projetos não pode ser negativa");
        }
        this.valorPorProjeto = valorPorProjeto;
        this.quantidadeProjetos = quantidadeProjetos;
    }

    public double getValorPorProjeto() {
        return valorPorProjeto;
    }

    public void setValorPorProjeto(double valorPorProjeto) {
        if (valorPorProjeto < 0) {
            throw new IllegalArgumentException("O valor por projeto não pode ser negativo");
        }
        this.valorPorProjeto = valorPorProjeto;
    }

    public int getQuantidadeProjetos() {
        return quantidadeProjetos;
    }

    public void setQuantidadeProjetos(int quantidadeProjetos) {
        if (quantidadeProjetos < 0) {
            throw new IllegalArgumentException("A quantidade de projetos não pode ser negativa");
        }
        this.quantidadeProjetos = quantidadeProjetos;
    }

    @Override
    public double calcularRemuneracao() {
        return valorPorProjeto * quantidadeProjetos;
    }

    @Override
    public double calcularTributos() {
        return calcularRemuneracao() * 0.06;
    }
}
