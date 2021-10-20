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

public class Compra implements Serializable {

    /*
    Se declaran los atributos de la entidad con sus respectivas restricciones.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private LocalDate fechaCompra;

    @Column(nullable = false)
    private String medioPago;

    @Column(nullable = false)
    private Double precioEnvio;

    /*
    Se declaran las relaciones con otras entidades acompañadas de su respectiva multiplicidad.
     */

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Domicilio domicilio;

    /*
    Constructor de la entidad.
     */

    public Compra(LocalDate fechaCompra, String medioPago, Usuario usuario, List<DetalleCompra> detalleCompras, Domicilio domicilio, Double precioEnvio) {
        this.fechaCompra = fechaCompra;
        this.medioPago = medioPago;
        this.usuario = usuario;
        this.detalleCompras = detalleCompras;
        this.domicilio = domicilio;
        this.precioEnvio = precioEnvio;
    }
}
