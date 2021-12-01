package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.MedioPago;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface CompraServicio {

    Compra hacerCompra(Compra compra, List<ProductoCarrito> productoCarritos) throws Exception;

    List<Compra> obtenerComprasUsuario(String codigoUsuario) throws Exception;

    List<MedioPago> listarMedioDePagos();

    Compra obtenerCompra(Integer codigoCompra) throws Exception;
}
