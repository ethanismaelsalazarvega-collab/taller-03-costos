package ec.edu.uagraria.ganatrack;

import ec.edu.uagraria.ganatrack.alertas.*;
import ec.edu.uagraria.ganatrack.dominio.RegistroMonitoreo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MotorAlertasTest {

    @BeforeEach
    void resetConfiguracion() {
        ConfiguracionGanaTrack.INSTANCIA.setUmbralDiasVacunacion(180);
        ConfiguracionGanaTrack.INSTANCIA.setUmbralCaidaProduccion(15.0);
        ConfiguracionGanaTrack.INSTANCIA.setUmbralHorasRetencion(24);
    }

    // --- REGLA 1: VACUNA_VENCIDA ---
    @Test
    @DisplayName("Vacuna vencida: Se dispara cuando supera el umbral (181 > 180)")
    void vacunaVencidaDisparaPorEncima() {
        RegistroMonitoreo reg = new RegistroMonitoreo("BOV-001", 181, 0.0, 0);
        assertTrue(ReglasGanaTrack.vacunaVencida().evaluar(reg).isPresent());
    }

    @Test
    @DisplayName("Vacuna vencida: NO se dispara en el umbral exacto (180)")
    void vacunaVencidaNoDisparaEnUmbral() {
        RegistroMonitoreo reg = new RegistroMonitoreo("BOV-001", 180, 0.0, 0);
        assertTrue(ReglasGanaTrack.vacunaVencida().evaluar(reg).isEmpty());
    }

    // --- REGLA 2: CAIDA_PRODUCCION ---
    @Test
    @DisplayName("Caida de produccion: Se dispara cuando supera el umbral (15.1 > 15.0)")
    void caidaProduccionDisparaPorEncima() {
        RegistroMonitoreo reg = new RegistroMonitoreo("BOV-002", 0, 15.1, 0);
        assertTrue(ReglasGanaTrack.caidaProduccion().evaluar(reg).isPresent());
    }

    @Test
    @DisplayName("Caida de produccion: NO se dispara en el umbral exacto (15.0)")
    void caidaProduccionNoDisparaEnUmbral() {
        RegistroMonitoreo reg = new RegistroMonitoreo("BOV-002", 0, 15.0, 0);
        assertTrue(ReglasGanaTrack.caidaProduccion().evaluar(reg).isEmpty());
    }

    // --- REGLA 3: DESPACHO_RETENIDO ---
    @Test
    @DisplayName("Despacho retenido: Se dispara cuando supera el umbral (25 > 24)")
    void despachoRetenidoDisparaPorEncima() {
        RegistroMonitoreo reg = new RegistroMonitoreo("DESP-001", 0, 0.0, 25);
        assertTrue(ReglasGanaTrack.despachoRetenido().evaluar(reg).isPresent());
    }

    @Test
    @DisplayName("Despacho retenido: NO se dispara en el umbral exacto (24)")
    void despachoRetenidoNoDisparaEnUmbral() {
        RegistroMonitoreo reg = new RegistroMonitoreo("DESP-001", 0, 0.0, 24);
        assertTrue(ReglasGanaTrack.despachoRetenido().evaluar(reg).isEmpty());
    }

    // --- NOTIFICACION A OBSERVADORES ---
    @Test
    @DisplayName("El motor notifica a todos los observadores registrados")
    void motorNotificaATodosLosObservadores() {
        MotorAlertas motor = new MotorAlertas();
        motor.agregarRegla(ReglasGanaTrack.vacunaVencida());
        motor.agregarRegla(ReglasGanaTrack.caidaProduccion());

        List<Alerta> bitacoraMemoria = new ArrayList<>();
        List<Alerta> canalConsola = new ArrayList<>();

        motor.suscribir(canalConsola::add);
        motor.suscribir(bitacoraMemoria::add);

        RegistroMonitoreo critico = new RegistroMonitoreo("BOV-100", 190, 22.5, 0);
        int totalPublicadas = motor.procesar(critico);

        assertEquals(2, totalPublicadas);
        assertEquals(2, canalConsola.size());
        assertEquals(2, bitacoraMemoria.size());
    }
}