package ec.edu.uagraria.ganatrack.dominio;

public record Arroz(int ciclosPorAnio) implements Cultivo {
    @Override
    public String nombreComun() {
        return "Arroz (" + ciclosPorAnio + " ciclos/año)";
    }
}