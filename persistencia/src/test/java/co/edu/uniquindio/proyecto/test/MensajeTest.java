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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MensajeTest {

    @Autowired
    private MensajeRepo mensajeRepo;

    @Autowired
    private ChatRepo chatRepo;

    @Test
    @Sql("classpath:data.sql")
    public void registrarTest(){

        Chat chat = chatRepo.findById(1).orElse(null);
        Mensaje mensaje = new Mensaje("Hola, esta disponible este producto?","Juan", LocalDate.now(),chat);

        Mensaje mensajeGuardado = mensajeRepo.save(mensaje);
        System.out.println(mensajeGuardado);
        Assertions.assertNotNull(mensajeGuardado);

    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        mensajeRepo.deleteById(1);
        Mensaje mensajeConsultado = mensajeRepo.findById(1).orElse(null);
        Assertions.assertNull(mensajeConsultado);

    }

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

    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Mensaje> listaMensajes =  mensajeRepo.findAll();
        listaMensajes.forEach(u -> System.out.println(u));
    }
}
