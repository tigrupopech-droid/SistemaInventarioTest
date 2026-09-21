package inventario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventarioServiceTest {

    private InventarioService inventario;

    @BeforeEach
    void prepararPrueba() {
        inventario = new InventarioService();
    }

    @Test
    void debeAgregarProducto() {
        Producto producto =
                new Producto("P001", "Teclado", 10, 15000);

        inventario.agregarProducto(producto);

        assertEquals(1, inventario.cantidadProductos());
        assertNotNull(inventario.buscarProducto("P001"));
    }

    @Test
    void debeAumentarStock() {
        Producto producto =
                new Producto("P002", "Mouse", 10, 10000);

        inventario.agregarProducto(producto);
        inventario.aumentarStock("P002", 5);

        assertEquals(15, inventario.obtenerStock("P002"));
    }

    @Test
    void debeDescontarStock() {
        Producto producto =
                new Producto("P003", "Monitor", 10, 120000);

        inventario.agregarProducto(producto);
        inventario.descontarStock("P003", 3);

        assertEquals(7, inventario.obtenerStock("P003"));
    }

    @Test
    void noDebePermitirStockInsuficiente() {
        Producto producto =
                new Producto("P004", "Notebook", 2, 450000);

        inventario.agregarProducto(producto);

        assertThrows(
                IllegalArgumentException.class,
                () -> inventario.descontarStock("P004", 5)
        );
    }

    @Test
    void noDebePermitirProductosDuplicados() {
        Producto producto1 =
                new Producto("P005", "Impresora", 5, 90000);

        Producto producto2 =
                new Producto("P005", "Impresora", 8, 90000);

        inventario.agregarProducto(producto1);

        assertThrows(
                IllegalArgumentException.class,
                () -> inventario.agregarProducto(producto2)
        );
    }
}