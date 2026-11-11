package ec.edu.uagraria.ganatrack.dominio;

public record Cacao(String variedad, boolean organico) implements Cultivo {
    @Override
    public String nombreComun() {
        return "Cacao " + variedad;
    }
}