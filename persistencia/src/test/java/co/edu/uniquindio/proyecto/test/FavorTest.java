package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
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
    Clase que tiene objetivo verificar la funcionalidad de la clase Favor.
 */
public class FavorTest {

    /*
        Se declara la variable que nos ayudara a gestionar las funcionalidades de un repositorio.
     */
    @Autowired
    private FavorRepo favorRepo;
    @Autowired
    private DomicilioRepo domicilioRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que se puede crear y registrar una nueva o varias instancias de la clase Favor.
     */
    public void registrarFavor(){

        Usuario usuario = usuarioRepo.findById("100765489").orElse(null);
        Domicilio domicilioOrigen = domicilioRepo.findById(1).orElse(null);
        Domicilio domicilioDestino = domicilioRepo.findById(2).orElse(null);


        Favor favor = new Favor("Recoger celular", "Entregar celular a tavo", domicilioOrigen,domicilioDestino, usuario);

        Favor favorGuardado = favorRepo.save(favor);

        Assertions.assertNotNull(favorGuardado);
    }
    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que la eliminación de una instancia de la clase ciudad funciona de manera correcta.
     */
    public void eliminarFavorTest() {

        favorRepo.deleteById(1);

        Favor FavorBuscado = favorRepo.findById(1).orElse(null);
        Assertions.assertNull(FavorBuscado);
    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que confirma el buen funcionamiento de la edición de una instancia de la clase Favor.
     */
    public void editartFavorTest() {
        Favor favorGuardado = favorRepo.findById(1).orElse(null);

        favorGuardado.setDescripcionDestino("recoger pc");
        favorRepo.save(favorGuardado);

        Favor buscado = favorRepo.findById(1).orElse(null);
        Assertions.assertEquals("recoger pc", buscado.getDescripcionDestino());
    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que se crearon varias instancias de una misma clase.
     */
    public void listarFavoresTest(){
        List<Favor> favores = favorRepo.findAll();
        Assertions.assertEquals(3, favores.size());

        favores.forEach(c -> System.out.println(c));
    }

}
