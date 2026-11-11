# GanaTrack — Taller 4: SOLID, Factory y Observer
**Universidad Agraria del Ecuador — Programación 3 (O.O. Avanzada)**  
**Grupo 6 (Paralelo 4SC)**

### Integrantes y Roles
- **Ethan Salazar Vega**: Coordinador / Responsable de Dominio y Fábrica.
- **Antonella Castillo & Ronaldo Alvear**: Desarrolladores / Responsables del Motor de Alertas y Reglas.
- **Washington Ávalos & Juan Chonillo**: Control de Calidad (QA), Pruebas JUnit 5 y Revisión de Pull Request.

---

## 1. Diagnóstico del Código Previo (Paso 2)

| Pregunta | Respuesta del grupo |
| :--- | :--- |
| **¿Qué clase tiene más de un motivo para cambiar? ¿Cuáles son?** | La clase `CalculadoraCostos` / `Inventario`. Tenía más de un motivo para cambiar: si variaba la persistencia o colección de datos, si cambiaba la forma de instanciar los productos del dominio con `new`, o si se introducían nuevas alertas del negocio. |
| **¿Dónde se usa new directamente sobre los records del dominio?** | En las clases de prueba (`*Test.java`) y en las clases ejecutoras, donde se llamaba directamente a `new Bovino(...)`, `new PastoForraje(...)` y `new InsumoPecuario(...)`. |
| **¿Hay umbrales o tarifas escritos como números sueltos dentro de una regla?** | Sí, existían "números mágicos" fijos (como `180` para días de vacunación, `15.0` para porcentaje de caída y `24` para horas de retención) quemados dentro de condicionales. |
| **¿Hay alguna herencia que exista solo para reutilizar código?** | Sí, en versiones previas se usaba herencia entre entidades genéricas para reutilizar atributos comunes en lugar de desacoplar mediante la interfaz funcional `Trazable`. |

---

## 2. Aplicación de Principios SOLID

| Principio | Clase Concreta del Proyecto | Justificación Técnica |
| :--- | :--- | :--- |
| **S** (Single Responsibility) | `MotorAlertas` | Su única responsabilidad es coordinar la evaluación de reglas y notificar a los observadores suscritos. No genera alertas ni conoce la lógica de persistencia. |
| **O** (Open/Closed) | `MotorAlertas` / `ReglasGanaTrack` | El motor está abierto a la extensión y cerrado a la modificación: se pueden agregar infinitas reglas sanitarias mediante lambdas sin tocar una sola línea de `MotorAlertas`. |
| **L** (Liskov Substitution) | `Bovino`, `PastoForraje`, `InsumoPecuario` | Todos implementan el contrato `Trazable` y son intercambiables en cualquier método que consuma un elemento trazable sin alterar la semántica del programa. |
| **I** (Interface Segregation) | `Trazable`, `ReglaAlerta`, `ObservadorAlertas` | Interfaces funcionales segregadas, pequeñas y con un único método abstracto, evitando que las clases implementen métodos que no necesitan. |
| **D** (Dependency Inversion) | `MotorAlertas` | Depende estrictamente de interfaces funcionales (`ReglaAlerta`, `ObservadorAlertas`) y no de implementaciones concretas ni canales específicos de notificación. |

---

## 3. Justificación Técnica de Umbrales

1. **Umbral de Vacunación (180 días):**  
   Corresponde al periodo máximo legal de revacunación obligatoria en Ecuador dispuesto por **AGROCALIDAD** para la erradicación de la Fiebre Aftosa. Pasados los 180 días, el animal pierde su estatus inmunitario oficial.
2. **Umbral de Caída de Producción (15.0%):**  
   Estándar zootécnico en hatos bovinos de leche/carne; una disminución brusca mayor al 15% indica un cuadro patológico temprano (ej. mastitis subclínica) o deficiencia crítica en la calidad del forraje.
3. **Umbral de Retención de Despacho (24 horas):**  
   Tiempo límite establecido por la normativa de bienestar animal y vigencia de las guías de movilización agropecuaria en corrales antes del transporte formal.