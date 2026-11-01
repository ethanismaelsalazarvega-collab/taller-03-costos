package ec.uagraria.costos;

@FunctionalInterface
public interface EstrategiaCosto {

    // Método abstracto obligatorio
    double calcular(Lote lote);

    // Método por defecto opcional
    default String descripcion() {
        return "Estrategia de costo";
    }
}