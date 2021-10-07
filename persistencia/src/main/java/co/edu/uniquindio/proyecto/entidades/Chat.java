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
@ToString
public class Chat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @JoinColumn (nullable = false)
    @ManyToOne
    private Usuario usuarioComprador;

    @JoinColumn (nullable = false)
    @ManyToOne
    private Producto codigoProductoChat;

    @OneToMany(mappedBy = "codigoChat")
    private List<Mensaje> mensajes;

    public Chat(Integer codigo, Usuario usuarioComprador, Producto codigoProductoChat, List<Mensaje> mensajes) {
        this.codigo = codigo;
        this.usuarioComprador = usuarioComprador;
        this.codigoProductoChat = codigoProductoChat;
        this.mensajes = mensajes;
    }
}