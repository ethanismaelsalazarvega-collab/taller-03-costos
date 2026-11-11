package ec.edu.uagraria.ganatrack;

import ec.edu.uagraria.ganatrack.dominio.Bovino;
import ec.edu.uagraria.ganatrack.dominio.InsumoPecuario;
import ec.edu.uagraria.ganatrack.dominio.PastoForraje;
import ec.edu.uagraria.ganatrack.dominio.Trazable;
import ec.edu.uagraria.ganatrack.fabrica.FabricaProductos;
import ec.edu.uagraria.ganatrack.fabrica.TipoProducto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabricaProductosTest {

    @Test
    @DisplayName("Fábrica genera Bovino con código trazable y prefijo BOV")
    void crearBovino() {
        Trazable prod = FabricaProductos.crearProducto(TipoProducto.BOVINO, "Brahman", 450.0, 30);
        assertInstanceOf(Bovino.class, prod);
        assertTrue(prod.codigo().startsWith("BOV-"));
        assertEquals("Brahman", ((Bovino) prod).raza());
    }

    @Test
    @DisplayName("Fábrica genera PastoForraje con prefijo PAS")
    void crearPasto() {
        Trazable prod = FabricaProductos.crearProducto(TipoProducto.PASTO_FORRAJE, "Mombaza", 250.0, 0);
        assertInstanceOf(PastoForraje.class, prod);
        assertTrue(prod.codigo().startsWith("PAS-"));
    }

    @Test
    @DisplayName("Fábrica genera InsumoPecuario con prefijo INS")
    void crearInsumo() {
        Trazable prod = FabricaProductos.crearProducto(TipoProducto.INSUMO_PECUARIO, "Vacuna Aftosa", 0.0, 100);
        assertInstanceOf(InsumoPecuario.class, prod);
        assertTrue(prod.codigo().startsWith("INS-"));
    }
}