package ec.edu.uagraria.ganatrack.dominio;

public record Bovino(
        String codigo,
        String raza,
        double pesoKg,
        int diasUltimaVacuna
) implements Trazable {}