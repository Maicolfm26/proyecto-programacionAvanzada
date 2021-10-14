package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Usuario extends Persona implements Serializable {

    @ElementCollection
    private List<String> telefonos;

    @OneToMany(mappedBy = "vendedor")
    private List<Producto> listaProductos;

    @ManyToMany(mappedBy = "usuariosFavoritos")
    private List<Producto> productosFavoritos;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "comprador")
    private List<Chat> chats;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> compras;

    @OneToMany(mappedBy = "usuario")
    private List<Subasta_Usuario> subastasUsuario;

    public Usuario(String codigo, String nombre, String email, String password, List<String> telefonos, Ciudad ciudad) {
        super(codigo, nombre, email, password);
        this.telefonos = telefonos;
        this.ciudad = ciudad;
    }
}
