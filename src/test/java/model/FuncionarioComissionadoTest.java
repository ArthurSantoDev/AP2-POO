package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FuncionarioComissionadoTest {

    @Test
    void deveCalcularSalarioMaisComissao() {
        FuncionarioComissionado c = new FuncionarioComissionado(1, "Carla", "52998224725", 1500, 10, 10000);
        assertEquals(2500, c.calcularRemuneracao(), 0.001);
    }

    @Test
    void deveLancarExcecaoComPercentualAcimaDeCem() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioComissionado(1, "Carla", "52998224725", 1500, 150, 1000));
    }

    @Test
    void deveLancarExcecaoComVendaNegativa() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioComissionado(1, "Carla", "52998224725", 1500, 10, -1000));
    }

    @Test
    void deveCalcularTributos() {
        FuncionarioComissionado c = new FuncionarioComissionado(1, "Carla", "52998224725", 1500, 10, 10000);
        assertEquals(2500 * 0.10, c.calcularTributos(), 0.001);
    }
}
