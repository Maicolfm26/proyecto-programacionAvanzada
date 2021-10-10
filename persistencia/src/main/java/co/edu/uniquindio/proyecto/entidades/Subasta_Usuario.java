package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Subasta_Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Subasta subasta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @Positive
    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private LocalDate fecha_subasta;

    public Subasta_Usuario(Subasta subasta, Usuario usuario, Double valor, LocalDate fecha_subasta) {
        this.subasta = subasta;
        this.usuario = usuario;
        this.valor = valor;
        this.fecha_subasta = fecha_subasta;
    }
}
