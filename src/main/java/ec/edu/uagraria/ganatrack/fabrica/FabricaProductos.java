package ec.edu.uagraria.ganatrack.fabrica;

import ec.edu.uagraria.ganatrack.dominio.Bovino;
import ec.edu.uagraria.ganatrack.dominio.InsumoPecuario;
import ec.edu.uagraria.ganatrack.dominio.PastoForraje;
import ec.edu.uagraria.ganatrack.dominio.Trazable;

public class FabricaProductos {

    // Switch exhaustivo en Java 21 sin cláusula default (requisito de la rúbrica)
    public static Trazable crearProducto(TipoProducto tipo, String detalle, double valor, int extra) {
        return switch (tipo) {
            case BOVINO -> new Bovino(
                    Trazable.generarCodigo("BOV"),
                    detalle,
                    valor,
                    extra
            );
            case PASTO_FORRAJE -> new PastoForraje(
                    Trazable.generarCodigo("PAS"),
                    detalle,
                    valor
            );
            case INSUMO_PECUARIO -> new InsumoPecuario(
                    Trazable.generarCodigo("INS"),
                    detalle,
                    extra
            );
        };
    }
}