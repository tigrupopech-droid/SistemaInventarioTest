package inventario;

import java.util.ArrayList;
import java.util.List;

public class InventarioService {

    private final List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }

        if (buscarProducto(producto.getCodigo()) != null) {
            throw new IllegalArgumentException("El producto ya existe");
        }

        productos.add(producto);
    }

    public Producto buscarProducto(String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }

        return null;
    }

    public void aumentarStock(String codigo, int cantidad) {
        Producto producto = buscarProducto(codigo);

        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado");
        }

        producto.aumentarStock(cantidad);
    }

    public void descontarStock(String codigo, int cantidad) {
        Producto producto = buscarProducto(codigo);

        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado");
        }

        producto.descontarStock(cantidad);
    }

    public int obtenerStock(String codigo) {
        Producto producto = buscarProducto(codigo);

        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado");
        }

        return producto.getStock();
    }

    public int cantidadProductos() {
        return productos.size();
    }
}