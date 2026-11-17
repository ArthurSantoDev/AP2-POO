package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FuncionarioCLTTest {

    @Test
    void deveCalcularSalarioMaisBeneficios() {
        FuncionarioCLT clt = new FuncionarioCLT(1, "Ana", "52998224725", 2000, 450);
        assertEquals(2450, clt.calcularRemuneracao(), 0.001);
    }

    @Test
    void deveLancarExcecaoComBeneficioNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioCLT(1, "Ana", "52998224725", 2000, -50));
    }

    @Test
    void deveLancarExcecaoComSalarioNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioCLT(1, "Ana", "52998224725", -2000, 100));
    }

    @Test
    void deveCalcularTributos() {
        FuncionarioCLT clt = new FuncionarioCLT(1, "Ana", "52998224725", 2000, 300);
        assertEquals(2300 * 0.12, clt.calcularTributos(), 0.001);
    }
}
