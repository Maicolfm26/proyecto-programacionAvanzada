package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/*
Etiquetas para uso de métodos con el fin de acortar la cantidad de lineas de código
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

/*
Clase para la entidad usuario la cual tendrá su tabla correspondiente en Mysql.
 */

public class Usuario extends Persona implements Serializable {

     /*
    Se declaran los atributos de la entidad con sus respectivas restricciones.
     */

    @ElementCollection
    @Column(nullable = false)
    private Set<String> telefonos;

     /*
    Se declaran las relaciones con otras entidades acompañadas de su respectiva multiplicidad.
     */

    @OneToMany(mappedBy = "vendedor")
    private List<Producto> listaProductos;

    @ManyToMany(mappedBy = "usuariosFavoritos")
    private List<Producto> productosFavoritos;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "comprador")
    private List<Chat> chats;

    @OneToMany(mappedBy = "usuario")
    private List<Domicilio> domicilios;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> compras;

    @OneToMany(mappedBy = "usuario")
    private List<Subasta_Usuario> subastasUsuario;

    @OneToMany(mappedBy = "usuario")
    private List<Favor> favoresPedidos;

     /*
    Constructor de la entidad.
     */

    public Usuario(String codigo, String nombre, String email, String password, Set<String> telefonos, List<Domicilio> domicilios) {
        super(codigo, nombre, email, password);
        this.telefonos = telefonos;
        this.domicilios = domicilios;
    }
}
