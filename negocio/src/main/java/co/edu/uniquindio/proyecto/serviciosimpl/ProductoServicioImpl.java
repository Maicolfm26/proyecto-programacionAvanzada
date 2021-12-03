package co.edu.uniquindio.proyecto.serviciosImpl;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.filter.ProductoSpecification;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;
    private final UsuarioRepo usuarioRepo;
    private final CategoriaRepo categoriaRepo;
    private final ComentarioRepo comentarioRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo, UsuarioRepo usuarioRepo, CategoriaRepo categoriaRepo, ComentarioRepo comentarioRepo) {
        this.productoRepo = productoRepo;
        this.usuarioRepo = usuarioRepo;
        this.categoriaRepo = categoriaRepo;
        this.comentarioRepo = comentarioRepo;
    }

    @Override
    public Producto publicarProducto(Producto producto) throws Exception {
         return  productoRepo.save(producto);
    }

    @Override
    public void eliminarProducto(Integer codigoProducto) throws Exception {
        Producto producto = obtenerProducto(codigoProducto);
        producto.eliminarUsuariosFavoritos();
        for(Usuario usuario : producto.getUsuariosFavoritos()) {
            usuarioRepo.save(usuario);
            System.out.println("eliminado");
        }
        productoRepo.deleteById(codigoProducto);
    }

    @Override
    public void actualizarProducto(Producto producto) throws Exception {
            if(productoRepo.findById(producto.getCodigo()).isEmpty()){
                throw new Exception("El producto no existe");
            }
            productoRepo.save(producto);
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepo.listarProductos();
    }

    @Override
    public List<Producto> obtenerProductosCategoria(Integer codigoCategoria) {
        return categoriaRepo.obtenerProductosPorCategoria(codigoCategoria);
    }

    @Override
    public Comentario hacerComentario(Comentario comentario) throws Exception {
        return comentarioRepo.save(comentario);
    }

    @Override
    public List<Producto> buscarProductos(Specification<Producto> productoSpecification) {
        return productoRepo.findAll(productoSpecification);
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
