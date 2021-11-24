package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ProductoServicioTest {

    private final ProductoServicio productoServicio;
    private final UsuarioServicio usuarioServicio;

    public ProductoServicioTest(ProductoServicio productoServicio, UsuarioServicio usuarioServicio) {
        this.productoServicio = productoServicio;
        this.usuarioServicio = usuarioServicio;
    }

    @Test
    public void publicarProductoTest() {
        Producto producto = new Producto();

        List<String> imagenes = new ArrayList<>();
        imagenes.add("foto1.png");
        imagenes.add("foto2.png");
        imagenes.add("foto3.png");



    }

}
