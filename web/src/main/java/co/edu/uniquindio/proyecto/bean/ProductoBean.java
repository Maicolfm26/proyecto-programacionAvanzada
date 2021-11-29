package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ProductoBean implements Serializable {

    @Getter
    @Setter
    private Producto producto;

    private final ProductoServicio productoServicio;
    private final UsuarioServicio usuarioServicio;
    private final CiudadServicio ciudadServicio;
    private final CategoriaServicio categoriaServicio;
    private final DepartamentoServicio departamentoServicio;

    private ArrayList<String> imagenes;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    @Getter
    @Setter
    private List<Categoria> categorias;

    @Getter
    @Setter
    private List<Departamento> departamentos;

    @Getter
    @Setter
    private Departamento departamento;

    @Value(value = "#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;


    @Value("${upload.url}")
    private String urlUploads;

    public ProductoBean(ProductoServicio productoServicio, UsuarioServicio usuarioServicio, CiudadServicio ciudadServicio, CategoriaServicio categoriaServicio, DepartamentoServicio departamentoServicio) {
        this.productoServicio = productoServicio;
        this.usuarioServicio = usuarioServicio;
        this.ciudadServicio = ciudadServicio;
        this.categoriaServicio = categoriaServicio;
        this.departamentoServicio = departamentoServicio;
    }

    @PostConstruct
    public void inicializar() {
        this.producto = new Producto();
        this.imagenes = new ArrayList<>();
        this.categorias = categoriaServicio.obtenerCategorias();
        departamentos = departamentoServicio.obtenerDepartamentos();
    }

    public void actualizarCiudades() {
        ciudades = departamentoServicio.obtenerCiudadesPorDepartamento(departamento);
    }


    public String crearProducto() {
        if (usuarioSesion != null) {
            if (!imagenes.isEmpty()) {
                try {
                    producto.setVendedor(usuarioSesion);
                    producto.setImagenes(imagenes);
                    producto.setFechaLimite(LocalDate.now().plusMonths(1));
                    productoServicio.publicarProducto(producto);
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro exitoso");
                    FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
                    return "/detalle_producto?faces-redirect=true&amp;producto="+producto.getCodigo();
                } catch (Exception e) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                    FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
                }
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Debe de subir al menos una imagen del producto");
                FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
            }
        }
        return null;
    }

    public void subirImagenes(FileUploadEvent event) {
        UploadedFile imagen = event.getFile();
        String nombreImagen = copiarImagen(imagen);
        if (nombreImagen != null) {
            imagenes.add(nombreImagen);
        }
    }

    public String copiarImagen(UploadedFile imagen) {
        String ruta = null;
        try {
            File archivo = new File(urlUploads + File.separator + imagen.getFileName());
            OutputStream os = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), os);
            ruta = imagen.getFileName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ruta;
    }
}
