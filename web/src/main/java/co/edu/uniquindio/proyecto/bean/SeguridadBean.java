package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.Email.EmailSenderService;
import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Domicilio;
import co.edu.uniquindio.proyecto.entidades.MedioPago;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import co.edu.uniquindio.proyecto.servicios.DomicilioServicio;
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
    private final CompraServicio compraServicio;
    private final DomicilioServicio domicilioServicio;

    public SeguridadBean(UsuarioServicio usuarioServicio, CompraServicio compraServicio, DomicilioServicio domicilioServicio) {
        this.usuarioServicio = usuarioServicio;
        this.compraServicio = compraServicio;
        this.domicilioServicio = domicilioServicio;
    }

    @Getter @Setter
    private boolean autenticado;

    @Autowired
    private EmailSenderService senderService;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private Usuario usuarioSesion;

    @Getter @Setter
    private List<ProductoCarrito> productosCarrito;
    @Getter @Setter
    private Double subtotal;
    @Getter @Setter
    private Compra compra;
    @Getter @Setter
    private List<MedioPago> medioPagos;
    @Getter @Setter
    private List<Domicilio> domicilios;


    @PostConstruct
    public void inicializar() {
        subtotal = 0.0;
        productosCarrito = new ArrayList<>();
    }


    public String iniciarSesion() {
        try {
            usuarioSesion = usuarioServicio.iniciarSesion(email, password);
            autenticado = true;
            compra = new Compra();
            medioPagos = compraServicio.listarMedioDePagos();
            domicilios = domicilioServicio.obtenerDomiciliosUsuario(usuarioSesion.getCodigo());
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
                compra.setUsuario(usuarioSesion);
                compra = compraServicio.hacerCompra(compra, productosCarrito);
                senderService.sendEmail(usuarioSesion.getEmail(), "Compra éxitosa", "La compra se realizó de manera correcta\n" +
                        "Medio de pago: "+compra.getMedioPago().name()+"\nDetalles de la compra: \n"+getMensaje()+"Total: "+
                        subtotal+"\nDirección: "+compra.getDomicilio().getDireccion());
                productosCarrito.clear();
                subtotal = 0.0;
                compra = new Compra();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra realizada correctamente.");
                FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
            }
        }
    }



    private String getMensaje(){
        String mensaje = "";
        for(ProductoCarrito pc : productosCarrito){
            mensaje += "Producto: " + pc.getNombre() +"\tPrecio por unidad: "+pc.getPrecio()+"\tUnidades: "+
                    pc.getUnidades()+ "\tPrecio: "+pc.getUnidades()*pc.getPrecio()+"\n";
        }
        return  mensaje;
    }
}
