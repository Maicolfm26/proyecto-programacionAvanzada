package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaTest {

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Test
    public void registarCategoriaTest(){

        Categoria categoria = new Categoria("Ropa");

        Categoria categoriaGuardada = categoriaRepo.save(categoria);

        Assertions.assertNotNull(categoriaGuardada);

    }

    @Test
    @Sql("classpath:data.sql")
    public void editarCatregoriaTest(){
        Categoria categoriaGuardada = categoriaRepo.findById( 1 ).orElse(null);

        categoriaGuardada.setNombre("Motos");
        categoriaRepo.save(categoriaGuardada);

        Categoria categoriaBuscada = categoriaRepo.findById( 1 ).orElse(null);

        Assertions.assertEquals("Motos", categoriaBuscada.getNombre());
    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarCategoria(){
        categoriaRepo.deleteById( 1 );

        Categoria categoriaBuscada = categoriaRepo.findById( 1 ).orElse(null);
        Assertions.assertNull(categoriaBuscada);
    }
    
    @Test
    @Sql("classpath:data.sql")
    public void listarCategoria(){
        List<Categoria> categorias = categoriaRepo.findAll();
        Assertions.assertEquals(4, categorias.size());

        categorias.forEach(c -> System.out.println(c));
    }
}
