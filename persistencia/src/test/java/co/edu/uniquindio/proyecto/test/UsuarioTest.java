package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Admin;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;


    @Test
    @Sql("classpath:data.sql")
    public void registrarTest(){

        Ciudad ciudad = new Ciudad("128","Ibague");
        ciudadRepo.save(ciudad);

        List<String> telefonosUsuario = new ArrayList<String>();
        telefonosUsuario.add("3128280008");
        telefonosUsuario.add("3223631932");


        Usuario usuario = new Usuario("1010","Laura", "laura@email.com","123", telefonosUsuario, ciudad);


        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        System.out.println(usuarioGuardado);
        Assertions.assertNotNull(usuarioGuardado);
    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        usuarioRepo.deleteById("100765489");
        Usuario usuarioConsultado = usuarioRepo.findById("100765489").orElse(null);
        Assertions.assertNull(usuarioConsultado);

    }

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

    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Usuario> listaUsuarios =  usuarioRepo.findAll();
        listaUsuarios.forEach(u -> System.out.println(u));
    }
}
