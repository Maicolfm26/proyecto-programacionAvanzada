package co.edu.uniquindio.proyecto.serviciosimpl;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
    @Override
    public Usuario iniciarSesion(String email, String password) throws Exception {
        return null;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) throws Exception {
        return null;
    }

    @Override
    public void eliminarUsuario(String codigo) throws Exception {

    }

    @Override
    public void actualizarUsuario(Usuario usuarioActualizado) throws Exception {

    }

    @Override
    public Usuario obtenerUsuario(String codigo) throws Exception {
        return null;
    }

    @Override
    public void agregarProductoFavoritos(Producto producto) {

    }

    @Override
    public void eliminarProductoFavoritos(Producto producto) {

    }
}
