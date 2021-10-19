package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/*Etiquetas para reducir las lineas de codigo*/
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Admin extends Persona implements Serializable {
    /* Constructor de la entidad implementando el constructor de la super clase*/
    public Admin(String codigo, String nombre, String email, String password) {
        super(codigo, nombre, email, password);
    }
}
