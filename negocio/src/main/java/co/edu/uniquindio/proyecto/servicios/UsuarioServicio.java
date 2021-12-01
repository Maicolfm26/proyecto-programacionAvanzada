package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.Optional;

public interface UsuarioServicio {

    Usuario iniciarSesion(String email, String password) throws Exception;

    Usuario crearUsuario(Usuario usuario) throws Exception;

    void eliminarUsuario(String codigo) throws Exception;

    Usuario actualizarUsuario(Usuario usuarioActualizado) throws Exception;

    Usuario obtenerUsuario(String codigo) throws Exception;

    void agregarProductoFavoritos(Producto producto,Usuario usuario);

    void eliminarProductoFavoritos(Producto producto,Usuario usuario);

    Optional<Usuario> buscarPorEmail(String email) throws Exception;
}
