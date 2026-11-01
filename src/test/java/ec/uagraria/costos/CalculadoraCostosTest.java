package ec.uagraria.costos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraCostosTest {

    private Lote loteBanano;
    private Lote loteCacaoOrganico;
    private CalculadoraCostos calculadora;

    @BeforeEach
    void setUp() {
        calculadora = new CalculadoraCostos();

        // Lote de Banano de prueba (4 hectáreas)
        loteBanano = new Lote("L-01", new Banano("Cavendish"), 4.0, List.of(
                new Insumo("Urea 50 kg", 12, "saco", 32.50),
                new Insumo("Fungicida", 8, "litro", 21.75),
                new Insumo("Semilla certificada", 1, "lote", 180.00)
        ));

        // Lote de Cacao Orgánico de prueba (2.5 hectáreas)
        loteCacaoOrganico = new Lote("L-02", new Cacao("CCN-51", true), 2.5, List.of(
                new Insumo("Urea 50 kg", 5, "saco", 32.50),
                new Insumo("Abono orgánico", 30, "saco", 9.80)
        ));
    }

    @Test
    @DisplayName("Prueba 1: Sin estrategia devuelve Optional vacío")
    void sinEstrategiaDevuelveOptionalVacio() {
        assertTrue(calculadora.calcular(loteBanano).isEmpty());
    }

    @Test
    @DisplayName("Prueba 2: Costo directo suma correctamente solo los insumos")
    void costoDirectoSumaSoloInsumos() {
        calculadora.setEstrategia(Estrategias.costoDirecto());
        assertEquals(744.00, calculadora.calcular(loteBanano).orElseThrow(), 0.01);
    }

    @Test
    @DisplayName("Prueba 3: Costo con mano de obra calcula insumos y jornales")
    void costoConManoDeObraCalculaCorrectamente() {
        calculadora.setEstrategia(Estrategias.costoConManoDeObra());
        assertEquals(2076.00, calculadora.calcular(loteBanano).orElseThrow(), 0.01);
    }

    @Test
    @DisplayName("Prueba 4: Costo por hectárea divide costo total entre la superficie")
    void costoPorHectareaDivideCorrectamente() {
        calculadora.setEstrategia(Estrategias.costoPorHectarea());
        assertEquals(519.00, calculadora.calcular(loteBanano).orElseThrow(), 0.01);
    }

    @Test
    @DisplayName("Prueba 5: Subsidio 20% urea descuenta solo el insumo correspondiente")
    void costoConSubsidioFertilizanteAplicaDescuento() {
        calculadora.setEstrategia(Estrategias.costoConSubsidioFertilizante(0.20));
        assertEquals(1998.00, calculadora.calcular(loteBanano).orElseThrow(), 0.01);
    }

    @Test
    @DisplayName("Prueba 6: Estrategia compuesta con IVA aplica el 15% sobre la base")
    void estrategiaCompuestaConIvaAplicaQuincePorCiento() {
        calculadora.setEstrategia(Estrategias.conIva(Estrategias.costoConManoDeObra()));
        assertEquals(2387.40, calculadora.calcular(loteBanano).orElseThrow(), 0.01);
    }

    @Test
    @DisplayName("Prueba 7: Cacao orgánico aplica guarda 'when' y jornales correspondientes")
    void cacaoOrganicoAplicaJornalCorrecto() {
        assertEquals(25, loteCacaoOrganico.jornalesPorHectarea());
        calculadora.setEstrategia(Estrategias.costoConManoDeObra());
        assertEquals(1612.75, calculadora.calcular(loteCacaoOrganico).orElseThrow(), 0.01);
    }
}