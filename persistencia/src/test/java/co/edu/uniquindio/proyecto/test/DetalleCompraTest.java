package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetalleCompraTest {

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Test
    @Sql("classpath:data.sql")
    public void registrarTest(){

        Compra compra = compraRepo.findById(1).orElse(null);
        Producto producto = productoRepo.findById(1).orElse(null);

        DetalleCompra detalleCompra = new DetalleCompra(compra,producto,10,20000.00);

        DetalleCompra detalleCompraGuardado = detalleCompraRepo.save(detalleCompra);
        System.out.println(detalleCompraGuardado);
        Assertions.assertNotNull(detalleCompraGuardado);

    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        detalleCompraRepo.deleteById(1);
        DetalleCompra detalleCompraConsultada = detalleCompraRepo.findById(1).orElse(null);
        Assertions.assertNull(detalleCompraConsultada);

    }

    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest()
    {
        DetalleCompra detalleCompraGuardado = detalleCompraRepo.findById(1).orElse(null);
        detalleCompraGuardado.setPrecio_producto(50000.00);

        detalleCompraRepo.save(detalleCompraGuardado);

        DetalleCompra detalleCompraBuscada = detalleCompraRepo.findById(1).orElse(null);
        Assertions.assertEquals(50000.00,detalleCompraBuscada.getPrecio_producto());
    }

    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<DetalleCompra> listaDetalles =  detalleCompraRepo.findAll();
        Assertions.assertEquals(3, listaDetalles.size());

        listaDetalles.forEach(u -> System.out.println(u));
    }
}
