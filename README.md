# Taller Grupal 3: Lambdas y Patrón Strategy
**Universidad Agraria del Ecuador**  
**Facultad de Ciencias Agrarias · Carrera de Computación en Línea**  
**Asignatura:** Programación 3 (Orientada a Objetos Avanzada) · Semana 3

---

## 👥 Integrantes y Roles
* **Ethan Salazar** — Coordinador de Proyecto
* **Antonella Castillo** — Desarrolladora Principal
* **Washington Ávalos** — Revisor de Calidad (QA)
* **Ronaldo Alvear** — Desarrollador de Soporte
* **Juan Chonillo** — Documentador Técnico

---

## 📋 Descripción del Sistema
Calculadora de costos de producción agropecuaria para cultivos del litoral ecuatoriano (Banano, Cacao y Arroz) construida sobre **Java 21 LTS**. Implementa:
* **Jerarquía Sellada (`sealed`)** y records inmutables para el dominio agrícola.
* **Pattern Matching** exhaustivo con guardas (`when`) en estructuras `switch`.
* **Patrón de diseño Strategy** desacoplado mediante interfaces funcionales (`@FunctionalInterface`) y expresiones lambda.
* Gestión segura de ausencia de valor mediante **`Optional`** (sin uso de `null` ni llamadas a `.get()`).

---

## 🛠️ Requisitos del Entorno
* **JDK:** Java 21 LTS (OpenJDK / Eclipse Temurin)
* **Gestor de dependencias:** Apache Maven 3.8+
* **Pruebas:** JUnit Jupiter 5.10.2

---

## 🚀 Instrucciones de Ejecución

### 1. Compilar el proyecto:
```bash
mvn clean compile