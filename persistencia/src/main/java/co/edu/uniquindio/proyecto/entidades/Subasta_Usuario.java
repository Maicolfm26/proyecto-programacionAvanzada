package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

//Etiquetas para uso de métodos con el fin de acortar la cantidad de lineas de código

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
//Clase para la entidad Subasta_Usuario la cual tendrá su tabla correspondiente en Mysql.

public class Subasta_Usuario implements Serializable {

    //Se declaran los atributos de la entidad con sus respectivas restricciones.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Positive
    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private LocalDate fecha_subasta;

    //Se declaran las relaciones con otras entidades acompañadas de su respectiva multiplicidad.

    @ManyToOne
    @JoinColumn(nullable = false)
    private Subasta subasta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    //Constructor de la entidad.

    public Subasta_Usuario(Subasta subasta, Usuario usuario, Double valor, LocalDate fecha_subasta) {
        this.subasta = subasta;
        this.usuario = usuario;
        this.valor = valor;
        this.fecha_subasta = fecha_subasta;
    }
}
