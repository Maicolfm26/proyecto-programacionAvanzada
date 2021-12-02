package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Domicilio;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.DomicilioServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    private final ProductoServicio productoServicio;
    private final UsuarioServicio usuarioServicio;

    private final DomicilioServicio domicilioServicio;

    public InicioBean(ProductoServicio productoServicio, UsuarioServicio usuarioServicio, DomicilioServicio domicilioServicio) {
        this.productoServicio = productoServicio;
        this.usuarioServicio = usuarioServicio;
        this.domicilioServicio = domicilioServicio;
    }

    @Getter @Setter
    private List<Domicilio> domicilios;

    @Getter
    @Setter
    private List<Producto> productos;

    @Getter
    @Setter
    private List<Producto> misProductos;

    @Getter
    @Setter
    private List<Producto> misProductosFavoritos;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuario;

    @PostConstruct
    public void inicializar() {
        productos = productoServicio.listarProductos();
        try {

            if (usuario != null) {
                misProductos = productoServicio.obtenerProductosVendedor(usuario.getCodigo());
                misProductosFavoritos = usuarioServicio.listarProductosFavoritos(usuario.getCodigo());
                domicilios = domicilioServicio.obtenerDomiciliosUsuario(usuario.getCodigo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(Producto producto) {
        if (usuario != null) {
            try {
                eliminarProductoFavoritoUsuario(producto.getCodigo());

                productoServicio.eliminarProducto(producto.getCodigo());
                misProductos.remove(producto);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto eliminado");
                FacesContext.getCurrentInstance().addMessage("msj-options", msg);

            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msj-options", msg);
            }
        }
    }

    public void eliminarProductoFavoritoUsuario(int codigo){
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        for(Usuario u : usuarios){
            for(Producto p : u.getProductosFavoritos()){
                if(p.getCodigo() == codigo){
                    try {
                        usuarioServicio.eliminarProductoFavorito(codigo, u.getCodigo());
                    }catch (Exception e){
                        new Exception("No se puede eliminar el producto favorito");
                    }
                }
            }
        }
    }

    public void reactivarProducto(Producto producto) {
        producto.setFechaLimite(LocalDate.now().plusMonths(1));
        try {
            productoServicio.actualizarProducto(producto);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Se a reactivado el producto");
            FacesContext.getCurrentInstance().addMessage("msj-options", msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-options", msg);
        }
    }


    public String irADetalle(String id) {
        return "/detalle_producto?faces-redirect=true&amp;producto=" + id;
    }

    public String irAEditar(String id) {
        return "editarProducto?faces-redirect=true&amp;producto=" + id;
    }
}
