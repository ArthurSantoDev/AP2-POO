package exception;

public class FuncionarioJaCadastradoException extends RuntimeException {
    public FuncionarioJaCadastradoException(int codigo) {
        super("Funcionário já cadastrado com o código " + codigo);
    }

    public FuncionarioJaCadastradoException(String mensagem) {
        super(mensagem);
    }
}
