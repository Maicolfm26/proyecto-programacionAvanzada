package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.Email.EmailSenderService;
import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.AdminServicio;
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
    private final AdminServicio adminServicio;

    public SeguridadBean(UsuarioServicio usuarioServicio, CompraServicio compraServicio, DomicilioServicio domicilioServicio, AdminServicio adminServicio) {
        this.usuarioServicio = usuarioServicio;
        this.compraServicio = compraServicio;
        this.domicilioServicio = domicilioServicio;
        this.adminServicio = adminServicio;
    }

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private boolean autenticadoAdmin;

    @Autowired
    private EmailSenderService senderService;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private Usuario usuarioSesion;

    @Getter @Setter
    private Admin adminSesion;

    @Getter @Setter
    private List<ProductoCarrito> productosCarrito;
    @Getter @Setter
    private Double subtotal;
    @Getter @Setter
    private Compra compra;
    @Getter @Setter
    private List<MedioPago> medioPagos;


    @PostConstruct
    public void inicializar() {
        subtotal = 0.0;
        productosCarrito = new ArrayList<>();
    }


    public String iniciarSesionAdmin() {
        try {
            adminSesion = adminServicio.iniciarSesion(email, password);
            autenticadoAdmin = true;
            return "/admin/index?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
        return null;
    }

    public String iniciarSesion() {
        try {
            usuarioSesion = usuarioServicio.iniciarSesion(email, password);
            autenticado = true;
            compra = new Compra();
            medioPagos = compraServicio.listarMedioDePagos();
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

    public void agregarAlCarrito(Integer id, String nombre, String imagen, Double precio, Double descuento) {
        ProductoCarrito productoCarrito = new ProductoCarrito(id, nombre, imagen, precio, 1, descuento);
        if(!productosCarrito.contains(productoCarrito)) {
            productosCarrito.add(productoCarrito);
            subtotal += precio - ((precio*descuento)/100);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto agregado al carrito");
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
    }

    public void eliminarDelCarrito(int posicion) {
        ProductoCarrito p = productosCarrito.get(posicion);
        subtotal -= (p.getPrecio()*p.getUnidades()-((p.getPrecio()*p.getUnidades()*p.getDescuento())/100));
        productosCarrito.remove(posicion);
    }

    public void actualizarSubTotal() {
        subtotal = 0.0;
        productosCarrito.forEach(p -> subtotal +=
                (p.getPrecio() * p.getUnidades()- ((p.getPrecio()*p.getUnidades()*p.getDescuento())/100)));
    }

    public void comprar() {
        if(usuarioSesion != null && !productosCarrito.isEmpty()) {
           try {
                compra.setUsuario(usuarioSesion);
                compra.setTotal(subtotal);
                compra = compraServicio.hacerCompra(compra, productosCarrito);
                Thread hilo = new Thread(() -> senderService.sendEmail(usuarioSesion.getEmail(), "Compra éxitosa", "La compra se realizó de manera correcta\n" +
                        "Medio de pago: "+compra.getMedioPago().name()+"\nDetalles de la compra: \n"+getMensaje()+"Total: "+
                        subtotal+"\nDirección: "+compra.getDomicilio().getDireccion()));
                hilo.run();
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
                    pc.getUnidades()+ "\tPrecio: "+pc.getUnidades()*pc.getPrecio()+"\n"+ "Descuento:\t"+
                    pc.getDescuento() +"\nSubtotal:\t"+ (pc.getPrecio()*pc.getUnidades()-
                    ((pc.getPrecio()*pc.getDescuento()*pc.getUnidades())/100))+"\n";
        }
        return  mensaje;
    }

}
