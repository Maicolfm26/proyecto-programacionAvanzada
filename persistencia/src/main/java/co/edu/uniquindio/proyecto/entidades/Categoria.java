
package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor

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
    private Integer codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    /*
        Se declaran las relaciones con las demas entidades
     */
    @ManyToMany
    private List<Producto> productos;

    /*
        Se declara el constructor de la clase
     */
    public Categoria(String nombre) {
        this.nombre = nombre;
    }
}

