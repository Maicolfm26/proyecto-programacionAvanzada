package co.edu.uniquindio.proyecto.serviciosImpl;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepo;
    private final DomicilioRepo domicilioRepo;
    private final UsuarioRepo usuarioRepo;
    private final ProductoRepo productoRepo;
    private final DetalleCompraRepo detalleCompraRepo;

    public CompraServicioImpl(CompraRepo compraRepo, DomicilioRepo domicilioRepo, UsuarioRepo usuarioRepo, ProductoRepo productoRepo, DetalleCompraRepo detalleCompraRepo) {
        this.compraRepo = compraRepo;
        this.domicilioRepo = domicilioRepo;
        this.usuarioRepo = usuarioRepo;
        this.productoRepo = productoRepo;
        this.detalleCompraRepo = detalleCompraRepo;
    }

    @Override
    public Compra hacerCompra(Usuario usuario, List<ProductoCarrito> productosCarrito, MedioPago medioPago) throws Exception {
        Domicilio domicilio = domicilioRepo.findById(1).get();

        Compra compra = new Compra();
        compra.setFechaCompra(LocalDate.now());
        compra.setMedioPago(medioPago);
        compra.setUsuario(usuario);
        compra.setPrecioEnvio(5000.0);
        compra.setDomicilio(domicilio);

        Compra compraGuardada = compraRepo.save(compra);

        for(ProductoCarrito productoCarrito : productosCarrito) {
            DetalleCompra detalleCompra = new DetalleCompra();
            detalleCompra.setCompra(compraGuardada);
            detalleCompra.setProducto(productoRepo.findById(productoCarrito.getId()).get());
            detalleCompra.setUnidades(productoCarrito.getUnidades());
            detalleCompra.setPrecio_producto(productoCarrito.getPrecio());

            detalleCompraRepo.save(detalleCompra);
        }
        return compraGuardada;
    }

    @Override
    public List<Compra> obtenerComprasUsuario(String codigoUsuario) throws Exception{
        if (usuarioRepo.findById(codigoUsuario).isEmpty()) {
            throw new Exception("El codigo del producto no existe");
        }
        return compraRepo.listarComprasUsuario(codigoUsuario);
    }
}
