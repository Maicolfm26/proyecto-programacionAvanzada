
package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS )
@MappedSuperclass
/*
    Clase persona que se define como una entidad que tendra una tabla en la base de datos de mysql.
 */
public class Persona implements Serializable {

    /*
        Se declaran los atributos de la entidad.
     */

    @Id
    @Column(length = 10)
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;
}

