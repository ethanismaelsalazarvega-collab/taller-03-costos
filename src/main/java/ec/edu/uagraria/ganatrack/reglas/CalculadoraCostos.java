package ec.edu.uagraria.ganatrack.reglas;

import ec.edu.uagraria.ganatrack.dominio.Lote;

import java.util.Optional;

public class CalculadoraCostos {

    // Referencia a la interfaz funcional (el patrón Strategy)
    private EstrategiaCosto estrategia;

    // Constructor vacío (inicia sin estrategia configurada)
    public CalculadoraCostos() { }

    // Constructor con estrategia inicial
    public CalculadoraCostos(EstrategiaCosto estrategia) {
        this.estrategia = estrategia;
    }

    // Permite cambiar la fórmula de cálculo en cualquier momento
    public void setEstrategia(EstrategiaCosto estrategia) {
        this.estrategia = estrategia;
    }

    /**
     * Calcula el costo aplicando la estrategia actual.
     * Devuelve Optional.empty() si no hay estrategia o si el lote es null.
     */
    public Optional<Double> calcular(Lote lote) {
        if (estrategia == null || lote == null) {
            return Optional.empty();
        }
        return Optional.of(estrategia.calcular(lote));
    }

    /**
     * Método utilitario para redondear a dos decimales.
     */
    public static double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}