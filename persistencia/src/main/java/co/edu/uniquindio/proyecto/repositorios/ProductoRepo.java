package co.edu.uniquindio.proyecto.repositorios;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer>, JpaSpecificationExecutor<Producto> {

    @Query("select p from Producto p where p.vendedor.codigo = :codigo")
    List<Producto> listarProductosVendedor(String codigo);

    @Query("select p from Producto p where p.fechaLimite > current_date")
    List<Producto> listarProductos();

    @Query("select d.producto.nombre, sum(d.unidades) from DetalleCompra d  group by d.producto order by sum(d.unidades) desc")
    List<Object[]> listarProductosVendidos();


    @Query("select c.nombre,count(p) from Producto p join p.categorias c group by c")
    List<Object[]> obtenerTotalProductosPorCategoria();
}
