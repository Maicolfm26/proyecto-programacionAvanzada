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
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {
    @Autowired
    private ProductoRepo productoRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private CategoriaRepo categoriaRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;


    @Test
    @Sql("classpath:data.sql")
    public void registrarTest(){

        List<Categoria> listaCategorias = new ArrayList<Categoria>();
        Categoria categoria = categoriaRepo.findById(5).orElse(null);
        listaCategorias.add(categoria);

        Ciudad ciudad = ciudadRepo.findById("123").orElse(null);

        List<String> telefonosUsuario = new ArrayList<String>();
        telefonosUsuario.add("3128280008");
        telefonosUsuario.add("3223631932");

        List<String> listaImagenes= new ArrayList<String>();
        listaImagenes.add("C:/Users/ALEJANDRA/OneDrive/Escritorio/Smirnoff.jpg");

        Usuario usuario = new Usuario("1010","Laura", "laura@email.com","123", telefonosUsuario, ciudad);
        usuarioRepo.save(usuario);

        Producto producto = new Producto("Smirnoff",10,"Vodka Smirnoff lulo botella 375 ml",21000, LocalDate.of(2021,10,27),listaCategorias,listaImagenes,usuario,ciudad);

        Producto productoGuardado = productoRepo.save(producto);
        Assertions.assertNotNull(productoGuardado);
    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        productoRepo.deleteById(1);
        Producto productoConsultado = productoRepo.findById(1).orElse(null);
        Assertions.assertNull(productoConsultado);

    }

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


    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Producto> listaProductos =  productoRepo.findAll();
        listaProductos.forEach(p -> System.out.println(p));
    }
}
