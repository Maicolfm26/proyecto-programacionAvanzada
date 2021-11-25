package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;

import java.util.List;

public interface ProductoServicio {

    void publicarProducto(Producto producto) throws Exception;

    void eliminarProducto(Producto producto) throws Exception;

    void actualizarProducto(Producto producto) throws Exception;

    List<Producto> obtenerProductosCategoria(Integer codigoCategoria) throws Exception;

    void hacerComentario(Comentario comentario) throws Exception;

    List<Producto> buscarProductos(String busqueda);

    List<Producto> obtenerProductosVendedor(String codigoVendedor) throws Exception;

    Producto obtenerProducto(Integer codigo)  throws Exception;
}
