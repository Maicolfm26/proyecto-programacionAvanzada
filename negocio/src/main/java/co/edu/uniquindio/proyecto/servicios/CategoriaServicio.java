package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;

import java.util.List;

public interface CategoriaServicio {

    List<Categoria> obtenerCategorias();

    Categoria obtenerCategoria(Integer codigo) throws Exception;
}
