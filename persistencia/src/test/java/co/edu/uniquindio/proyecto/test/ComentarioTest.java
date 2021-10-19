package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
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
    Clase que usa con el fin de comprobar la funcionalidad del CRUD de la clase comentario
 */
public class ComentarioTest {

    /*
        Se declara la variable que nos ayuda con las funcionalidades de un repositorio.
     */
    @Autowired
    private ComentarioRepo comentarioRepo;

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que es posible registrar nuevas instancias de la clase comentario.
     */
    public void registrarCometarios(){
        Comentario comentarioBuscado = comentarioRepo.findById( 1 ).orElse(null);

        Assertions.assertNotNull(comentarioBuscado);
    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que se puede editar una instancia ya creada de la clase comentario.
     */
    public void editarComentario(){
        Comentario comentarioGuardado = comentarioRepo.findById( 1 ).orElse(null);

        comentarioGuardado.setCalificacion( 1 );
        comentarioRepo.save(comentarioGuardado);

        Comentario buscado = comentarioRepo.findById( 1 ).orElse(null);

        Assertions.assertEquals(1, buscado.getCalificacion());
    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica el buen funcionemiento de la eliminación de una instancia de la clase comentario.
     */
    public void eliminarComentarioTest(){
        comentarioRepo.deleteById( 1 );

        Comentario comentarioGuardado = comentarioRepo.findById( 1 ).orElse(null);
        Assertions.assertNull(comentarioGuardado);
    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que es posible la insercción de varias instancias de la clase comentario y además, lista todas
        las instancias creadas.
     */
    public void listarComentariosTest(){
        List<Comentario> comentarios = comentarioRepo.findAll();
        Assertions.assertEquals(2, comentarios.size());

        comentarios.forEach(System.out::println);
    }
}
