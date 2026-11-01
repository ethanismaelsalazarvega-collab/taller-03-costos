package ec.uagraria.costos;

public final class Estrategias {

    public static final double VALOR_JORNAL = 18.50; // USD por día
    public static final double IVA = 0.15;           // 15% IVA

    private Estrategias() { } // Evita que se creen objetos con "new Estrategias()"

    // 1. Solo insumos
    public static EstrategiaCosto costoDirecto() {
        return lote -> lote.costoInsumos();
    }

    // 2. Insumos + Jornales
    public static EstrategiaCosto costoConManoDeObra() {
        return lote -> lote.costoInsumos()
                + lote.jornalesPorHectarea() * lote.hectareas() * VALOR_JORNAL;
    }

    // 3. Dividido para el tamaño del lote
    public static EstrategiaCosto costoPorHectarea() {
        return lote -> costoConManoDeObra().calcular(lote) / lote.hectareas();
    }

    // 4. Con subsidio del Estado al fertilizante
    public static EstrategiaCosto costoConSubsidioFertilizante(double porcentaje) {
        return lote -> {
            double descuento = lote.insumos().stream()
                    .filter(i -> i.nombre().toLowerCase().contains("urea"))
                    .mapToDouble(Insumo::costo) // Referencia a método
                    .sum() * porcentaje;
            return costoConManoDeObra().calcular(lote) - descuento;
        };
    }

    // 5. Envoltorio para agregar IVA
    public static EstrategiaCosto conIva(EstrategiaCosto base) {
        return lote -> base.calcular(lote) * (1 + IVA);
    }
}