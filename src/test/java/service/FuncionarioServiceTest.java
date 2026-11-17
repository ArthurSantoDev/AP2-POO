package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.FuncionarioJaCadastradoException;
import exception.FuncionarioNaoEncontradoException;
import model.FuncionarioCLT;
import model.FuncionarioPJ;
import repository.FuncionarioRepository;

class FuncionarioServiceTest {
    private FuncionarioService service;

    @BeforeEach
    void setUp() {
        service = new FuncionarioService(new FuncionarioRepository());
        service.cadastrar(new FuncionarioCLT(1, "Ana", "52998224725", 2000, 100));
        service.cadastrar(new FuncionarioPJ(2, "Bruno", "11144477735", 500, 4));
    }

    @Test
    void deveCadastrarEListarFuncionarios() {
        assertEquals(2, service.listarFuncionarios().size());
    }

    @Test
    void deveLancarExcecaoAoCadastrarCodigoDuplicado() {
        FuncionarioCLT duplicado = new FuncionarioCLT(1, "Duda", "11144477736", 1500, 0);
        assertThrows(FuncionarioJaCadastradoException.class, () -> service.cadastrar(duplicado));
    }

    @Test
    void deveLancarExcecaoAoCadastrarCpfDuplicado() {
        FuncionarioCLT duplicado = new FuncionarioCLT(3, "Duda", "52998224725", 1500, 0);
        assertThrows(FuncionarioJaCadastradoException.class, () -> service.cadastrar(duplicado));
    }

    @Test
    void deveBuscarPorCodigo() {
        assertEquals("Bruno", service.buscarFuncionario(2).getNome());
    }

    @Test
    void deveBuscarPorCpf() {
        assertEquals("Ana", service.buscarFuncionario("52998224725").getNome());
    }

    @Test
    void deveBuscarPorNome() {
        assertEquals(1, service.buscarFuncionario("Ana", true).size());
        assertEquals(1, service.buscarFuncionario("Bru", false).size());
    }

    @Test
    void deveLancarExcecaoAoBuscarInexistente() {
        assertThrows(FuncionarioNaoEncontradoException.class, () -> service.buscarFuncionario(99));
        assertThrows(FuncionarioNaoEncontradoException.class, () -> service.buscarFuncionario("00000000000"));
    }

    @Test
    void deveRemoverFuncionario() {
        service.removerFuncionario(1);
        assertEquals(1, service.listarFuncionarios().size());
    }

    @Test
    void deveCalcularFolhaDePagamento() {
        assertEquals(2100 + 2000, service.calcularFolhaPagamento(), 0.001);
    }

    @Test
    void deveGerarRelatorioComTotal() {
        String relatorio = service.gerarRelatorio();
        assertTrue(relatorio.contains("Ana"));
        assertTrue(relatorio.contains("Total da folha de pagamento"));
    }
}
