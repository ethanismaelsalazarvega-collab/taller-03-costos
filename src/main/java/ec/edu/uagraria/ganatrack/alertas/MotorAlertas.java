package ec.edu.uagraria.ganatrack.alertas;

import ec.edu.uagraria.ganatrack.dominio.RegistroMonitoreo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MotorAlertas {
    private final List<ReglaAlerta> reglas = new ArrayList<>();
    private final List<ObservadorAlertas> observadores = new ArrayList<>();

    public void agregarRegla(ReglaAlerta regla) {
        reglas.add(regla);
    }

    public void suscribir(ObservadorAlertas observador) {
        observadores.add(observador);
    }

    public int procesar(RegistroMonitoreo lectura) {
        int publicadas = 0;
        for (ReglaAlerta regla : reglas) {
            Optional<Alerta> alerta = regla.evaluar(lectura);
            if (alerta.isPresent()) {
                observadores.forEach(o -> o.notificar(alerta.get()));
                publicadas++;
            }
        }
        return publicadas;
    }
}