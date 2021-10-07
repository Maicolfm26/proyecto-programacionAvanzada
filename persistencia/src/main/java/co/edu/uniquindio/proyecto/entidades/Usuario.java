package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario extends Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private String codigo;

    @ManyToMany(mappedBy = "usuariosFavoritos")
    private List<Producto> favoritosUsuario;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> compras;

    @OneToMany(mappedBy = "vendedor")
    private List<Producto> listaProductos;

    @OneToMany(mappedBy = "usuarioComprador")
    private List<Chat> chats;

    @ManyToOne
    private Ciudad ciudad;

}
