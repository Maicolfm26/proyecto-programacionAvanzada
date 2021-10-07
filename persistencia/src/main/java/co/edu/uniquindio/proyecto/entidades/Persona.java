
package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS )
@MappedSuperclass
public class Persona implements Serializable {

    @Id
    @Column(nullable = false, length = 10)
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;
}

