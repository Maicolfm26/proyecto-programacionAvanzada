package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
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
para la entidad Producto
*/

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ProductoTest {

    /*
    Se declaran las variables que ayudarán a gestionar las funcionalidades crud a través del uso del
    repositorio correspondiente a cada entidad.
     */

    @Autowired
    private ProductoRepo productoRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private CategoriaRepo categoriaRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;

    /*
    Método mediante el que se desarrolla el test de la realización de un registro para un producto,
    este método además de crear datos en su interior también extrae algunos de ellos del archivo
    data.sql.
     */

    @Test
    @Sql("classpath:data.sql")
    public void registrarTest(){

        List<Categoria> listaCategorias = new ArrayList<>();
        Categoria categoria = categoriaRepo.findById(5).orElse(null);
        listaCategorias.add(categoria);

        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);

        List<String> listaImagenes= new ArrayList<>();
        listaImagenes.add("C:/Users/ALEJANDRA/OneDrive/Escritorio/Smirnoff.jpg");

        Usuario usuario = usuarioRepo.findById("42785998").orElse(null);

        Producto producto = new Producto("Smirnoff",10,"Vodka Smirnoff lulo botella 375 ml",21000.0, LocalDate.of(2021,10,27),listaCategorias,listaImagenes,usuario,ciudad);

        Producto productoGuardado = productoRepo.save(producto);
        Assertions.assertNotNull(productoGuardado);
    }

    /*
    Método mediante el que se desarrolla el test para la eliminación de un producto, este método extrae
    algunos datos del archivo data.sql y realiza consultas a la base de datos.
     */

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        productoRepo.deleteById(1);
        Producto productoConsultado = productoRepo.findById(1).orElse(null);
        Assertions.assertNull(productoConsultado);

    }

     /*
    Método mediante el que se desarrolla el test para la actualización de la información de un producto,
    este método extrae algunos datos del archivo data.sql y realiza consultas a la base de datos.
     */

    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest()
    {
        Producto productoGuardado = productoRepo.findById(1).orElse(null);
        productoGuardado.setDescripcion("Descripción actualizada");

        productoRepo.save(productoGuardado);

        Producto productoBuscado = productoRepo.findById(1).orElse(null);
        Assertions.assertEquals("Descripción actualizada",productoBuscado.getDescripcion());
    }

      /*
    Método mediante el que se desarrolla el test para la obtención de una lista que contenga todos los
    productos registrados hasta el momento en la base de datos, este método extrae dichos datos del
    archivo data.sql.
     */

    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Producto> listaProductos =  productoRepo.findAll();
        Assertions.assertEquals(3, listaProductos.size());

        listaProductos.forEach(p -> System.out.println(p));
    }
}
