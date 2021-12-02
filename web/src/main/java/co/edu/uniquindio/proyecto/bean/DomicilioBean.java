package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Domicilio;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.DepartamentoServicio;
import co.edu.uniquindio.proyecto.servicios.DomicilioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.List;

@ViewScoped
@Component
public class DomicilioBean {

    @Getter @Setter
    private Domicilio domicilio;

    @Autowired
    private DepartamentoServicio departamentoServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private DomicilioServicio domicilioServicio;

    @Value(value = "#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Value("#{seguridadBean}")
    private SeguridadBean seguridadBean;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<Departamento> departamentos;

    @Getter @Setter
    private Departamento departamento;

    @PostConstruct
    public void inicializar() {
        this.domicilio = new Domicilio();
        this.ciudades = ciudadServicio.obtenerCiudades();
        this.departamentos = departamentoServicio.obtenerDepartamentos();
    }

    public void actualizarCiudades() {
        ciudades = departamentoServicio.obtenerCiudadesPorDepartamento(departamento);
    }

    public String crearDomicilio(){
        if (usuarioSesion != null) {
            try{
                domicilio.setUsuario(usuarioSesion);
                domicilio = domicilioServicio.crearDomicilio(domicilio);

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El domicilio se ha creado correctamente");
                FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
                return "/usuario/perfil?faces-redirect=true" ;
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
            }
        }
        return "";
    }
}
