package inventario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventarioAcceptanceTest {

    @Test
    void usuarioPuedeRealizarFlujoCompletoDeInventario() {

        InventarioService inventario = new InventarioService();

        Producto producto =
                new Producto("ACC001", "Notebook Empresarial", 10, 550000);

        inventario.agregarProducto(producto);

        // Se reciben 5 unidades y posteriormente salen 3.
        inventario.aumentarStock("ACC001", 5);
        inventario.descontarStock("ACC001", 3);

        // El stock final esperado es 12.
        assertEquals(12, inventario.obtenerStock("ACC001"));

        Producto productoConsultado =
                inventario.buscarProducto("ACC001");

        assertNotNull(productoConsultado);
        assertEquals("Notebook Empresarial", productoConsultado.getNombre());
    }

    @Test
    void sistemaDebeRechazarSalidaMayorAlStockDisponible() {

        InventarioService inventario = new InventarioService();

        Producto producto =
                new Producto("ACC002", "Monitor Profesional", 2, 180000);

        inventario.agregarProducto(producto);

        assertThrows(
                IllegalArgumentException.class,
                () -> inventario.descontarStock("ACC002", 5)
        );

        // Al rechazarse la operación, el stock debe permanecer en 2.
        assertEquals(2, inventario.obtenerStock("ACC002"));
    }
}