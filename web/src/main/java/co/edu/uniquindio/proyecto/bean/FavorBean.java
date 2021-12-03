package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Domicilio;
import co.edu.uniquindio.proyecto.entidades.Favor;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.DomicilioServicio;
import co.edu.uniquindio.proyecto.servicios.FavorServicio;
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
public class FavorBean {

    @Getter @Setter
    private Favor favor;

    @Autowired
    private DomicilioServicio domicilioServicio;

    @Autowired
    private FavorServicio favorServicio;

    @Value(value = "#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Getter @Setter
    private List<Domicilio> domicilios;

    @PostConstruct
    public void inicializar() {
        this.favor = new Favor();
        domicilios = domicilioServicio.obtenerDomiciliosUsuario(usuarioSesion.getCodigo());
    }

    public void crearFavor(){
        if(usuarioSesion!=null){
            try {
                favor.setUsuario(usuarioSesion);
                favor = favorServicio.crearFavor(favor);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El favor se ha creado correctamente");
                FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
            }
        }
    }
}
