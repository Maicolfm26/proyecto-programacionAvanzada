package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;

import java.util.List;

public interface CompraServicio {

    Compra hacerCompra(Compra compra) throws Exception;

    List<Compra> obtenerComprasUsuario(String codigoUsuario) throws Exception;
}
