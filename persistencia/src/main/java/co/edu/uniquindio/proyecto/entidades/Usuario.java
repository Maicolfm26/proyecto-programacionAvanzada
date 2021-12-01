package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Etiquetas para uso de métodos con el fin de acortar la cantidad de lineas de código
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
/*
Clase para la entidad usuario la cual tendrá su tabla correspondiente en Mysql.
 */

public class Usuario extends Persona implements Serializable {

    /*
   Se declaran los atributos de la entidad con sus respectivas restricciones.
    */
    @ElementCollection
    @Column(nullable = false)
    @ToString.Exclude
    private List<String> telefonos;

     /*
    Se declaran las relaciones con otras entidades acompañadas de su respectiva multiplicidad.
     */

    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Producto> listaProductos;

    @ManyToMany(mappedBy = "usuariosFavoritos", fetch = FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @ToString.Exclude
    private Set<Producto> productosFavoritos = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "comprador", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Chat> chats;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Domicilio> domicilios;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Compra> compras;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Subasta_Usuario> subastasUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Favor> favoresPedidos;

     /*
    Constructor de la entidad.
     */

    public Usuario(Ciudad ciudad, String codigo, String nombre, String email, String password, List<String> telefonos) {
        super(codigo, nombre, email, password);
        this.telefonos = telefonos;
        this.ciudad = ciudad;
    }

    public void agregarProductoFavorito(Producto producto) {
        productosFavoritos.add(producto);
        producto.getUsuariosFavoritos().add(this);
    }

    public void eliminarProductoFavorito(Producto producto) {
        productosFavoritos.remove(producto);
        producto.getUsuariosFavoritos().remove(this);
    }
}
