# Taller UI Patterns (Java)

Controlador de interfaz para el Sistema de Configuración y Reservas Universitarias. Implementa un patrón por cada categoría de UI Patterns.

## Compilar y ejecutar

```bash
mkdir out
javac -encoding UTF-8 -d out src/navigation/*.java src/content/*.java src/input/*.java src/state/*.java src/main/*.java
java -Dfile.encoding=UTF-8 -cp out main.Main
```

## Salida en consola
<img width="735" height="617" alt="image" src="https://github.com/user-attachments/assets/9a742655-370a-4817-9c69-629ae8cf803b" />



## Cómo se evitan los Dark Patterns

La implementación evita los Dark Patterns porque ninguna operación bloquea, culpa ni confunde al usuario. `ValidadorForgiving` normaliza teléfonos y fechas con expresiones regulares y nunca lanza excepciones ni mensajes de error, por lo que no hay formularios que rechacen datos por su formato. `GestorSesionUI` permite armar la reserva sin cuenta (Lazy Registration) y solo pide registro al guardar, conservando el borrador para no perder el avance. Cancelar la reserva y cerrar sesión son métodos directos de un solo paso, con mensajes neutros ("Reserva cancelada.", "Sesión cerrada."), lo que evita el Roach Motel y el Confirmshaming. No existe ningún costo oculto ni flujo que desvíe la atención hacia una opción distinta de la elegida, por lo que tampoco hay Drip Pricing ni Misdirection.
Evitar los patrones oscuros es importante porque una interfaz debe ayudar al usuario a cumplir su objetivo, no manipularlo para beneficio de la organización. Prácticas como el Roach Motel, el Confirmshaming, el Drip Pricing o el Misdirection quitan autonomía, pueden causar pérdidas o malestar reales, destruyen la confianza en el servicio y, en muchos países, ya están sancionadas por las normas de protección al consumidor. Diseñar de forma transparente y sin fricciones innecesarias es, además, una responsabilidad ética de quien desarrolla el sistema.
