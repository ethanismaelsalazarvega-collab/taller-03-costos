package ec.edu.uagraria.ganatrack.dominio;

public record InsumoPecuario(
        String codigo,
        String nombre,
        int stockDosis
) implements Trazable {}