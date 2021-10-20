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
@ToString
/*
    Clase Departamento que va a tener uns tabla en la base de datos en mysql.
 */
public class Departamento implements Serializable {

    /*
        Se declaran los atributos con sus respectivas restricciones sql.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    /*
        Se declaran las relaciones de la entidad con las de mas entidades.
     */

    @OneToMany(mappedBy = "departamento")
    @ToString.Exclude
    private List<Ciudad> ciudades;

    /*
        Método constructor de la clase.
     */

    public Departamento(String nombre) {
        this.nombre = nombre;
    }
}

