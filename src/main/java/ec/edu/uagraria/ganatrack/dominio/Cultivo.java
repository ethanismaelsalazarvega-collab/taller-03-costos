// Jerarquía sellada implementada por Antonella Castillo
package ec.edu.uagraria.ganatrack.dominio;

public sealed interface Cultivo permits Banano, Cacao, Arroz {
    String nombreComun();
}
