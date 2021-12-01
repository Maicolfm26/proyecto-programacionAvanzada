package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
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
public class DetalleCompraBean implements Serializable {

    private final CompraServicio compraServicio;

    @Value("#{param['compra']}")
    private String codigoCompra;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuario;

    @Getter @Setter
    private Compra compra;

    @Getter @Setter
    private List<DetalleCompra> detalleCompras;

    public DetalleCompraBean(CompraServicio compraServicio) {
        this.compraServicio = compraServicio;
    }

    @PostConstruct
    public void inicializar() throws Exception {
        if (codigoCompra != null && !codigoCompra.isEmpty()) {
            compra = compraServicio.obtenerCompra(Integer.parseInt(codigoCompra));
            detalleCompras = compra.getDetalleCompras();
        }
    }
}
