package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.DepartamentoServicio;
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
import java.util.List;

@Component
@ViewScoped
public class EditarUsuarioBean implements Serializable {

    private final UsuarioServicio usuarioServicio;

    private final DepartamentoServicio departamentoServicio;

    public EditarUsuarioBean(UsuarioServicio usuarioServicio, DepartamentoServicio departamentoServicio) {
        this.usuarioServicio = usuarioServicio;
        this.departamentoServicio = departamentoServicio;
    }

    private String email;
    private String cedula;

    @Getter @Setter
    private List<Departamento> departamentos;

    @Getter @Setter
    private Departamento departamento;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Value("#{param['usuario']}")
    private String codigoUsuario;

    @Getter @Setter
    private Usuario usuarioActualizado;

    @PostConstruct
    public void inicializar(){
        try {
            usuarioActualizado = usuarioServicio.obtenerUsuario(codigoUsuario);
            departamentos = departamentoServicio.obtenerDepartamentos();
            departamento = usuarioActualizado.getCiudad().getDepartamento();
            actualizarCiudades();
            this.email = usuarioActualizado.getEmail();
            this.cedula = usuarioActualizado.getCodigo();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void actualizarUsuario(){
        try {
            usuarioServicio.actualizarUsuario(cedula, email, usuarioActualizado);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Se actualizó el usuario");
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }catch (Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
    }

    public void actualizarCiudades() {
        ciudades = departamentoServicio.obtenerCiudadesPorDepartamento(departamento);
    }
}
