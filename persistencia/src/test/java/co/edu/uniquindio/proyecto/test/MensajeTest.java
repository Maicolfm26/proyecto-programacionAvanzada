package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
/*
Esta clase permite la realización de pruebas sobre las funcionalidades CRUD establecidas
para la entidad Usuario
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MensajeTest {

    /*Variables que hacen referencia a los repositorios que son de ayuda para buscar por id,
    eliminar por id entre otras que le agreguemos*/
    @Autowired
    private MensajeRepo mensajeRepo;

    @Autowired
    private ChatRepo chatRepo;


    /*Se crea el test de registrar un mensaje en este caso buscamos en el archivo .sql
     * mediante el repositorio las entidades, las enviamos al constructor lo guardamos
     * y verificamos que no este en null*/
    @Test
    @Sql("classpath:data.sql")
    public void registrarTest(){

        Chat chat = chatRepo.findById(1).orElse(null);
        Mensaje mensaje = new Mensaje("Hola, esta disponible este producto?","Juan", LocalDate.now(),chat);

        Mensaje mensajeGuardado = mensajeRepo.save(mensaje);
        System.out.println(mensajeGuardado);
        Assertions.assertNotNull(mensajeGuardado);

    }

    /*Creamos el test para elminar,en este caso eliminamos con la ayuda del repositorio el que tenga
     * id igual a 1, luego la buscamos y nos debera aparecer en null ya que estaria eliminada*/
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        mensajeRepo.deleteById(1);
        Mensaje mensajeConsultado = mensajeRepo.findById(1).orElse(null);
        Assertions.assertNull(mensajeConsultado);

    }


    /*Se crea el test de actualizar,en este caso buscamos en el archivo sql, luego le seteamos un nuevo dato en
    alguno de sus atributos, seguidamente lo guardamos y luego volvemos a buscar el mismo registro
    * y con la ayuda del asser deberemos indicar que esperamos el dato que hemos actualizado en el registro modificado*/
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest()
    {
        Mensaje mensajeGuardado = mensajeRepo.findById(1).orElse(null);
        mensajeGuardado.setEmisor("Camilo");

        mensajeRepo.save(mensajeGuardado);

        Mensaje mensajeBuscado = mensajeRepo.findById(1).orElse(null);
        Assertions.assertEquals("Camilo",mensajeBuscado.getEmisor());
    }


    /*Se crea el test de listar los registros, en este casi creamos una lista de la entidad y le decimos
     * al repositorio que nos devuelta todos los registros de esa entidad que se encuentren en el archivo.sql
     * luego con la ayuda de un ciclo las imprimimos*/
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Mensaje> listaMensajes =  mensajeRepo.findAll();
        Assertions.assertEquals(3, listaMensajes.size());

        listaMensajes.forEach(u -> System.out.println(u));
    }
}
