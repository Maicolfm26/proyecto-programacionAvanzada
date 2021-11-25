package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Test
    @Sql("classpath:data.sql")
    public void crearUsuarioTest() {
        try {
        Ciudad ciudad = ciudadServicio.obtenerCiudad(1);

        Set<String> telefonosUsuario = new HashSet<>();
        telefonosUsuario.add("3128280008");
        telefonosUsuario.add("3223631932");

        Usuario nuevoUsuario = new Usuario(ciudad, "123", "Laura Suárez", "laurasuarez@email.com", "laurasuarez066","123", telefonosUsuario);

        Usuario comparador = usuarioServicio.crearUsuario(nuevoUsuario);
        Assertions.assertNotNull(comparador);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){
        try{
            usuarioServicio.eliminarUsuario("100765489");
            Assertions.assertTrue(true);
        }catch (Exception e){
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void actualizarTest() {
        try{
            Usuario usuario = usuarioServicio.obtenerUsuario("42785998");
            usuario.setPassword("new password");
            usuarioServicio.actualizarUsuario(usuario);
            Usuario modificado = usuarioServicio.obtenerUsuario("42785998");
            Assertions.assertEquals("new password",modificado.getPassword());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:data.sql")
    public void iniciarSesionTest() {
        try {
            Usuario usuario = usuarioServicio.iniciarSesion("maria@gmail.com", "1130");
            Assertions.assertNotNull(usuario);
        } catch (Exception e){
            Assertions.assertTrue(false,e.getMessage());
        }

    }
}

