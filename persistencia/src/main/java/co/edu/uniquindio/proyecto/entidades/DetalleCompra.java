package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class DetalleCompra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @ManyToOne
    @JoinColumn (nullable = false)
    private Compra codigoCompra;

    @ManyToOne
    @JoinColumn (nullable = false)
    private Producto codigoProducto;

    @Positive
    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false)
    private Double precio_producto;

    public DetalleCompra(Integer codigo, Compra codigoCompra, Producto codigoProducto, Integer unidades, Double precio_producto) {
        this.codigo = codigo;
        this.codigoCompra = codigoCompra;
        this.codigoProducto = codigoProducto;
        this.unidades = unidades;
        this.precio_producto = precio_producto;
    }
}
