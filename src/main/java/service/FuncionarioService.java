package service;

import java.util.ArrayList;
import java.util.List;

import exception.FuncionarioJaCadastradoException;
import exception.FuncionarioNaoEncontradoException;
import model.Funcionario;
import repository.FuncionarioRepository;

public class FuncionarioService {
    private FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Funcionario funcionario) {
        if (repository.existeCodigo(funcionario.getCodigo())) {
            throw new FuncionarioJaCadastradoException("Já existe funcionário com o código " + funcionario.getCodigo());
        }
        if (repository.existeCpf(funcionario.getCpf())) {
            throw new FuncionarioJaCadastradoException("Já existe funcionário com o CPF " + funcionario.getCpf());
        }
        repository.salvar(funcionario);
    }

    public Funcionario buscarFuncionario(int codigo) {
        return repository.buscarPorCodigo(codigo);
    }

    public Funcionario buscarFuncionario(String cpf) {
        return repository.buscarPorCpf(cpf);
    }

    public List<Funcionario> buscarFuncionario(String nome, boolean buscaExata) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome para busca é obrigatório");
        }
        return repository.buscarPorNome(nome.trim(), buscaExata);
    }

    public void removerFuncionario(int codigo) {
        repository.remover(codigo);
    }

    public List<Funcionario> listarFuncionarios() {
        return repository.listarTodos();
    }

    public double calcularRemuneracao(int codigo) {
        return repository.buscarPorCodigo(codigo).calcularRemuneracao();
    }

    public double calcularFolhaPagamento() {
        double total = 0;
        for (Funcionario f : repository.listarTodos()) {
            total += f.calcularRemuneracao();
        }
        return total;
    }

    public String gerarRelatorio() {
        String relatorio = "=== RELATORIO DE FUNCIONARIOS ===\n";
        for (Funcionario f : repository.listarTodos()) {
            relatorio += "Codigo: " + f.getCodigo()
                    + " | Nome: " + f.getNome()
                    + " | CPF: " + f.getCpf()
                    + " | Tipo: " + f.getClass().getSimpleName()
                    + " | Remuneracao: R$ " + String.format("%.2f", f.calcularRemuneracao()) + "\n";
        }
        relatorio += "Total da folha de pagamento: R$ " + String.format("%.2f", calcularFolhaPagamento());
        return relatorio;
    }
}
