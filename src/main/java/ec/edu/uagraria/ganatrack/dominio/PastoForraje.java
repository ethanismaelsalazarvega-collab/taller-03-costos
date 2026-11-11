package ec.edu.uagraria.ganatrack.dominio;

public record PastoForraje(
        String codigo,
        String variedad,
        double cantidadKg
) implements Trazable {}