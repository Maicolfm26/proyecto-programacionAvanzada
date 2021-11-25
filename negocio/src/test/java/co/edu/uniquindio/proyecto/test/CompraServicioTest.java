package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DomicilioRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import co.edu.uniquindio.proyecto.servicios.DetalleCompraServicio;
import co.edu.uniquindio.proyecto.servicios.DomicilioServicio;
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
import java.util.Optional;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
@Sql("classpath:data.sql")
public class CompraServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private DetalleCompraServicio detalleCompraServicio;

    @Autowired
    private DomicilioServicio domicilioServicio;

    @Autowired
    private CompraServicio compraServicio;

    @Test
    public void hacerCompraTest() throws Exception {
        Usuario usuario = usuarioServicio.obtenerUsuario("100765489");
        List<DetalleCompra> detallesCompra = new ArrayList<>();
        DetalleCompra detalleCompra = detalleCompraServicio.obtenerDetalleCompra(1);
        detallesCompra.add(detalleCompra);
        Domicilio domicilio = domicilioServicio.obtenerDomicilio(1);

        Compra compra = new Compra(LocalDate.now(), MedioPago.NEQUI, usuario, detallesCompra, domicilio, 5000.0);

        Compra compraGuardada = compraServicio.hacerCompra(compra);
        Assertions.assertNotNull(compraGuardada);
    }

    @Test
    public void listarComprasUsuario() throws Exception {
        Usuario usuario = usuarioServicio.obtenerUsuario("100765489");
        List<Compra> comprasUsuario = compraServicio.obtenerComprasUsuario(usuario.getCodigo());
        Assertions.assertEquals(4,comprasUsuario.size());
    }
}
