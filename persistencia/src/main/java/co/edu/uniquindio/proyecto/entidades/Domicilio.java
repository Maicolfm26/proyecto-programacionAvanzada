package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/*
Etiquetas para uso de métodos con el fin de acortar la cantidad de lineas de código
 */

@Entity
@Getter
@Setter
@NoArgsConstructor

/*
Clase para la entidad Domicilio la cual tendrá su tabla correspondiente en Mysql.
 */

public class Domicilio implements Serializable {

     /*
    Se declaran los atributos de la entidad con sus respectivas restricciones.
     */
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @EqualsAndHashCode.Include
     private Integer codigo;

     @Column(nullable = false, length = 100)
     private String barrio;

     @Column(nullable = false, length = 3)
     private String calle;

     @Column(nullable = false, length = 3)
     private String numero1;

     @Column(nullable = false, length = 3)
     private String numero2;

     @Column
     private String datos_opcionales;

     /*
    Se declaran las relaciones con otras entidades acompañadas de su respectiva multiplicidad.
     */

     @ManyToOne
     private Usuario usuario;

     @OneToMany(mappedBy = "domicilio")
     private List<Compra> comprasEntregadas;

     @OneToMany(mappedBy = "domicilioOrigen")
     private List<Favor> favoresRecogidos;

     @OneToMany(mappedBy = "domicilioDestino")
     private List<Favor> favoresEntregados;

     /*
    Constructores de la entidad.
     */

     public Domicilio(String barrio, String calle, String numero1, String numero2, Usuario usuario) {
          this.barrio = barrio;
          this.calle = calle;
          this.numero1 = numero1;
          this.numero2 = numero2;
          this.usuario = usuario;
     }

     public Domicilio(String barrio, String calle, String numero1, String numero2) {
          this.barrio = barrio;
          this.calle = calle;
          this.numero1 = numero1;
          this.numero2 = numero2;
     }

     public Domicilio(String barrio, String calle, String numero1, String numero2, String datos_opcionales, Usuario usuario) {
          this.barrio = barrio;
          this.calle = calle;
          this.numero1 = numero1;
          this.numero2 = numero2;
          this.datos_opcionales = datos_opcionales;
          this.usuario = usuario;
     }

     public Domicilio(String barrio, String calle, String numero1, String numero2, String datos_opcionales) {
          this.barrio = barrio;
          this.calle = calle;
          this.numero1 = numero1;
          this.numero2 = numero2;
          this.datos_opcionales = datos_opcionales;
     }
}
