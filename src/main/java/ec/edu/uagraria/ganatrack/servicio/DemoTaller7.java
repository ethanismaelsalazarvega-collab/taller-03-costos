package ec.edu.uagraria.ganatrack.servicio;

public class DemoTaller7 {
    public static void main(String[] args) {
        Inventario inv = new Inventario();

        // 5 ítems de GanaTrack usando los 3 tipos y repitiendo el código PAS-0001
        inv.registrar(new ItemInventario("BOV-0001", "Bovino", "Vaca Holstein Arete 104 (kg)", 480.0));
        inv.registrar(new ItemInventario("PAS-0001", "PastoForraje", "Pasto Brachiaria fresco (kg)", 350.0));
        inv.registrar(new ItemInventario("INS-0001", "InsumoPecuario", "Vacuna antiaftosa (dosis)", 120.0));
        inv.registrar(new ItemInventario("BOV-0002", "Bovino", "Toro Brahman Arete 205 (kg)", 620.0));
        inv.registrar(new ItemInventario("PAS-0001", "PastoForraje", "Pasto Brachiaria reingreso (kg)", 120.0));

        System.out.println("Registros (List): " + inv.registros().size());
        System.out.println("Codigos unicos (Set): " + inv.codigosUnicos());
        System.out.println("Total por tipo (Map): " + inv.totalPorTipo());
        System.out.println("Ordenados por cantidad (Comparator):");
        inv.ordenadosPorCantidad().forEach(i ->
                System.out.printf(" %-9s %-17s %7.1f%n", i.codigo(), i.tipo(), i.cantidad()));

        // Cola de despacho FIFO: bovinos en fila para vacunación según orden de llegada
        inv.registros().stream()
                .filter(i -> i.tipo().equals("Bovino"))
                .forEach(inv::encolarDespacho);

        System.out.println("Despacho vacunacion (Deque): "
                + inv.despacharSiguiente().orElseThrow().codigo()
                + " | pendientes: " + inv.pendientesDespacho());
    }
}