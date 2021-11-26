package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.servicios.DepartamentoServicio;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class DepartamentoConverter implements Converter<Departamento>, Serializable {

    private final DepartamentoServicio departamentoServicio;

    public DepartamentoConverter(DepartamentoServicio departamentoServicio) {
        this.departamentoServicio = departamentoServicio;
    }

    @Override
    public Departamento getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Departamento departamento =  null;
        try {
            departamento = departamentoServicio.obtenerDepartamento(Integer.parseInt(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departamento;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Departamento departamento) {
        String codigo = "";
        if(departamento != null) {
            codigo = departamento.getCodigo().toString();
        }
        return codigo;
    }

}
