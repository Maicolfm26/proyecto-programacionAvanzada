package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/*
Etiquetas para uso de métodos con el fin de acortar la cantidad de lineas de código
 */

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
/*
Clase para la entidad producto la cual tendrá su tabla correspondiente en Mysql.
 */

public class Producto implements Serializable {

    /*
    Se declaran los atributos de la entidad con sus respectivas restricciones.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String nombre;

    @Positive
    @Column(nullable = false)
    @NotBlank
    private Integer unidades;

    @Column(nullable = false)
    @NotBlank
    private String descripcion;

    @Positive
    @Column(nullable = false)
    private Double precio;

    @Future
    @Column(nullable = false)
    private LocalDate fechaLimite;

    @Positive
    private Double descuento;

    @ElementCollection
    @Column(nullable = false)
    private List<String> imagenes;

     /*
    Se declaran las relaciones con otras entidades acompañadas de su respectiva multiplicidad.
     */

    @ManyToMany(mappedBy = "productos", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Categoria> categorias;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario vendedor;

    @ManyToMany
    @ToString.Exclude
    private List<Usuario> usuariosFavoritos;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Subasta> subastas;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Comentario> comentarios;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Chat> chats;

    /*
   Constructores de la entidad.
    */

    public Producto(String nombre, Integer unidades, String descripcion, Double precio, LocalDate fechaLimite, List<Categoria> categorias, List<String> imagenes, Usuario vendedor, Ciudad ciudad) {
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

    public Producto(String nombre, Integer unidades, String descripcion, Double precio, LocalDate fechaLimite, List<Categoria> categorias, List<String> imagenes, Usuario vendedor, List<Subasta> subastas, Ciudad ciudad) {
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
