# SistemaInventarioTest

Proyecto desarrollado para implementar una estrategia de automatización de pruebas utilizando Java, Maven, JUnit 5, Selenium y Git.

El sistema representa un inventario básico de productos y permite registrar productos, consultar existencias, aumentar stock y descontar stock.

## Tecnologías utilizadas

- Java 17
- Maven
- JUnit 5
- Selenium
- Git
- GitHub
- GitHub Actions

## Estructura del proyecto

El proyecto utiliza la estructura estándar de Maven:

- `src/main/java`: código principal de la aplicación.
- `src/test/java`: pruebas automatizadas.
- `pom.xml`: configuración de Maven y dependencias.
- `README.md`: documentación del proyecto.

## Estrategia de pruebas

Se implementaron distintos niveles de pruebas automatizadas.

### Pruebas unitarias

La clase `InventarioServiceTest` verifica de forma aislada las principales operaciones del sistema:

- Registro de productos.
- Aumento de stock.
- Descuento de stock.
- Validación de stock insuficiente.
- Validación de productos duplicados.

Las pruebas unitarias son ejecutadas mediante JUnit 5 y Maven Surefire.

### Pruebas de integración

La clase `InventarioIntegrationIT` comprueba la interacción entre `Producto` e `InventarioService`.

Estas pruebas validan operaciones completas sobre el inventario y son ejecutadas mediante Maven Failsafe durante la fase `verify`.

## Ejecución de pruebas

Para ejecutar las pruebas unitarias:

```bash
mvn test