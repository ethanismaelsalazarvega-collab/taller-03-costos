package ec.edu.uagraria.ganatrack.dominio;

import java.util.concurrent.atomic.AtomicInteger;

public interface Trazable {
    String codigo();

    AtomicInteger SECUENCIA = new AtomicInteger(100);

    static String generarCodigo(String prefijo) {
        return String.format("%s-%04d", prefijo, SECUENCIA.incrementAndGet());
    }
}