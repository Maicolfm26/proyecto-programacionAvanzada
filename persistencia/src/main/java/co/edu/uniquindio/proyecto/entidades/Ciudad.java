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
/*
    Clase ciudad que va a tener uns tabla en la base de datos en mysql.
 */
public class Ciudad implements Serializable {

    /*
        Se declaran los atributos con sus respectivas restricciones sql.
     */
    @Id
    @Column(nullable = false, length = 50)
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false, length = 50)
    private String nombre;

    /*
        Se declaran las relaciones de la entidad con las de mas entidades.
     */
    @OneToMany(mappedBy = "ciudad")
    private List<Producto> productos;

    @OneToMany(mappedBy = "ciudad")
    private List<Usuario> usuarios;

    /*
        Método constructor de la clase.
     */

    public Ciudad(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}

