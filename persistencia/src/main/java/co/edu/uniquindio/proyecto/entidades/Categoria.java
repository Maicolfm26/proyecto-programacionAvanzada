
package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Categoria implements Serializable {

    @Id
    @Column(nullable = false, length = 50)
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @ManyToMany
    private List<Producto> productos;
}

