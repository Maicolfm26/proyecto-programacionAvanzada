package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
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

/*
    Clase que tiene como objetivo realzar las prubas de funcionalidad de la clase categoria.
 */
public class CategoriaTest {

    /*
        Se declara la variable que ayuda a la gestión de funcionalidades a traves del uso de repositorios.
     */
    @Autowired
    private CategoriaRepo categoriaRepo;

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que el registro de una nueva instacia se realizo con exito, es de tener en cuenta que en todos los
        métodos se utilizo unos datos creados anteriormente en el archivo data.sql
     */
    public void registarCategoriaTest(){

        Categoria categoria = new Categoria("Ropa");

        Categoria categoriaGuardada = categoriaRepo.save(categoria);

        Assertions.assertNotNull(categoriaGuardada);

    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que los cambios en una instancia se realizaron de forma exitosa.
     */
    public void editarCatregoriaTest(){
        Categoria categoriaGuardada = categoriaRepo.findById( 1 ).orElse(null);

        categoriaGuardada.setNombre("Motos");
        categoriaRepo.save(categoriaGuardada);

        Categoria categoriaBuscada = categoriaRepo.findById( 1 ).orElse(null);

        Assertions.assertEquals("Motos", categoriaBuscada.getNombre());
    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que la eliminación de una instacia se realizo de manera correcta.
     */
    public void eliminarCategoria(){
        categoriaRepo.deleteById( 1 );

        Categoria categoriaBuscada = categoriaRepo.findById( 1 ).orElse(null);
        Assertions.assertNull(categoriaBuscada);
    }
    
    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que todas las instancias fueron cargadas de manera correcta.
     */
    public void listarCategoria(){
        List<Categoria> categorias = categoriaRepo.findAll();
        Assertions.assertEquals(5, categorias.size());

        categorias.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:data.sql")
    /*
        Método que verifica que se traigan los productos de una determinada categoria
    */
    public void obtenerProductosPorCategoriaTest(){
        List<Producto> productos = categoriaRepo.obtenerProductosPorCategoria(1);
        Assertions.assertEquals(3, productos.size());

        productos.forEach(System.out::println);
    }
}
