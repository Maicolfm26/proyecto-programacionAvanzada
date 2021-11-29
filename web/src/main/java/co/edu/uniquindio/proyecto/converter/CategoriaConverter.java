package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class CategoriaConverter implements Converter<Categoria>, Serializable {

    private final CategoriaServicio categoriaServicio;

    public CategoriaConverter(CategoriaServicio categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    @Override
    public Categoria getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Categoria categoria = null;
        try {
            categoria = categoriaServicio.obtenerCategoria(Integer.parseInt(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoria;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Categoria categoria) {
        String codigo = "";
        if(categoria != null) {
            codigo = categoria.getCodigo().toString();
        }
        return codigo;
    }
}
