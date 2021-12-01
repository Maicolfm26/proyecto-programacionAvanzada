package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Domicilio;
import co.edu.uniquindio.proyecto.servicios.DomicilioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class DomicilioConverter implements Converter<Domicilio>, Serializable {

    @Autowired
    private DomicilioServicio domicilioServicio;

    @Override
    public Domicilio getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Domicilio domicilio = null;
        try {
            domicilio = domicilioServicio.obtenerDomicilio(Integer.parseInt(s));
        }catch (Exception e){
            e.printStackTrace();
        }

        return domicilio;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Domicilio domicilio) {
        if(domicilio != null){
            return domicilio.getCodigo().toString();
        }
        return "";
    }
}
