package co.edu.uniquindio.proyecto.repositorios;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    @Query("select p from Producto p where p.vendedor.codigo = :codigo")
    List<Producto> listarProductosVendedor(String codigo);

    @Query("select p from Producto p where p.nombre like concat('%', :busqueda, '%') or p.descripcion like concat('%', :busqueda, '%')")
    List<Producto> buscarProductos(String busqueda);

<<<<<<< HEAD
=======
    @Query("select p from Producto p where p.fechaLimite > current_date")
    List<Producto> listarProductos();
>>>>>>> a67d543b2a377d2e083628803730582ce7ceb81f
}
