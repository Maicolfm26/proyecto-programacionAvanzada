package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Domicilio;


public interface DomicilioServicio {

    Domicilio obtenerDomicilio(Integer codigo) throws Exception;
}
