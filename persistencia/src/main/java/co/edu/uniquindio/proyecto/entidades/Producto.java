package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Positive
    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false)
    private String descripcion;

    @Positive
    @Column(nullable = false)
    private Integer precio;

    @Future
    @Column(nullable = false)
    private LocalDate fechaLimite;

    //Se entiende que la columna por defecto puede es nullable = true
    private Integer descuento;

    @ManyToOne
    private Usuario vendedor;

    @ManyToOne
    private Ciudad ciudad;

    @ManyToMany(mappedBy = "productos")
    private List<Categoria> categorias;

    @ManyToMany
    private List<Usuario> usuariosFavoritos;

    @OneToMany(mappedBy = "codigoProducto")
    private List<DetalleCompra> detalleCompras;

    @OneToMany(mappedBy = "codigoProductoChat")
    private List<Chat> chats;
}
