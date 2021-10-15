package co.edu.uniquindio.proyecto.entidades;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Chat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @ManyToOne
    @JoinColumn (nullable = false)
    private Usuario comprador;

    @ManyToOne
    @JoinColumn (nullable = false)
    private Producto producto;

    @OneToMany(mappedBy = "chat")
    private List<Mensaje> mensajes;

    public Chat(Usuario comprador, Producto producto, List<Mensaje> mensajes) {
        this.comprador = comprador;
        this.producto = producto;
        this.mensajes = mensajes;
    }
}