package ec.edu.uagraria.ganatrack.alertas;

import ec.edu.uagraria.ganatrack.dominio.RegistroMonitoreo;
import java.util.Optional;

@FunctionalInterface
public interface ReglaAlerta {
    Optional<Alerta> evaluar(RegistroMonitoreo lectura);
}