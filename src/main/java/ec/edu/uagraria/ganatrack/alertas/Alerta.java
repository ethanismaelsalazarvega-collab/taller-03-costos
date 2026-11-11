package ec.edu.uagraria.ganatrack.alertas;

import java.time.LocalDateTime;

public record Alerta(
        TipoAlerta tipo,
        String referenciaId,
        String mensaje,
        LocalDateTime fechaHora
) {}