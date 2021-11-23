package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;

import java.util.List;

public interface ProductoServicio {

    Producto publicarProducto(Producto producto) throws Exception;

    void eliminarProducto(Producto producto);

    void actualizarProducto(Producto producto) throws Exception;

    List<Producto> obtenerProductosCategoria(Categoria categoria) throws Exception;

    Comentario hacerComentario(Comentario comentario) throws Exception;

    List<Producto> buscarProductos(String busqueda);

    List<Producto> obtenerProductosVendedor(String codigoVendedor) throws Exception;

}
