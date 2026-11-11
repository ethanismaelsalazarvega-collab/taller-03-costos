package ec.edu.uagraria.ganatrack.alertas;

import java.time.LocalDateTime;
import java.util.Optional;

public class ReglasGanaTrack {

    public static ReglaAlerta vacunaVencida() {
        return lectura -> {
            int limite = ConfiguracionGanaTrack.INSTANCIA.getUmbralDiasVacunacion();
            if (lectura.diasSinVacuna() > limite) {
                return Optional.of(new Alerta(
                        TipoAlerta.VACUNA_VENCIDA,
                        lectura.identificador(),
                        "Bovino excede límite de días sin vacunación (" + lectura.diasSinVacuna() + " días).",
                        LocalDateTime.now()
                ));
            }
            return Optional.empty();
        };
    }

    public static ReglaAlerta caidaProduccion() {
        return lectura -> {
            double limite = ConfiguracionGanaTrack.INSTANCIA.getUmbralCaidaProduccion();
            if (lectura.porcentajeCaidaProduccion() > limite) {
                return Optional.of(new Alerta(
                        TipoAlerta.CAIDA_PRODUCCION,
                        lectura.identificador(),
                        "Caída de producción supera el umbral crítico (" + lectura.porcentajeCaidaProduccion() + "%).",
                        LocalDateTime.now()
                ));
            }
            return Optional.empty();
        };
    }

    public static ReglaAlerta despachoRetenido() {
        return lectura -> {
            int limite = ConfiguracionGanaTrack.INSTANCIA.getUmbralHorasRetencion();
            if (lectura.horasRetencionDespacho() > limite) {
                return Optional.of(new Alerta(
                        TipoAlerta.DESPACHO_RETENIDO,
                        lectura.identificador(),
                        "Despacho retenido por " + lectura.horasRetencionDespacho() + " horas.",
                        LocalDateTime.now()
                ));
            }
            return Optional.empty();
        };
    }
}