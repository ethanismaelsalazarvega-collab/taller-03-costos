package ec.edu.uagraria.ganatrack.dominio;

public record Banano(String variedad) implements Cultivo {
    @Override
    public String nombreComun() {
        return "Banano " + variedad;
    }
}