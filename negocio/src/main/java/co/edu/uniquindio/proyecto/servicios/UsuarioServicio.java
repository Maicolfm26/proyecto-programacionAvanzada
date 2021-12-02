package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {

    Usuario iniciarSesion(String email, String password) throws Exception;

    Usuario crearUsuario(Usuario usuario) throws Exception;

    void eliminarUsuario(String codigo) throws Exception;

    Usuario actualizarUsuario(String codigo, String email, Usuario usuarioActualizado) throws Exception;

    Usuario obtenerUsuario(String codigo) throws Exception;

    Usuario agregarProductoFavorito(Integer codigoProducto, String codigoUsuario) throws Exception;

    Usuario eliminarProductoFavorito(Integer codigoProducto, String codigoUsuario) throws Exception;

    Usuario buscarPorEmail(String email) throws Exception;

    public List<Producto> listarProductosFavoritos(String codigoVendedor)throws Exception;
}
