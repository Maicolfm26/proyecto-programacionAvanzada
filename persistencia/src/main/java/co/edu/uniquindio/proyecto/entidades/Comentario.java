package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;

//Etiquetas para uso de métodos con el fin de acortar la cantidad de lineas de código

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
//Clase para la entidad comentario la cual tendrá su tabla correspondiente en Mysql.

public class Comentario implements Serializable {

    //Se declaran los atributos de la entidad con sus respectivas restricciones.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private String mensaje;

    //Puede ser null mientras el dueño del producto no haga una respuesta
    private String respuesta;

    @Column(nullable = false)
    private LocalDate fecha_comentario;

    @Column(nullable = false)
    @Max(5)
    @Min(0)
    private Integer calificacion;

     //Se declaran las relaciones con otras entidades acompañadas de su respectiva multiplicidad.

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

     //Constructor de la entidad.

    public Comentario(String mensaje, LocalDate fecha_comentario, Integer calificacion, Producto producto, Usuario usuario) {
        this.mensaje = mensaje;
        this.fecha_comentario = fecha_comentario;
        this.calificacion = calificacion;
        this.producto = producto;
        this.usuario = usuario;
    }
}
