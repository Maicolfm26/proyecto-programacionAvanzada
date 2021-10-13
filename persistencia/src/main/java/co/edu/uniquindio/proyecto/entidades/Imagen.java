package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Imagen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private String ruta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    public Imagen(String ruta, Producto producto) {
        this.ruta = ruta;
        this.producto = producto;
    }
}
