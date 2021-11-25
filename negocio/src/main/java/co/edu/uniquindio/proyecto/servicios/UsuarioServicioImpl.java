package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{

    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario iniciarSesion(String email, String password) throws Exception {
        Optional<Usuario> usuario= usuarioRepo.findByEmailAndPassword(email,password);
        if (usuario.isEmpty()){
            throw new Exception("Los datos de autenticación no coinciden con ningún usuario registrado");
        }
        return usuario.get();
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
        buscado = usuarioRepo.findByUsername(usuario.getUsername());
        if (buscado.isPresent()){
            throw new Exception("El username del usuario ya existe");
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
        if (buscado.isEmpty()){
            throw new Exception("El usuario no fue encontrado en nuestros registros");
        }
        return usuarioRepo.save(usuarioActualizado);
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
