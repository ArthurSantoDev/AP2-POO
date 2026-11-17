package model;

public class FuncionarioCLT extends Funcionario implements Tributavel {
    private double adicionalBeneficios;

    public FuncionarioCLT(int codigo, String nome, String cpf, double salarioBase, double adicionalBeneficios) {
        super(codigo, nome, cpf, salarioBase);
        if (adicionalBeneficios < 0) {
            throw new IllegalArgumentException("O adicional de benefícios não pode ser negativo");
        }
        this.adicionalBeneficios = adicionalBeneficios;
    }

    public double getAdicionalBeneficios() {
        return adicionalBeneficios;
    }

    public void setAdicionalBeneficios(double adicionalBeneficios) {
        if (adicionalBeneficios < 0) {
            throw new IllegalArgumentException("O adicional de benefícios não pode ser negativo");
        }
        this.adicionalBeneficios = adicionalBeneficios;
    }

    @Override
    public double calcularRemuneracao() {
        return getSalarioBase() + adicionalBeneficios;
    }

    @Override
    public double calcularTributos() {
        return calcularRemuneracao() * 0.12;
    }
}
