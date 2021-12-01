package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByEmailAndPassword(String email, String clave);

    Optional<Usuario> findByEmail(String email);

    @Query("select p from Usuario u, IN (u.productosFavoritos) p where u.codigo = :codigo")
    List<Producto> listarProductosFavoritos(String codigo);

}
