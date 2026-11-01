// Jerarquía sellada implementada por Antonella Castillo
package ec.uagraria.costos;

public sealed interface Cultivo permits Banano, Cacao, Arroz {
    String nombreComun();
}
