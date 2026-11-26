package ec.edu.uagraria.ganatrack.servicio;

/**
 * Taller 7: una línea del inventario del proyecto GanaTrack (record inmutable).
 */
public record ItemInventario(String codigo, String tipo, String descripcion, double cantidad) {
    public ItemInventario {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
    }
}