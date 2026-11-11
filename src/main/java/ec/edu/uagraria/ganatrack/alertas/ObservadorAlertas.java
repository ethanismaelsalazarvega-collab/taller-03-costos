package ec.edu.uagraria.ganatrack.alertas;

@FunctionalInterface
public interface ObservadorAlertas {
    void notificar(Alerta alerta);
}