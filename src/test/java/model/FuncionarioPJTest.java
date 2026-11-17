package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FuncionarioPJTest {

    @Test
    void deveCalcularRemuneracaoPorProjetos() {
        FuncionarioPJ pj = new FuncionarioPJ(1, "Bruno", "52998224725", 500, 4);
        assertEquals(2000, pj.calcularRemuneracao(), 0.001);
    }

    @Test
    void deveLancarExcecaoComValorNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioPJ(1, "Bruno", "52998224725", -500, 2));
    }

    @Test
    void deveLancarExcecaoComQuantidadeNegativa() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioPJ(1, "Bruno", "52998224725", 500, -2));
    }

    @Test
    void deveCalcularTributos() {
        FuncionarioPJ pj = new FuncionarioPJ(1, "Bruno", "52998224725", 500, 4);
        assertEquals(2000 * 0.06, pj.calcularTributos(), 0.001);
    }
}
