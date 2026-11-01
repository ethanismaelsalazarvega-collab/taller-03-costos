package ec.uagraria.costos;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("  SISTEMA DE COSTOS AGROPECUARIOS - DEMOSTRACIÓN  ");
        System.out.println("==================================================\n");

        // 1. Lote de prueba 1: Banano Cavendish (4.0 hectáreas)
        Lote loteBanano = new Lote("L-01", new Banano("Cavendish"), 4.0, List.of(
                new Insumo("Urea 50 kg", 12, "saco", 32.50),
                new Insumo("Fungicida", 8, "litro", 21.75),
                new Insumo("Semilla certificada", 1, "lote", 180.00)
        ));

        // 2. Lote de prueba 2: Cacao Orgánico (2.5 hectáreas)
        Lote loteCacao = new Lote("L-02", new Cacao("Nacional", true), 2.5, List.of(
                new Insumo("Urea 50 kg", 5, "saco", 32.50),
                new Insumo("Abono orgánico", 30, "saco", 9.80)
        ));

        CalculadoraCostos calc = new CalculadoraCostos();

        // --- PRUEBAS CON LOTE DE BANANO ---
        System.out.println("--- PRUEBAS CON LOTE DE BANANO (4.0 ha) ---");

        // Caso 1: Sin estrategia configurada (debe dar Optional.empty)
        System.out.println("1. Sin estrategia: " + calc.calcular(loteBanano));

        // Caso 2: Costo directo (solo insumos)
        calc.setEstrategia(Estrategias.costoDirecto());
        System.out.printf("2. Costo directo: USD %.2f%n", calc.calcular(loteBanano).orElseThrow());

        // Caso 3: Insumos + mano de obra
        calc.setEstrategia(Estrategias.costoConManoDeObra());
        System.out.printf("3. Insumos + mano de obra: USD %.2f%n", calc.calcular(loteBanano).orElseThrow());

        // Caso 4: Costo por hectárea
        calc.setEstrategia(Estrategias.costoPorHectarea());
        System.out.printf("4. Costo por hectárea: USD %.2f%n", calc.calcular(loteBanano).orElseThrow());

        // Caso 5: Subsidio del 20% a la urea
        calc.setEstrategia(Estrategias.costoConSubsidioFertilizante(0.20));
        System.out.printf("5. Con subsidio 20%% a la urea: USD %.2f%n", calc.calcular(loteBanano).orElseThrow());

        // Caso 6: Con IVA del 15% (sobre insumos + mano de obra)
        calc.setEstrategia(Estrategias.conIva(Estrategias.costoConManoDeObra()));
        System.out.printf("6. Con IVA del 15%%: USD %.2f%n", calc.calcular(loteBanano).orElseThrow());

        // --- PRUEBAS CON LOTE DE CACAO ORGÁNICO ---
        System.out.println("\n--- PRUEBAS CON LOTE DE CACAO ORGÁNICO (2.5 ha) ---");

        // Caso 7: Jornales por hectárea (debe resolverse a 25 por la guarda 'when')
        System.out.println("7. Jornales por hectárea (Cacao Orgánico): " + loteCacao.jornalesPorHectarea());

        // Caso 8: Insumos + mano de obra en cacao orgánico
        calc.setEstrategia(Estrategias.costoConManoDeObra());
        System.out.printf("8. Insumos + mano de obra (Cacao Orgánico): USD %.2f%n", calc.calcular(loteCacao).orElseThrow());
    }
}