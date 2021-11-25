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
}
