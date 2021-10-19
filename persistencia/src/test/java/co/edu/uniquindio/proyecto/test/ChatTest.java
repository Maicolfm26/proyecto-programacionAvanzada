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


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatTest {

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private ProductoRepo productoRepo;
    @Autowired
    private MensajeRepo mensajeRepo;
    @Autowired
    private ChatRepo chatRepo;

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

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        chatRepo.deleteById(1);
        Chat chatConsultado = chatRepo.findById(1).orElse(null);
        Assertions.assertNull(chatConsultado);

    }

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

    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Chat> listaChats =  chatRepo.findAll();
        Assertions.assertEquals(3, listaChats.size());

        listaChats.forEach(u -> System.out.println(u));
    }
}