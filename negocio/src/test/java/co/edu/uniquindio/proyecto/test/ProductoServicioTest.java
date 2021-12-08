package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.dto.ProductoFilter;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.filter.ProductoSpecification;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
@Sql("classpath:data.sql")
public class ProductoServicioTest {

    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private CiudadServicio ciudadServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private CategoriaServicio categoriaServicio;

    @Test
    public void publicarProductoTest() {
        try {
            Ciudad ciudad = ciudadServicio.obtenerCiudad(1);

            List<String> imagenes = new ArrayList<>();
            imagenes.add("foto1.png");
            imagenes.add("foto2.png");
            imagenes.add("foto3.png");

            List<Categoria> categorias = categoriaServicio.obtenerCategorias();

            Usuario vendedor = usuarioServicio.obtenerUsuario("100765489");

            Producto producto = new Producto("Camisa", 5, "Camisa blanca", 25000.0, LocalDate.now().plusMonths(1), categorias, imagenes, vendedor, ciudad);

            Producto guardado = productoServicio.publicarProducto(producto);

            Assertions.assertNotNull(guardado);

        } catch (Exception e) {
            Assertions.assertTrue(false, e.getMessage());
        }
    }

    @Test
    public void eliminarProductoTest() {
        try {
            productoServicio.eliminarProducto(2);
            Producto producto = productoServicio.obtenerProducto(2);
            Assertions.assertTrue(false, "No se elimino el producto");
        } catch (Exception e) {
        }
    }

    @Test
    public void obtenerProductosCategoriaTest() {
        List<Producto> productos = productoServicio.obtenerProductosCategoria(1);
        Assertions.assertEquals(3, productos.size());
    }

    @Test
    public void hacerComentarioTest() {
        Producto producto = null;
        try {
            producto = productoServicio.obtenerProducto(1);
            Usuario usuario = usuarioServicio.obtenerUsuario("100765489");
            Comentario comentario = new Comentario("Esta bueno el producto", LocalDate.now(), 4, producto, usuario);
            Comentario comentarioGuardado = productoServicio.hacerComentario(comentario);
            Assertions.assertNotNull(comentarioGuardado);
        } catch (Exception e) {
            Assertions.assertTrue(false, e.getMessage());
        }
    }

    @Test
    public void buscarProductosTest() {
        ProductoSpecification productoSpecification = new ProductoSpecification(new ProductoFilter());
        productoSpecification.getProductoFilter().setNombre("Camisa");
        List<Producto> productos = productoServicio.buscarProductos(productoSpecification);
        Assertions.assertEquals(2, productos.size());
    }

    @Test
    public void obtenerProducto() {
        try {
            Producto producto = productoServicio.obtenerProducto(2);
            Assertions.assertNotNull(producto);
        } catch (Exception e) {
            Assertions.assertTrue(false, e.getMessage());
        }
    }

    @Test
    public void actualizarProducto() throws Exception {
        Producto producto = productoServicio.obtenerProducto(1);
        producto.setPrecio(1000.0);
        producto.setNombre("producto editado");
        productoServicio.actualizarProducto(producto);
        producto = productoServicio.obtenerProducto(1);
        Assertions.assertEquals("producto editado", producto.getNombre());
        Assertions.assertEquals(1000.0, producto.getPrecio());
    }

    @Test
    public void listarProductosVendedor() throws Exception {
        List<Producto> productos = productoServicio.obtenerProductosVendedor("100765489");
        Assertions.assertEquals(2, productos.size());
    }

    @Test
    public void listarProductosVendidosTest(){
        List<Object[]> listaProductosVendidos = productoServicio.listarProductoVendidos();
        Assertions.assertEquals(5,listaProductosVendidos.size());
        //listaProductosVendidos.forEach(p -> System.out.println(p[0]+"\t"+p[1]));
    }
}
