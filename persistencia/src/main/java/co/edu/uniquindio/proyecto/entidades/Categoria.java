
package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
/*
    Clase de categoria que tendrá su tabla respectiva en mysql
 */
public class Categoria implements Serializable {

    /*
        Se declaran los atributos de la clase con sus respectivas restricciones.
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Exclude
    private Integer codigo;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String nombre;

    /*
        Se declaran las relaciones con las demas entidades
     */
    @ManyToMany(mappedBy = "categorias")
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> productos;

    /*
        Se declara el constructor de la clase
     */
    public Categoria(String nombre) {
        this.nombre = nombre;
    }
}

