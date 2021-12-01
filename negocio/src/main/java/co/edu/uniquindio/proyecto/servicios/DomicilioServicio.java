package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Domicilio;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;


public interface DomicilioServicio {

    Domicilio obtenerDomicilio(Integer codigo) throws Exception;

    List<Domicilio> obtenerDomiciliosUsuario(String codigo);
}
