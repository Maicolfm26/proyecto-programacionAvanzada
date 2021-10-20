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
    Clase que tiene objetivo verificar la funcionalidad de la clase Departamento.
 */
public class DepartamentoTest {

    /*
        Se declara la variable que nos ayudara a gestionar las funcionalidades de un repositorio.
     */
    @Autowired
    private DepartamentoRepo departamentoRepo;

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que se puede crear y registrar una nueva o varias instancias de la clase Departamento.
     */
    public void registrarDepartamento(){

        Departamento departamento = new Departamento("Armenia");
        Departamento departamentoGuardado = departamentoRepo.save(departamento);

        Assertions.assertNotNull(departamentoGuardado);
    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que la eliminación de una instancia de la clase Departamento funciona de manera correcta.
     */
    public void eliminarDepartamentoTest() {

        departamentoRepo.deleteById(1);

        Departamento departamentoBuscado = departamentoRepo.findById(1).orElse(null);
        Assertions.assertNull(departamentoBuscado);
    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que confirma el buen funcionamiento de la edición de una instancia de la clase Departamento.
     */
    public void editartDepartamentoTest() {
        Departamento departamentoGuardado = departamentoRepo.findById(1).orElse(null);

        departamentoGuardado.setNombre("Armenia 2");
        departamentoRepo.save(departamentoGuardado);

        Departamento departamento = departamentoRepo.findById(1).orElse(null);
        Assertions.assertEquals("Armenia 2", departamento.getNombre());
    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que se crearon varias instancias de una misma clase.
     */
    public void listarDepartamentoTest(){
        List<Departamento> departamentos = departamentoRepo.findAll();
        Assertions.assertEquals(5, departamentos.size());

        departamentos.forEach(c -> System.out.println(c));
    }

}
