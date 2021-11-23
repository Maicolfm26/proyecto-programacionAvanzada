package co.edu.uniquindio.proyecto.entidades;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*Etiquetas para reducir las lineas de codigo*/
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Chat implements Serializable {

    /*Atributos de la clase incluyendo su llave primaria*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    /*Relaciones entre entidades en este caso Usuario, Producto y Mensaje*/
    @ManyToOne
    @JoinColumn (nullable = false)
    private Usuario comprador;

    @ManyToOne
    @JoinColumn (nullable = false)
    private Producto producto;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Mensaje> mensajes;

    /*Constructor de la clase*/
    public Chat(Usuario comprador, Producto producto, List<Mensaje> mensajes) {
        this.comprador = comprador;
        this.producto = producto;
        this.mensajes = mensajes;
    }
}