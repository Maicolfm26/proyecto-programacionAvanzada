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
@ToString
public class Mensaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length = 200,nullable = false)
    private String mensaje;

    @Column(length = 200,nullable = false)
    private String emisor;

    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne
    private Chat codigoChat;

    public Mensaje(Integer codigo, String mensaje, String emisor, LocalDate fecha, Chat codigoChat) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.emisor = emisor;
        this.fecha = fecha;
        this.codigoChat = codigoChat;
    }
}