package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {

    @Query("select c.productos from Categoria c where c.codigo = :codigoCategoria")
    List<Producto> obtenerProductosPorCategoria(Integer codigoCategoria);

}
