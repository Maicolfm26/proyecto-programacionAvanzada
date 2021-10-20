package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/*
Etiquetas para uso de métodos con el fin de acortar la cantidad de lineas de código
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
/*
Clase para la entidad compra la cual tendrá su tabla correspondiente en Mysql.
 */

public class Favor implements Serializable {

    /*
    Se declaran los atributos de la entidad con sus respectivas restricciones.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private String descripcionOrigen;

    @Column(nullable = false)
    private String descripcionDestino;

    /*
    Se declaran las relaciones con otras entidades acompañadas de su respectiva multiplicidad.
     */

    @ManyToOne
    @JoinColumn(nullable = false)
    private Domicilio domicilioOrigen;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Domicilio domicilioDestino;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    /*
    Constructor de la entidad.
     */

    public Favor(String descripcionOrigen, String descripcionDestino, Domicilio domicilioOrigen, Domicilio domicilioDestino, Usuario usuario) {
        this.descripcionOrigen = descripcionOrigen;
        this.descripcionDestino = descripcionDestino;
        this.domicilioOrigen = domicilioOrigen;
        this.domicilioDestino = domicilioDestino;
        this.usuario = usuario;
    }
}
