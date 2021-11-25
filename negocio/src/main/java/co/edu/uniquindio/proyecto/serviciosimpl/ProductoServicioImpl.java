package co.edu.uniquindio.proyecto.serviciosimpl;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;

    private final UsuarioRepo usuarioRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo, UsuarioRepo usuarioRepo) {
        this.productoRepo = productoRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Producto publicarProducto(Producto producto) throws Exception {
        return null;
    }

    @Override
    public void eliminarProducto(Producto producto) {

    }

    @Override
    public void actualizarProducto(Producto producto) throws Exception {
            if(productoRepo.findById(producto.getCodigo()).isEmpty()){
                throw new Exception("El producto no existe");
            }
            productoRepo.save(producto);
    }

    @Override
    public List<Producto> obtenerProductosCategoria(Categoria categoria) throws Exception {
        return null;
    }

    @Override
    public Comentario hacerComentario(Comentario comentario) throws Exception {
        return null;
    }

    @Override
    public List<Producto> buscarProductos(String busqueda) {
        return null;
    }

    @Override
    public List<Producto> obtenerProductosVendedor(String codigoVendedor) throws Exception{
        if (usuarioRepo.findById(codigoVendedor).isEmpty()) {
            throw new Exception("El codigo del producto no existe");
        }
        return productoRepo.listarProductosVendedor(codigoVendedor);
    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws Exception {
        Optional<Producto> buscado = productoRepo.findById(codigo);
        if(buscado.isEmpty()){
            throw new Exception("El producto no existe");
        }
        return buscado.get();
    }
}
