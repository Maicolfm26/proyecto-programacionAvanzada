package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
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
public class ComentarioTest {

    @Autowired
    private ComentarioRepo comentarioRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private ProductoRepo productoRepo;

    @Test
    @Sql("classpath:data.sql")
    public void registrarCometarios(){
        Usuario usuario = usuarioRepo.findById("42785998").orElse(null);
        Producto productoGuardado = productoRepo.findById(1).orElse(null);

        Comentario comentario = new Comentario("Me interesa el producto", LocalDate.now(), 4, productoGuardado, usuario);

        Comentario comentarioGuardado = comentarioRepo.save(comentario);

        Assertions.assertNotNull(comentarioGuardado);
    }

    @Test
    @Sql("classpath:data.sql")
    public void editarComentario(){
        Comentario comentarioGuardado = comentarioRepo.findById( 1 ).orElse(null);

        comentarioGuardado.setCalificacion( 1 );
        comentarioRepo.save(comentarioGuardado);

        Comentario buscado = comentarioRepo.findById( 1 ).orElse(null);

        Assertions.assertEquals(1, buscado.getCalificacion());
    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarComentarioTest(){
        comentarioRepo.deleteById( 1 );

        Comentario comentarioGuardado = comentarioRepo.findById( 1 ).orElse(null);
        Assertions.assertNull(comentarioGuardado);
    }

    @Test
    @Sql("classpath:data.sql")
    public void listarComentariosTest(){
        List<Comentario> comentarios = comentarioRepo.findAll();
        Assertions.assertEquals(2, comentarios.size());

        comentarios.forEach(System.out::println);
    }
}
