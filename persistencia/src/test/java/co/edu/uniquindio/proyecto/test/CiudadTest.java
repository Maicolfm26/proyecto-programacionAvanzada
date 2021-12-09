package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.DepartamentoRepo;
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
    Clase que tiene objetivo verificar la funcionalidad de la clase ciudad.
 */
public class CiudadTest {

    /*
        Se declara la variable que nos ayudara a gestionar las funcionalidades de un repositorio.
     */
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private DepartamentoRepo departamentoRepo;

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que se puede crear y registrar una nueva o varias instancias de la clase ciudad.
     */
    public void registrarCiudad(){

        Departamento departamento = departamentoRepo.findById(1).orElse(null);

        Ciudad ciudad = new Ciudad("Armenia", departamento);

        Ciudad ciudadGuardada = ciudadRepo.save(ciudad);

        Assertions.assertNotNull(ciudadGuardada);
    }
    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que la eliminación de una instancia de la clase ciudad funciona de manera correcta.
     */
    public void eliminarCiudadTest() {

        ciudadRepo.deleteById(1);

        Ciudad ciudadBuscada = ciudadRepo.findById(1).orElse(null);
        Assertions.assertNull(ciudadBuscada);
    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que confirma el buen funcionamiento de la edición de una instancia de la clase ciudad.
     */
    public void editartCiudadTest() {
        Ciudad ciudadGuardada = ciudadRepo.findById(1).orElse(null);

        ciudadGuardada.setNombre("Armenia 2");
        ciudadRepo.save(ciudadGuardada);

        Ciudad buscada = ciudadRepo.findById(1).orElse(null);
        Assertions.assertEquals("Armenia 2", buscada.getNombre());
    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que se crearon varias instancias de una misma clase.
     */
    public void listarCiudadesTest(){
        List<Ciudad> ciudades = ciudadRepo.findAll();
        Assertions.assertEquals(5, ciudades.size());

        ciudades.forEach(c -> System.out.println(c));
    }

    @Test
    @Sql("classpath:data.sql")
    public void listarCiudadesProductos(){
        List<Object[]> lista = ciudadRepo.listarCiudadesProductos();
        Assertions.assertEquals(5,lista.size());
    }

}
