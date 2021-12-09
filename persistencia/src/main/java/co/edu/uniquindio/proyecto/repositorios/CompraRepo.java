package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer> {

    @Query("select c from Compra c where c.usuario.codigo = :codigoUsuario")
    List<Compra> listarComprasUsuario(String codigoUsuario);

    @Query("select u.nombre, u.compras.size from Usuario u order by u.compras.size desc")
    List<Object[]> listarCompradoresFrecuentes();

}
