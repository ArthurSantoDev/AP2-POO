package exception;

public class FuncionarioNaoEncontradoException extends RuntimeException {
    public FuncionarioNaoEncontradoException(int codigo) {
        super("Funcionário não encontrado para o código " + codigo);
    }

    public FuncionarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
