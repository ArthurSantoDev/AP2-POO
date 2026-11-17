package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.FuncionarioNaoEncontradoException;
import model.Funcionario;

public class FuncionarioRepository {
    private Map<Integer, Funcionario> funcionarios = new HashMap<>();

    public void salvar(Funcionario funcionario) {
        funcionarios.put(funcionario.getCodigo(), funcionario);
    }

    public Funcionario buscarPorCodigo(int codigo) {
        Funcionario funcionario = funcionarios.get(codigo);
        if (funcionario == null) {
            throw new FuncionarioNaoEncontradoException("Funcionário não encontrado: código " + codigo);
        }
        return funcionario;
    }

    public Funcionario buscarPorCpf(String cpf) {
        for (Funcionario f : funcionarios.values()) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }
        throw new FuncionarioNaoEncontradoException("Funcionário não encontrado: CPF " + cpf);
    }

    public List<Funcionario> buscarPorNome(String nome, boolean buscaExata) {
        List<Funcionario> resultado = new ArrayList<>();
        for (Funcionario f : funcionarios.values()) {
            if (buscaExata) {
                if (f.getNome().equalsIgnoreCase(nome)) {
                    resultado.add(f);
                }
            } else {
                if (f.getNome().toLowerCase().contains(nome.toLowerCase())) {
                    resultado.add(f);
                }
            }
        }
        return resultado;
    }

    public boolean existeCodigo(int codigo) {
        return funcionarios.containsKey(codigo);
    }

    public boolean existeCpf(String cpf) {
        for (Funcionario f : funcionarios.values()) {
            if (f.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    public List<Funcionario> listarTodos() {
        return new ArrayList<>(funcionarios.values());
    }

    public void remover(int codigo) {
        if (funcionarios.remove(codigo) == null) {
            throw new FuncionarioNaoEncontradoException("Funcionário não encontrado: código " + codigo);
        }
    }
}
