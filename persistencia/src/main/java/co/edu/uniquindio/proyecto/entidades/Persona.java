
package co.edu.uniquindio.proyecto.entidades;

import com.sun.security.auth.UserPrincipal;
import lombok.*;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.security.Principal;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS )
@MappedSuperclass
@ToString
/*
    Clase persona que se define como una entidad que tendra una tabla en la base de datos de mysql.
 */
public class Persona implements Serializable {

    /*
        Se declaran los atributos de la entidad.
     */


    @Id
    @Column(length = 10)
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String nombre;

    @Column(nullable = false, length = 50, unique = true)
    @NotBlank
    private String email;

    @Column(nullable = false)
    @NotBlank
    private String password;
}

