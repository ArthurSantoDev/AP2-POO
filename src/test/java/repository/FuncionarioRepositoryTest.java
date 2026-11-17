package repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.FuncionarioNaoEncontradoException;
import model.FuncionarioCLT;

class FuncionarioRepositoryTest {
    private FuncionarioRepository repository;

    @BeforeEach
    void setUp() {
        repository = new FuncionarioRepository();
        repository.salvar(new FuncionarioCLT(1, "Ana", "52998224725", 2000, 100));
        repository.salvar(new FuncionarioCLT(2, "Bruno", "11144477735", 1800, 50));
    }

    @Test
    void deveSalvarEBuscarPorCodigo() {
        assertEquals("Ana", repository.buscarPorCodigo(1).getNome());
    }

    @Test
    void deveLancarExcecaoAoBuscarCodigoInexistente() {
        assertThrows(FuncionarioNaoEncontradoException.class, () -> repository.buscarPorCodigo(99));
    }

    @Test
    void deveBuscarPorCpf() {
        assertEquals("Bruno", repository.buscarPorCpf("11144477735").getNome());
    }

    @Test
    void deveListarTodos() {
        assertEquals(2, repository.listarTodos().size());
    }

    @Test
    void deveVerificarSeExisteCodigo() {
        assertTrue(repository.existeCodigo(1));
        assertFalse(repository.existeCodigo(99));
    }

    @Test
    void deveRemoverFuncionario() {
        repository.remover(1);
        assertEquals(1, repository.listarTodos().size());
    }

    @Test
    void deveLancarExcecaoAoRemoverInexistente() {
        assertThrows(FuncionarioNaoEncontradoException.class, () -> repository.remover(99));
    }
}
