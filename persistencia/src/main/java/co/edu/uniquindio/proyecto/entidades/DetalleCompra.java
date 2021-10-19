package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/*Etiquetas para reducir las lineas de codigo*/
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DetalleCompra implements Serializable {

    /*Atributos de la clase incluyendo su llave primaria*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Positive
    @Column(nullable = false)
    private Integer unidades;

    @Positive
    @Column(nullable = false)
    private Double precio_producto;

    /*Relaciones con las otras entidades en este caso Compra y Producto*/
    @ManyToOne
    @JoinColumn(nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    /*Constructor de la clase*/
    public DetalleCompra(Compra compra, Producto producto, Integer unidades, Double precio_producto) {
        this.compra = compra;
        this.producto = producto;
        this.unidades = unidades;
        this.precio_producto = precio_producto;
    }
}
