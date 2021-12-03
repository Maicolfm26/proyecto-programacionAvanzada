package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class PerfilBean implements Serializable {

    private final ProductoServicio productoServicio;
    private final CompraServicio compraServicio;

    public PerfilBean(ProductoServicio productoServicio, CompraServicio compraServicio) {
        this.productoServicio = productoServicio;
        this.compraServicio = compraServicio;
    }

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Getter@Setter
    private Usuario usuario;

    @Getter@Setter
    private  List<Producto> productos;

    @Getter@Setter
    private  List<Compra> compras;

    @Getter@Setter
    private List<String> telefonos;

    @PostConstruct
    public void inicializar() {
        usuario = usuarioSesion;
        try {
            productos = productoServicio.obtenerProductosVendedor(usuario.getCodigo());
            compras = compraServicio.obtenerComprasUsuario(usuario.getCodigo());
            if(usuario.getTelefonos() != null) {
                telefonos = usuario.getTelefonos();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String crearDomicilio(){
        return "/usuario/crearDomicilio?faces-redirect=true";
    }

}
