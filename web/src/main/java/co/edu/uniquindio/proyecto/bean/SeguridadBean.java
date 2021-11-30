package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.MedioPago;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    private final UsuarioServicio usuarioServicio;

    public SeguridadBean(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter
    private Usuario usuarioSesion;

    @Getter @Setter
    private List<ProductoCarrito> productosCarrito;
    @Getter @Setter
    private Double subtotal;

    @Autowired
    private CompraServicio compraServicio;

    @PostConstruct
    public void inicializar() {
        subtotal = 0.0;
        productosCarrito = new ArrayList<>();
    }


    public String iniciarSesion() {
        try {
            usuarioSesion = usuarioServicio.iniciarSesion(email, password);
            autenticado = true;
            return "/index?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
        return null;
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public void agregarAlCarrito(Integer id, String nombre, String imagen, Double precio) {
        ProductoCarrito productoCarrito = new ProductoCarrito(id, nombre, imagen, precio, 1);
        if(!productosCarrito.contains(productoCarrito)) {
            productosCarrito.add(productoCarrito);
            subtotal += precio;
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto agregado al carrito");
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
    }

    public void eliminarDelCarrito(int posicion) {
        subtotal -= productosCarrito.remove(posicion).getPrecio();
    }

    public void actualizarSubTotal() {
        subtotal = 0.0;
        productosCarrito.forEach(p -> subtotal += p.getPrecio() * p.getUnidades());
    }

    public void comprar() {
        if(usuarioSesion != null && !productosCarrito.isEmpty()) {
            try {
                compraServicio.hacerCompra(usuarioSesion, productosCarrito, MedioPago.DAVIPLATA);
                productosCarrito.clear();
                subtotal = 0.0;
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra realizada correctamente");
                FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
            }
        }
    }

}
