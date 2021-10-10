package co.edu.uniquindio.proyecto.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Telefono implements Serializable {

    @Id
    @Column(length = 10)
    private String num_telefono;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;
}
