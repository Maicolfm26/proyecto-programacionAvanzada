package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.DepartamentoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class RegistroBean implements Serializable {

    @Getter
    @Setter
    private Usuario usuario;

    @Getter
    @Setter
    private List<Departamento> departamentos;

    @Getter
    @Setter
    private Departamento departamento;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    private final UsuarioServicio usuarioServicio;
    private final DepartamentoServicio departamentoServicio;

    public RegistroBean(UsuarioServicio usuarioServicio, DepartamentoServicio departamentoServicio) {
        this.usuarioServicio = usuarioServicio;
        this.departamentoServicio = departamentoServicio;
    }


    @PostConstruct
    public void inicializar() {
        usuario = new Usuario();
        departamentos = departamentoServicio.obtenerDepartamentos();
    }

    public void registrarUsuario() {
        try {
            usuarioServicio.crearUsuario(usuario);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro exitoso");
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
    }

    public void actualizarCiudades() {
        ciudades = departamentoServicio.obtenerCiudadesPorDepartamento(departamento);
    }

}
