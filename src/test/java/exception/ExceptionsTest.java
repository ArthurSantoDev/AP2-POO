package exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ExceptionsTest {

    @Test
    void deveCriarExcecaoNaoEncontrado() {
        FuncionarioNaoEncontradoException e =
                new FuncionarioNaoEncontradoException("Funcionário não encontrado: código 42");
        assertTrue(e instanceof RuntimeException);
        assertEquals("Funcionário não encontrado: código 42", e.getMessage());
    }

    @Test
    void deveCriarExcecaoJaCadastrado() {
        FuncionarioJaCadastradoException e =
                new FuncionarioJaCadastradoException("Já existe funcionário com o código 7");
        assertTrue(e instanceof RuntimeException);
        assertEquals("Já existe funcionário com o código 7", e.getMessage());
    }
}
