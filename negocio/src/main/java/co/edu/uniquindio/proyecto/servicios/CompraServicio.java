package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.MedioPago;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface CompraServicio {

    Compra hacerCompra(Usuario usuario, List<ProductoCarrito> productoCarritos, MedioPago medioPago) throws Exception;

    List<Compra> obtenerComprasUsuario(String codigoUsuario) throws Exception;
}
