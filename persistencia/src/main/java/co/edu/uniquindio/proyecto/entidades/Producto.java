package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
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

    @PositiveOrZero
    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false)
    @NotBlank
    private String descripcion;

    @Positive
    @Column(nullable = false)
    private Double precio;

    private int promedio = 0;

    @Future
    @Column(nullable = false)
    private LocalDate fechaLimite;

    @PositiveOrZero
    private Double descuento = 0.0;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(nullable = false)
    private List<String> imagenes;

     /*
    Se declaran las relaciones con otras entidades acompañadas de su respectiva multiplicidad.
     */

    @ManyToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "codigo")
    private List<Categoria> categorias;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario vendedor;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name = "producto_usuario", joinColumns = @JoinColumn(name = "producto_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    @JsonIgnore
    private Set<Usuario> usuariosFavoritos = new HashSet<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<Subasta> subastas;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<Comentario> comentarios;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<DetalleCompra> detalleCompras;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
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

    @JsonIgnore
    public String getImagenPrincipal() {
        if (imagenes != null && !imagenes.isEmpty()) {
            return imagenes.get(0);
        }
        return "default-producto.jpg";
    }

    @JsonIgnore
    public String getFormatoCategorias() {
        String formato = "";
        for (Categoria categoria : categorias) {
            formato += categoria.getNombre();
        }
        return formato;
    }

    public int getPromedio() {
        int suma = 0;
        for (Comentario comentario : comentarios) {
            suma += comentario.getCalificacion();
        }
        return comentarios.size() == 0 ? 0 : suma / comentarios.size();
    }

    public void eliminarUsuariosFavoritos() {
        for(Usuario usuario: usuariosFavoritos) {
            usuario.eliminarProductoFavorito(this);
        }
    }

    public boolean verificarFecha(){
       return LocalDate.now().isBefore(fechaLimite) && unidades > 0;
    }

    public void reducirUnidades(Integer unidades){
        this.unidades -= unidades;
    }
}
