package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    List<Ciudad> findByDepartamento(Departamento departamento);

    @Query("select c.nombre, c.productos.size from Ciudad c order by c.productos.size desc")
    List<Object[]> listarCiudadesProductos();
}

