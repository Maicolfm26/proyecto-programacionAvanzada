package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/*
Esta clase permite la realización de pruebas sobre las funcionalidades CRUD establecidas
para la entidad Usuario
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatTest {

    /*Variables que hacen referencia a los repositorios que son de ayuda para buscar por id,
    eliminar por id entre otras que le agreguemos*/
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private ProductoRepo productoRepo;
    @Autowired
    private MensajeRepo mensajeRepo;
    @Autowired
    private ChatRepo chatRepo;


    /*Se crea el test de registrar un chat en este caso buscamos en el archivo .sql
     * mediante el repositorio las entidades, las enviamos al constructor lo guardamos
     * y verificamos que no este en null*/
    @Test
    @Sql("classpath:data.sql")
    public void registrarTest(){

        Usuario comprador = usuarioRepo.findById("42785998").orElse(null);
        Producto productoGuardado = productoRepo.findById(1).orElse(null);
        List<Mensaje> listaMensajes =  mensajeRepo.findAll();

        Chat chat = new Chat(comprador, productoGuardado, listaMensajes);

        Chat chatGuardado = chatRepo.save(chat);

        System.out.println(chat);
        Assertions.assertNotNull(chatGuardado);
    }

    /*Creamos el test para elminar,en este caso eliminamos con la ayuda del repositorio el que tenga
     * id igual a 1, luego la buscamos y nos debera aparecer en null ya que estaria eliminada*/
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        chatRepo.deleteById(1);
        Chat chatConsultado = chatRepo.findById(1).orElse(null);
        Assertions.assertNull(chatConsultado);

    }


    /*Se crea el test de actualizar,en este caso buscamos en el archivo sql, luego le seteamos un nuevo dato en
        alguno de sus atributos, seguidamente lo guardamos y luego volvemos a buscar el mismo registro
        * y con la ayuda del asser deberemos indicar que esperamos el dato que hemos actualizado en el registro modificado*/
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest()
    {
        Chat chatGuardado = chatRepo.findById(1).orElse(null);
        Usuario comprador = usuarioRepo.findById("100765489").orElse(null);
        chatGuardado.setComprador(comprador);

        chatRepo.save(chatGuardado);

        Chat chatBuscado = chatRepo.findById(1).orElse(null);
        Assertions.assertEquals(comprador.getCodigo(),chatBuscado.getComprador().getCodigo());
    }


    /*Se crea el test de listar los registros, en este casi creamos una lista de la entidad y le decimos
     * al repositorio que nos devuelta todos los registros de esa entidad que se encuentren en el archivo.sql
     * luego con la ayuda de un ciclo las imprimimos*/
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Chat> listaChats =  chatRepo.findAll();
        Assertions.assertEquals(3, listaChats.size());

        listaChats.forEach(u -> System.out.println(u));
    }
}