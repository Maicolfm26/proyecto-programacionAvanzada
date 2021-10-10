package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario implements Serializable {

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

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    public Comentario(String mensaje, LocalDate fecha_comentario, Integer calificacion, Producto producto, Usuario usuario) {
        this.mensaje = mensaje;
        this.fecha_comentario = fecha_comentario;
        this.calificacion = calificacion;
        this.producto = producto;
        this.usuario = usuario;
    }
}
