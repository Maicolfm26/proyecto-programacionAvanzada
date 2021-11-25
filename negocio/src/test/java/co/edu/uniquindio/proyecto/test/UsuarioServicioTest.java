package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
@Sql("classpath:data.sql")
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Test
    public void obtenerUsuario()
    {
        try {
            Usuario usuario = usuarioServicio.obtenerUsuario("1004917208");
            Assertions.assertNotNull(usuario);
        } catch (Exception e) {
            Assertions.assertTrue(false,e.getMessage());
        }
    }

    @Test
    public void agregarProductoFavorito()
    {
        try {
          Usuario usuario = usuarioServicio.obtenerUsuario("1004917208");
          Producto producto = productoServicio.obtenerProducto(2);
          usuarioServicio.agregarProductoFavoritos(producto,usuario);
          usuario.getProductosFavoritos().forEach(u -> System.out.println(u));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void eliminarProductoFavorito()
    {
        try {
            Usuario usuario = usuarioServicio.obtenerUsuario("1004917208");
            Producto producto = productoServicio.obtenerProducto(2);
            usuarioServicio.agregarProductoFavoritos(producto,usuario);
            usuarioServicio.agregarProductoFavoritos(producto,usuario);
            usuarioServicio.agregarProductoFavoritos(producto,usuario);
            usuarioServicio.agregarProductoFavoritos(producto,usuario);
            usuarioServicio.eliminarProductoFavoritos(producto,usuario);
            usuario.getProductosFavoritos().forEach(u -> System.out.println(u));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
