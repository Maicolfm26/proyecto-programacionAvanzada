package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

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
@NoArgsConstructor
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
    @Positive
    private Integer descuento;

    @ManyToMany(mappedBy = "productos")
    private List<Categoria> categorias;

    @ElementCollection
    private List<String> imagenes;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario vendedor;

    @ManyToMany
    private List<Usuario> usuariosFavoritos;

    @OneToMany(mappedBy = "producto")
    private List<Subasta> subastas;

    @OneToMany(mappedBy = "producto")
    private List<Comentario> comentarios;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "producto")
    private List<DetalleCompra> detalleCompras;

    @OneToMany(mappedBy = "producto")
    private List<Chat> chats;

    public Producto(String nombre, Integer unidades, String descripcion, Integer precio, LocalDate fechaLimite, List<Categoria> categorias, List<String> imagenes, Usuario vendedor, Ciudad ciudad) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaLimite = fechaLimite;
        this.categorias = categorias;
        this.imagenes = imagenes;
        this.vendedor = vendedor;
        this.ciudad = ciudad;
    }

    public Producto(String nombre, Integer unidades, String descripcion, Integer precio, LocalDate fechaLimite, List<Categoria> categorias, List<String> imagenes, Usuario vendedor, List<Subasta> subastas, Ciudad ciudad) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaLimite = fechaLimite;
        this.categorias = categorias;
        this.imagenes = imagenes;
        this.vendedor = vendedor;
        this.subastas = subastas;
        this.ciudad = ciudad;
    }
}
