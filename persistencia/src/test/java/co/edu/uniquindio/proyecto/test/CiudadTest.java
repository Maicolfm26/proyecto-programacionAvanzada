package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import javax.swing.undo.CannotUndoException;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    public void registrarCiudad(){

        Ciudad ciudad = new Ciudad("123", "Armenia");

        Ciudad ciudadGuardada = ciudadRepo.save(ciudad);

        Assertions.assertNotNull(ciudadGuardada);
    }
    @Test
    @Sql("classpath:data.sql")
    public void eliminarCiudadTest() {

        ciudadRepo.deleteById("123");

        Ciudad ciudadBuscada = ciudadRepo.findById("123").orElse(null);
        Assertions.assertNull(ciudadBuscada);
    }

    @Test
    @Sql("classpath:data.sql")
    public void editartCiudadTest() {
        Ciudad ciudadGuardada = ciudadRepo.findById("123").orElse(null);

        ciudadGuardada.setNombre("Armenia 2");
        ciudadRepo.save(ciudadGuardada);

        Ciudad buscada = ciudadRepo.findById("123").orElse(null);
        Assertions.assertEquals("Armenia 2", buscada.getNombre());
    }

    @Test
    @Sql("classpath:data.sql")
    public void listarCiudadesTest(){
        List<Ciudad> ciudades = ciudadRepo.findAll();
        Assertions.assertEquals(5, ciudades.size());

        ciudades.forEach(c -> System.out.println(c));
    }

}
