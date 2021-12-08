package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.dto.ProductoFilter;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.filter.ProductoSpecification;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {

    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoServicio.listarProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProducto(@PathVariable(name = "id") Integer id) {
        try {
            Producto producto = productoServicio.obtenerProducto(id);
            return ResponseEntity.status(200).body(producto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> borrarProducto(@PathVariable(name = "id") Integer id) {
        try {
            productoServicio.eliminarProducto(id);
            return ResponseEntity.status(200).body(new Mensaje(("El producto se elimino correctamente")));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        try {
            productoServicio.publicarProducto(producto);
            return ResponseEntity.status(201).body(new Mensaje(("El producto se creo correctamente")));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizarProducto(@RequestBody Producto producto) {
        try {
            productoServicio.actualizarProducto(producto);
            return ResponseEntity.status(200).body(new Mensaje(("El producto se actualizo correctamente")));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/buscar")
    public List<Producto> obtenerProductosFiltro(@RequestBody ProductoFilter productoFilter) {
        return productoServicio.buscarProductos(new ProductoSpecification(productoFilter));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> obtenerProductosUsuario(@PathVariable(name = "id") String id) {
        try {
            List<Producto> productos = productoServicio.obtenerProductosVendedor(id);
            return ResponseEntity.status(200).body(productos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/favoritos/{id}")
    public ResponseEntity<?> obtenerProductosFavoritos(@PathVariable(name = "id") String id) {
        try {
            List<Producto> productos = usuarioServicio.listarProductosFavoritos(id);
            return ResponseEntity.status(200).body(productos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }


}
