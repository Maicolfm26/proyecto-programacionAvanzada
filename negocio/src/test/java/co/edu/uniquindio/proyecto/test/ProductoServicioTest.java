package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
@Sql("classpath:data.sql")
public class ProductoServicioTest {

    @Autowired
    private ProductoServicio productoServicio;
    /*@Test
    public void publicarProductoTest() {
        try {
            Ciudad ciudad = departamentoServicio.obtenerCiudad(7);
            Producto producto = new Producto();

            List<String> imagenes = new ArrayList<>();
            imagenes.add("foto1.png");
            imagenes.add("foto2.png");
            imagenes.add("foto3.png");

        } catch (Exception e) {
            e.printStackTrace();
        }*/




    @Test
    public void obtenerProducto()
    {
        try {
            Producto producto = productoServicio.obtenerProducto(2);
            Assertions.assertNotNull(producto);
        } catch (Exception e) {
            Assertions.assertTrue(false,e.getMessage());
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
}
