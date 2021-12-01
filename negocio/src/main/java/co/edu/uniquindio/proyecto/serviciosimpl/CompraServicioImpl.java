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
    public Compra hacerCompra(Compra compra, List<ProductoCarrito> productosCarrito) throws Exception {
        compra.setFechaCompra(LocalDate.now());

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

    @Override
    public List<MedioPago> listarMedioDePagos() {
        return List.of(MedioPago.values());
    }

    @Override
    public Compra obtenerCompra(Integer codigoCompra) throws Exception {
        return compraRepo.findById(codigoCompra).orElseThrow(() -> new Exception("La compra no fue encontrada"));
    }
}
