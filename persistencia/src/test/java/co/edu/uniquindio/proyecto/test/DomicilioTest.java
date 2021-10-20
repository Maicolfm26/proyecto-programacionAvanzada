package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Domicilio;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.DomicilioRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
/*
    Clase que tiene objetivo verificar la funcionalidad de la clase Domicilio.
 */
public class DomicilioTest {

    /*
        Se declara la variable que nos ayudara a gestionar las funcionalidades de un repositorio.
     */
    @Autowired
    private DomicilioRepo domicilioRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que se puede crear y registrar una nueva o varias instancias de la clase Domicilio.
     */
    public void registrarDomicilio(){

        Usuario usuario = usuarioRepo.findById("100765489").orElse(null);

        Domicilio domicilio = new Domicilio("Zuldemayda", "13", "23", "34", usuario);
        Domicilio domicilioGuardado = domicilioRepo.save(domicilio);

        Assertions.assertNotNull(domicilioGuardado);
    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que la eliminación de una instancia de la clase Domicilio funciona de manera correcta.
     */
    public void eliminarDomicilioTest() {

        domicilioRepo.deleteById(1);

        Domicilio domicilioBuscado = domicilioRepo.findById(1).orElse(null);
        Assertions.assertNull(domicilioBuscado);
    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que confirma el buen funcionamiento de la edición de una instancia de la clase Domicilio.
     */
    public void editartDomicilioTest() {
        Domicilio domicilioGuardado = domicilioRepo.findById(1).orElse(null);

        domicilioGuardado.setBarrio("Cañas gordas");
        domicilioRepo.save(domicilioGuardado);

        Domicilio domicilio = domicilioRepo.findById(1).orElse(null);
        Assertions.assertEquals("Cañas gordas", domicilio.getBarrio());
    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que se crearon varias instancias de una misma clase.
     */
    public void listarDomicilioTest(){
        List<Domicilio> domicilios = domicilioRepo.findAll();
        Assertions.assertEquals(3, domicilios.size());

        domicilios.forEach(c -> System.out.println(c));
    }

}
