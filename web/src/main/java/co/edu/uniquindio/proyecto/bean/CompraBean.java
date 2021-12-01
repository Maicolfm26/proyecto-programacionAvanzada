package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
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
public class CompraBean implements Serializable {

    @Autowired
    private final CompraServicio compraServicio;

    public CompraBean(CompraServicio compraServicio) {
        this.compraServicio = compraServicio;
    }

    @Getter @Setter
    private List<Compra> compras;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuario;

    @PostConstruct
    public void inicializar(){
        try {
            compras = compraServicio.obtenerComprasUsuario(usuario.getCodigo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String irADetalle(String id) {
        return "/detalle_compra?faces-redirect=true&amp;compra=" + id;
    }

}
