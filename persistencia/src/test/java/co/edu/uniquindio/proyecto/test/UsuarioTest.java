package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Domicilio;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.DomicilioRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.*;

/*
Esta clase permite la realización de pruebas sobre las funcionalidades CRUD establecidas
para la entidad Usuario
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    /*
    Se declaran las variables que ayudarán a gestionar las funcionalidades crud a través del uso del
    repositorio correspondiente a cada entidad.
     */

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private DomicilioRepo domicilioRepo;

    /*
   Método mediante el que se desarrolla el test de la realización de un registro para un usuario,
   este método además de crear datos en su interior también extrae algunos de ellos del archivo
   data.sql.
    */

    @Test
    @Sql("classpath:data.sql")
    public void registrarTest(){

        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);
        List<Domicilio> domicilios = domicilioRepo.findAll();

        List<String> telefonosUsuario = new ArrayList<>();
        telefonosUsuario.add("3128280008");
        telefonosUsuario.add("3223631932");

        Usuario usuario = new Usuario(ciudad, "1010","Laura", "laura@email.com","123", telefonosUsuario);

        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        System.out.println(usuarioGuardado);
        Assertions.assertNotNull(usuarioGuardado);
    }

    /*
    Método mediante el que se desarrolla el test para la eliminación de un usuario, este método extrae
    algunos datos del archivo data.sql y realiza consultas a la base de datos.
     */

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        usuarioRepo.deleteById("100765489");
        Usuario usuarioConsultado = usuarioRepo.findById("100765489").orElse(null);
        Assertions.assertNull(usuarioConsultado);

    }

    /*
    Método mediante el que se desarrolla el test para la actualización de la información de un usuario,
    este método extrae algunos datos del archivo data.sql y realiza consultas a la base de datos.
     */

    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest()
    {
        Usuario usuarioGuardado = usuarioRepo.findById("42785998").orElse(null);
        usuarioGuardado.setNombre("Maria Fernanda Zapata");

        usuarioRepo.save(usuarioGuardado);

        Usuario usuarioBuscado = usuarioRepo.findById("42785998").orElse(null);
        Assertions.assertEquals("Maria Fernanda Zapata",usuarioBuscado.getNombre());
    }

    /*
    Método mediante el que se desarrolla el test para la obtención de una lista que contenga todos los
    usuarios registrados hasta el momento en la base de datos, este método extrae dichos datos del
    archivo data.sql.
     */

    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Usuario> listaUsuarios =  usuarioRepo.findAll();
        Assertions.assertEquals(5, listaUsuarios.size());

        listaUsuarios.forEach(u -> System.out.println(u));
    }
}
