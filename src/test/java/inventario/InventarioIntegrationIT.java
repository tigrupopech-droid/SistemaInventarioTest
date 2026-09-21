package inventario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventarioIntegrationIT {

    @Test
    void flujoCompletoDeInventario() {

        InventarioService inventario = new InventarioService();

        // Se crean productos
        Producto teclado =
                new Producto("P100", "Teclado Mecánico", 10, 35000);

        Producto mouse =
                new Producto("P101", "Mouse Gamer", 20, 18000);

        // Se integran los productos con el servicio
        inventario.agregarProducto(teclado);
        inventario.agregarProducto(mouse);

        // Se realizan operaciones de inventario
        inventario.aumentarStock("P100", 5);
        inventario.descontarStock("P101", 4);

        // Se comprueba el resultado completo
        assertEquals(15, inventario.obtenerStock("P100"));
        assertEquals(16, inventario.obtenerStock("P101"));
        assertEquals(2, inventario.cantidadProductos());

        assertNotNull(inventario.buscarProducto("P100"));
        assertNotNull(inventario.buscarProducto("P101"));
    }

    @Test
    void debeMantenerConsistenciaEntreProductoYServicio() {

        InventarioService inventario = new InventarioService();

        Producto monitor =
                new Producto("P200", "Monitor", 8, 150000);

        inventario.agregarProducto(monitor);
        inventario.descontarStock("P200", 3);

        Producto productoEncontrado =
                inventario.buscarProducto("P200");

        assertNotNull(productoEncontrado);
        assertEquals(5, productoEncontrado.getStock());
        assertEquals(5, inventario.obtenerStock("P200"));
    }
}