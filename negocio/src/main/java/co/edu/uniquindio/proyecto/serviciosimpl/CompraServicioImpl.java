package co.edu.uniquindio.proyecto.serviciosImpl;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import org.springframework.stereotype.Service;

import javax.persistence.RollbackException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepo;
    private final DomicilioRepo domicilioRepo;
    private final UsuarioRepo usuarioRepo;
    private final ProductoRepo productoRepo;
    private final DetalleCompraRepo detalleCompraRepo;

    public CompraServicioImpl(CompraRepo compraRepo, DomicilioRepo domicilioRepo,  UsuarioRepo usuarioRepo, ProductoRepo productoRepo, DetalleCompraRepo detalleCompraRepo) {
        this.compraRepo = compraRepo;
        this.domicilioRepo = domicilioRepo;
        this.usuarioRepo = usuarioRepo;
        this.productoRepo = productoRepo;
        this.detalleCompraRepo = detalleCompraRepo;
    }

    @Override
    public Compra hacerCompra(Compra compra, List<ProductoCarrito> productosCarrito)throws Exception {
        Compra compraGuardada = null;
        if(validarUnidades(productosCarrito)) {
            compra.setFechaCompra(LocalDate.now());
             compraGuardada = compraRepo.save(compra);
            for (ProductoCarrito productoCarrito : productosCarrito) {
                    DetalleCompra detalleCompra = new DetalleCompra();
                    detalleCompra.setCompra(compraGuardada);
                    Producto producto = productoRepo.findById(productoCarrito.getId()).orElse(null);
                    producto.reducirUnidades(productoCarrito.getUnidades());
                    detalleCompra.setProducto(producto);
                    detalleCompra.setUnidades(productoCarrito.getUnidades());
                    detalleCompra.setPrecio_producto(productoCarrito.getPrecio());
                    productoRepo.save(producto);
                    detalleCompraRepo.save(detalleCompra);
            }
        }else{
            throw new Exception("No se puede hacer la compra porque no hay unidades suficientes");
        }
        return compraGuardada;
    }


    private Boolean validarUnidades(List<ProductoCarrito> productosCarrito){
        boolean respuesta = true;
        for(ProductoCarrito pc : productosCarrito ){
            Optional<Producto> producto = productoRepo.findById(pc.getId());
            if(producto.get().getUnidades() < pc.getUnidades()){
                respuesta = false;
                break;
            }
        }
        return respuesta;
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
