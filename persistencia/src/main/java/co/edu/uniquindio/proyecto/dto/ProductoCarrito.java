package co.edu.uniquindio.proyecto.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ProductoCarrito {
    @EqualsAndHashCode.Include
    private Integer id;
    private String nombre, imagen;
    private Double precio;
    private Integer unidades;
}
