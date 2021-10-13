package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Ciudad implements Serializable {

    @Id
    @Column(nullable = false, length = 50)
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    private List<Producto> productos;

    @OneToMany(mappedBy = "ciudad")
    private List<Usuario> usuarios;

    public Ciudad(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}

