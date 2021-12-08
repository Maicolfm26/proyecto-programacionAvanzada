package co.edu.uniquindio.proyecto.serviciosimpl;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Usuario usuario = usuarioRepo.findByEmail(email).orElseThrow(() -> new Exception("Los datos de autenticacion son incorrectos"));

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        if(passwordEncryptor.checkPassword(password, usuario.getPassword())) {
            return usuario;
        } else {
            throw new Exception("Los datos de autenticacion son incorrectos");
        }
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(usuario.getCodigo());
        if (buscado.isPresent()) {
            throw new Exception("El código del usuario ya existe");
        }
        buscado = usuarioRepo.findByEmail(usuario.getEmail());
        if (buscado.isPresent()) {
            throw new Exception("El email del usuario ya existe");
        }
        if (usuario.getTelefonos().isEmpty()) {
            throw new Exception("Debe ingresar al menos un telefono");
        }

        try {
            usuario.getTelefonos().forEach(Long::parseLong);
        } catch (NumberFormatException e) {
            throw new Exception("El telefono debe de ser numerico");
        }
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        usuario.setPassword(passwordEncryptor.encryptPassword(usuario.getPassword()));

        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario buscarPorEmail(String email) throws Exception {
        return usuarioRepo.findByEmail(email).orElseThrow(() -> new Exception("El usuario con el email especificado no existe."));
    }

    @Override
    public void eliminarUsuario(String codigo) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        if (buscado.isEmpty()) {
            throw new Exception("El código del usuario no existe en nuestros registros");
        }
        for(Producto p : buscado.get().getListaProductos()){
            productoServicio.eliminarProducto(p.getCodigo());
        }
        usuarioRepo.deleteById(codigo);
    }

    @Override
    public Usuario actualizarUsuario(String codigo, String email, Usuario usuarioActualizado) throws Exception {
        Optional<Usuario> buscado;
        if (!codigo.equals(usuarioActualizado.getCodigo())) {
            buscado = usuarioRepo.findById(usuarioActualizado.getCodigo());
            if (buscado.isPresent()) {
                throw new Exception("El código del usuario ya existe");
            }
        }

        if(!email.equals(usuarioActualizado.getEmail())) {
            buscado = usuarioRepo.findByEmail(usuarioActualizado.getEmail());
            if (buscado.isPresent()) {
                throw new Exception("El email del usuario ya existe");
            }
        }

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        usuarioActualizado.setPassword(passwordEncryptor.encryptPassword(usuarioActualizado.getPassword()));

        return usuarioRepo.save(usuarioActualizado);
    }


    @Override
    public Usuario obtenerUsuario(String codigo) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        if (buscado.isEmpty()) {
            throw new Exception("El usuario no existe");
        }
        return buscado.get();
    }

    @Override
    public Usuario agregarProductoFavorito(Integer codigoProducto, String codigoUsuario) throws Exception {
        Usuario usuario = obtenerUsuario(codigoUsuario);
        Producto producto = productoServicio.obtenerProducto(codigoProducto);
        usuario.agregarProductoFavorito(producto);
        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario eliminarProductoFavorito(Integer codigoProducto, String codigoUsuario) throws Exception {
        Usuario usuario = obtenerUsuario(codigoUsuario);
        Producto producto = productoServicio.obtenerProducto(codigoProducto);
        usuario.eliminarProductoFavorito(producto);
        return usuarioRepo.save(usuario);
    }

    @Override
    public List<Producto> listarProductosFavoritos(String codigoVendedor) throws Exception {
        if (usuarioRepo.findById(codigoVendedor).isEmpty()) {
            throw new Exception("El codigo del vendedor no existe");
        }
        return usuarioRepo.listarProductosFavoritos(codigoVendedor);

    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public List<Object[]> listarProductosUsuario() {
        return usuarioRepo.listarUsuariosYProductos();
    }
}
