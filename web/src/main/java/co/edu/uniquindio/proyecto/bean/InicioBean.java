package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
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

    public InicioBean(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @Getter @Setter
    private List<Producto> productos;

    @Getter @Setter
    private List<Producto> misProductos;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuario;

    @PostConstruct
    public void inicializar() {
        productos = productoServicio.listarProductos();
        try {
            if(usuario != null) {
                misProductos = productoServicio.obtenerProductosVendedor(usuario.getCodigo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String irADetalle(String id) {
        return "/detalle_producto?faces-redirect=true&amp;producto=" + id;
    }
}
