package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoFilter;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.filter.ProductoSpecification;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
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
public class BusquedaBean implements Serializable {

    @Getter
    @Setter
    private String busqueda;

    @Getter
    @Setter
    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter
    @Setter
    private List<Producto> productos;

    @Getter
    @Setter
    private ProductoSpecification productoFilter;

    @Autowired
    private ProductoServicio productoServicio;

    @PostConstruct
    public void inicializar() {
        if (busquedaParam != null && !busquedaParam.isEmpty()) {
            try {
                productoFilter = new ProductoSpecification(new ProductoFilter());
                productoFilter.getProductoFilter().setNombre(busquedaParam);
                productos = productoServicio.buscarProductos(productoFilter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void aplicarFiltro() {
        productos = productoServicio.buscarProductos(productoFilter);
        System.out.println(productos);
    }
    public String buscar() {
        return "resultados?faces-redirect=true&amp;busqueda=" + busqueda;
    }
}

