package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    private final ProductoServicio productoServicio;
    private final UsuarioServicio usuarioServicio;

    public InicioBean(ProductoServicio productoServicio, UsuarioServicio usuarioServicio) {
        this.productoServicio = productoServicio;
        this.usuarioServicio = usuarioServicio;
    }

    @Getter @Setter
    private List<Producto> productos;

    @Getter @Setter
    private List<Producto> misProductos;

    @Getter @Setter
    private List<Producto> misProductosFavoritos;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuario;

    @PostConstruct
    public void inicializar() {
        productos = productoServicio.listarProductos();
        try {
            if(usuario!=null) {
                misProductos = productoServicio.obtenerProductosVendedor(usuario.getCodigo());
                misProductosFavoritos = usuarioServicio.listarProductosFavoritos(usuario.getCodigo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String irADetalle(String id) {
        return "/detalle_producto?faces-redirect=true&amp;producto=" + id;
    }
}
