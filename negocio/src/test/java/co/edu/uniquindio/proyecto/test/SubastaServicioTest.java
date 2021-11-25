package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.SubastaServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class SubastaServicioTest {

    @Autowired
    private SubastaServicio subastaServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Test
    public void crearSubasta()
    {
        try {
            Producto producto = productoServicio.obtenerProducto(2);
            Subasta subasta = new Subasta();
            subasta.setFechaLimite(LocalDate.now().plusMonths(2));
            subasta.setProducto(producto);
            subastaServicio.crearSubasta(subasta);
            producto.getSubastas().forEach(u -> System.out.println(u));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
