package ec.edu.uagraria.ganatrack;

import ec.edu.uagraria.ganatrack.alertas.*;
import ec.edu.uagraria.ganatrack.dominio.RegistroMonitoreo;
import java.util.ArrayList;
import java.util.List;

public class DemoMotorAlertas {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("   SISTEMA GANATRACK - MOTOR DE ALERTAS SANITARIAS (G06)   ");
        System.out.println("==========================================================");

        MotorAlertas motor = new MotorAlertas();

        // 1. Registrar las 3 reglas del Grupo G06
        motor.agregarRegla(ReglasGanaTrack.vacunaVencida());
        motor.agregarRegla(ReglasGanaTrack.caidaProduccion());
        motor.agregarRegla(ReglasGanaTrack.despachoRetenido());

        // 2. Observador 1: Canal de Alerta en Consola en Vivo
        motor.suscribir(alerta ->
                System.out.printf("[ALERTA ACTIVA] %-20s | Ref: %-12s | %s%n",
                        alerta.tipo(), alerta.referenciaId(), alerta.mensaje())
        );

        // 3. Observador 2: Bitácora de Auditoría en Memoria
        List<Alerta> bitacoraAuditoria = new ArrayList<>();
        motor.suscribir(bitacoraAuditoria::add);

        // 4. Simulamos una lectura crítica del hato ganadero
        System.out.println("\n>>> Procesando lectura crítica de lote y bovino...");
        RegistroMonitoreo lecturaCritica = new RegistroMonitoreo("BOV-2026-042", 192, 18.5, 30);

        int totalDisparadas = motor.procesar(lecturaCritica);

        System.out.println("\n----------------------------------------------------------");
        System.out.println("Resumen de ejecución:");
        System.out.println(" - Total de alertas notificadas: " + totalDisparadas);
        System.out.println(" - Alertas almacenadas en bitácora: " + bitacoraAuditoria.size());
        System.out.println("==========================================================");
    }
}