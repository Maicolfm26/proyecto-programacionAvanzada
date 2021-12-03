package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductoFilter {

    private String nombre;
    private Categoria categoria;
    private Double precioMinimo;
    private Double precioMaximo;
    private Ciudad ciudad;
    private Integer calificacionMinima;
    private Integer calificacionMaxima;

}
