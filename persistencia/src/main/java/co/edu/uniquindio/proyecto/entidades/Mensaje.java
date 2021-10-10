package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Mensaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length = 200, nullable = false)
    private String mensaje;

    @Column(length = 200, nullable = false)
    private String emisor;

    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Chat chat;

    public Mensaje(String mensaje, String emisor, LocalDate fecha, Chat chat) {
        this.mensaje = mensaje;
        this.emisor = emisor;
        this.fecha = fecha;
        this.chat = chat;
    }
}