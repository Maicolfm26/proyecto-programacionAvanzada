package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Imagen;
import co.edu.uniquindio.proyecto.repositorios.ImagenRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ImagenTest {

    @Autowired
    private ImagenRepo imagenRepo;

    @Test
    @Sql("classpath:data.sql")
    public void registrarImagenTest(){

        Imagen imagenBuscada = imagenRepo.findById(1).orElse(null);

        Assertions.assertNotNull(imagenBuscada);
    }

    @Test
    @Sql("classpath:data.sql")
    public void editarImagenTest(){
        Imagen imagenGuardada = imagenRepo.findById( 1 ).orElse(null);

        imagenGuardada.setRuta("rutaPrueba");
        imagenRepo.save(imagenGuardada);

        Imagen imagenBuscada = imagenRepo.findById( 1 ).orElse(null);

        Assertions.assertEquals("rutaPrueba", imagenBuscada.getRuta());
    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarImagenTest(){
        imagenRepo.deleteById( 1 );

        Imagen imagenBuscada = imagenRepo.findById( 1 ).orElse(null);
        Assertions.assertNull(imagenBuscada);
    }

    @Test
    @Sql("classpath:data.sql")
    public void listarImagenesTest(){
        List<Imagen> imagenes = imagenRepo.findAll();
        Assertions.assertEquals( 3, imagenes.size());

        imagenes.forEach(i -> System.out.println(i) );
    }
}
