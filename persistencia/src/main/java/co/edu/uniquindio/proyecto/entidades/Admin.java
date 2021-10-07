package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Admin extends Persona implements Serializable {

    @Id
    @Column(nullable = false, length = 10)
    @EqualsAndHashCode.Include
    private String codigo;



}
