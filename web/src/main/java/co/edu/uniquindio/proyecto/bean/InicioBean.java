package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
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

    @PostConstruct
    public void inicializar() {
        productos = productoServicio.listarProductos();
    }

    public String irADetalle(String id) {
        return "/detalle_producto?faces-redirect=true&amp;producto=" + id;
    }
}
