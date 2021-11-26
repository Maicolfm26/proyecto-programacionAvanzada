package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;

import java.util.List;

public interface DepartamentoServicio {

    List<Departamento> obtenerDepartamentos();

    List<Ciudad> obtenerCiudadesPorDepartamento(Departamento departamento);

    Departamento obtenerDepartamento(Integer codigo) throws Exception;
}
