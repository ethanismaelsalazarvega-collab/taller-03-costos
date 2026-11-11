package ec.edu.uagraria.ganatrack.dominio;

public record RegistroMonitoreo(
        String identificador,
        int diasSinVacuna,
        double porcentajeCaidaProduccion,
        int horasRetencionDespacho
) {}