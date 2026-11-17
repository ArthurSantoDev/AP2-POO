package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FuncionarioTest {

    @Test
    void deveLancarExcecaoComNomeVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioCLT(1, "  ", "52998224725", 2000, 0));
    }

    @Test
    void deveLancarExcecaoComCpfInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioCLT(1, "Ana", "123456", 2000, 0));
    }

    @Test
    void deveLancarExcecaoComSalarioNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioCLT(1, "Ana", "52998224725", -2000, 0));
    }

    @Test
    void deveValidarCpfComOnzeDigitos() {
        FuncionarioCLT f = new FuncionarioCLT(1, "Ana", "529.982.247-25", 2000, 0);
        assertTrue(f.validarCpf("11122233344"));
        assertFalse(f.validarCpf("123"));
        assertFalse(f.validarCpf(null));
    }

    @Test
    void deveChamarMetodoCorretoDeCadaTipo() {
        Funcionario f1 = new FuncionarioCLT(1, "Ana", "52998224725", 2000, 300);
        Funcionario f2 = new FuncionarioPJ(2, "Bruno", "11144477735", 500, 4);
        Funcionario f3 = new FuncionarioComissionado(3, "Carla", "52998224726", 1500, 10, 10000);

        assertEquals(2300, f1.calcularRemuneracao(), 0.001);
        assertEquals(2000, f2.calcularRemuneracao(), 0.001);
        assertEquals(2500, f3.calcularRemuneracao(), 0.001);
    }
}
