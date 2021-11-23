package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

//Etiquetas para uso de métodos con el fin de acortar la cantidad de lineas de código

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
//Clase para la entidad Subasta la cual tendrá su tabla correspondiente en Mysql.

public class Subasta implements Serializable {

    //Se declaran los atributos de la entidad con sus respectivas restricciones.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Future
    @Column(nullable = false)
    private LocalDate fechaLimite;

    //Se declaran las relaciones con otras entidades acompañadas de su respectiva multiplicidad.

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    @OneToMany(mappedBy = "subasta", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Subasta_Usuario> subastasUsuarios;

    //Constructor de la entidad.

    public Subasta(LocalDate fechaLimite, Producto producto) {
        this.fechaLimite = fechaLimite;
        this.producto = producto;
    }


}
