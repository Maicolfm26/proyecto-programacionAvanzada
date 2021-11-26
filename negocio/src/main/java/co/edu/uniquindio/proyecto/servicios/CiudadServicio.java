package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Ciudad;

import java.util.List;

public interface CiudadServicio {

    List<Ciudad> obtenerCiudades();

    Ciudad obtenerCiudad(Integer codigo) throws Exception;
}
