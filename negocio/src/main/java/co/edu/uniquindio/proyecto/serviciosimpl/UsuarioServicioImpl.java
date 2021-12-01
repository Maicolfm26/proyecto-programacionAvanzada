package co.edu.uniquindio.proyecto.serviciosImpl;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final ProductoServicio productoServicio;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, ProductoRepo productoRepo, ProductoServicio productoServicio) {
        this.usuarioRepo = usuarioRepo;
        this.productoServicio = productoServicio;
    }

    @Override
    public Usuario iniciarSesion(String email, String password) throws Exception {
        return usuarioRepo.findByEmailAndPassword(email,password).orElseThrow(() -> new Exception("Los datos de autenticacion son incorrectos"));
    }

    private Optional<Usuario> buscarPorEmail(String email){
        return usuarioRepo.findByEmail(email);
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(usuario.getCodigo());
        if (buscado.isPresent()){
            throw new Exception("El código del usuario ya existe");
        }
        buscado = buscarPorEmail(usuario.getEmail());
        if (buscado.isPresent()){
            throw new Exception("El email del usuario ya existe");
        }
        if(usuario.getTelefonos().isEmpty()) {
            throw new Exception("Debe ingresar al menos un telefono");
        }

        try {
            usuario.getTelefonos().forEach(Integer::parseInt);
        } catch (NumberFormatException e) {
            throw new Exception("El telefono debe de ser numerico");
        }


        return usuarioRepo.save(usuario);
    }

    @Override
    public void eliminarUsuario(String codigo) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        if (buscado.isEmpty()){
            throw new Exception("El código del usuario no existe en nuestros registros");
        }
        usuarioRepo.deleteById(codigo);
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuarioActualizado) throws Exception {
        Optional<Usuario> buscado = buscarPorEmail(usuarioActualizado.getEmail());
        if (buscado.isEmpty()) {
            throw new Exception("El usuario no fue encontrado en nuestros registros");
        }
        return usuarioRepo.save(usuarioActualizado);
    }

    @Override
    public Usuario obtenerUsuario(String codigo) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        if(buscado.isEmpty()){
            throw new Exception("El usuario no existe");
        }
        return buscado.get();
    }

    @Override
    public void agregarProductoFavorito(Integer codigoProducto, String codigoUsuario) throws Exception {
        Usuario usuario = obtenerUsuario(codigoUsuario);
        Producto producto = productoServicio.obtenerProducto(codigoProducto);
        usuario.agregarProductoFavorito(producto);
        usuarioRepo.save(usuario);
    }

    @Override
    public void eliminarProductoFavorito(Integer codigoProducto, String codigoUsuario) throws Exception {
        Usuario usuario = obtenerUsuario(codigoUsuario);
        Producto producto = productoServicio.obtenerProducto(codigoProducto);
        usuario.eliminarProductoFavorito(producto);
        usuarioRepo.save(usuario);
    }
}
