package ec.edu.uagraria.ganatrack.alertas;

public enum ConfiguracionGanaTrack {
    INSTANCIA;

    private int umbralDiasVacunacion = 180;
    private double umbralCaidaProduccion = 15.0;
    private int umbralHorasRetencion = 24;

    public int getUmbralDiasVacunacion() { return umbralDiasVacunacion; }
    public void setUmbralDiasVacunacion(int dias) { this.umbralDiasVacunacion = dias; }

    public double getUmbralCaidaProduccion() { return umbralCaidaProduccion; }
    public void setUmbralCaidaProduccion(double porcentaje) { this.umbralCaidaProduccion = porcentaje; }

    public int getUmbralHorasRetencion() { return umbralHorasRetencion; }
    public void setUmbralHorasRetencion(int horas) { this.umbralHorasRetencion = horas; }
}