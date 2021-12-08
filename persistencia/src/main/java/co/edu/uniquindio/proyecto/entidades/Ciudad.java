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
    Clase ciudad que va a tener uns tabla en la base de datos en mysql.
 */
public class Ciudad implements Serializable {

    /*
        Se declaran los atributos con sus respectivas restricciones sql.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String nombre;

    /*
        Se declaran las relaciones de la entidad con las de mas entidades.
     */

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> productos;

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Domicilio> domicilios;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Departamento departamento;

    /*
        Método constructor de la clase.
     */

    public Ciudad(String nombre, Departamento departamento) {
        this.nombre = nombre;
        this.departamento = departamento;
    }
}

