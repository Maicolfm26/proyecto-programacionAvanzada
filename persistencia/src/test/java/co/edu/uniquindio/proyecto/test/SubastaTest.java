package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Esta clase permite la realización de pruebas sobre las funcionalidades CRUD establecidas
para la entidad Subasta
*/

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class SubastaTest {

    /*
    Se declaran las variables que ayudarán a gestionar las funcionalidades crud a través del uso del
    repositorio correspondiente a cada entidad.
     */

    @Autowired
    private ProductoRepo productoRepo;
    @Autowired
    private SubastaRepo subastaRepo;

    /*
    En este metodo se verifica el metodo de guardar para la entidad subasta, se extrae un producto para crear
    una nueva subasta esta se guarda con el repositorio y se verifica que la subasta retornada no sea null
    esto quiere decir que se guardo.
     */

    @Test
    @Sql("classpath:data.sql")
    public void registrarTest() {
        Producto producto = productoRepo.findById(1).orElse(null);
        Subasta subasta = new Subasta(LocalDate.of(2022, 10, 19), producto);

        Subasta subastaGuardada = subastaRepo.save(subasta);

        Assertions.assertNotNull(subastaGuardada);
    }

    /*
    Método mediante el que se desarrolla el test para la eliminación de una subasta, este método elimina una subasta
    con el repositorio y despues consultamos con el repositorio la subasta previamente elimanada si nos devuelve
    null es que se borro correctamente.
     */

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        subastaRepo.deleteById(1);
        Subasta subastaConsultada = subastaRepo.findById(1).orElse(null);
        Assertions.assertNull(subastaConsultada);

    }

     /*
    Método mediante el que se desarrolla el test para la actualización de la información de una subasta,
    este método extrae una subasta con el repositorio le cambiamos la fecha limite y actualizamos la subasta
    con el repositorio, luego se vuelve a consultar la subasta y se mira si tiene la fecha que le actualizamos.
     */

    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest()
    {
        Subasta subastaGuardada = subastaRepo.findById(1).orElse(null);
        subastaGuardada.setFechaLimite(LocalDate.of(2022, 11, 20));

        subastaRepo.save(subastaGuardada);

        Subasta subastaBuscada = subastaRepo.findById(1).orElse(null);
        Assertions.assertEquals(LocalDate.of(2022, 11, 20), subastaBuscada.getFechaLimite());
    }

      /*
    Método mediante el que se desarrolla el test para la obtención de una lista que contenga todos las
    subastas registrados hasta el momento en la base de datos, este método extrae dichos datos del
    archivo data.sql y verificamos que el tamaño de la lista obtenida sea igual a los datos que insertamos en
    data.sql.
     */

    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Subasta> subastas = subastaRepo.findAll();
        Assertions.assertEquals(3, subastas.size());

        subastas.forEach(c -> System.out.println(c));
    }
}
