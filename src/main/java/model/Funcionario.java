package model;

public abstract class Funcionario implements Identificavel, Tributavel {
    private int codigo;
    private String nome;
    private String cpf;
    private double salarioBase;

    public Funcionario(int codigo, String nome, String cpf, double salarioBase) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("O código deve ser positivo");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório");
        }
        if (!validarCpf(cpf)) {
            throw new IllegalArgumentException("CPF inválido: " + cpf);
        }
        if (salarioBase < 0) {
            throw new IllegalArgumentException("O salário-base não pode ser negativo");
        }
        this.codigo = codigo;
        this.nome = nome.trim();
        this.cpf = cpf;
        this.salarioBase = salarioBase;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório");
        }
        this.nome = nome.trim();
    }

    public String getCpf() {
        return cpf;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase < 0) {
            throw new IllegalArgumentException("O salário-base não pode ser negativo");
        }
        this.salarioBase = salarioBase;
    }

    public abstract double calcularRemuneracao();

    public abstract double calcularTributos();

    public boolean validarCpf(String cpf) {
        if (cpf == null) {
            return false;
        }
        String digitos = cpf.replaceAll("[^0-9]", "");
        if (digitos.length() != 11) {
            return false;
        }
        if (digitos.equals("00000000000") || digitos.equals("11111111111") || digitos.equals("22222222222")) {
            return false;
        }
        return true;
    }
}
