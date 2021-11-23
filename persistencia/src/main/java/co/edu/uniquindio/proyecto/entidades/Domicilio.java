package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/*
Etiquetas para uso de métodos con el fin de acortar la cantidad de lineas de código
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
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
     @NotBlank
     private String barrio;

     @Column(nullable = false, length = 3)
     @NotBlank
     private String calle;

     @Column(nullable = false, length = 3)
     @NotBlank
     private String numero1;

     @Column(nullable = false, length = 3)
     @NotBlank
     private String numero2;

     @Column
     private String datos_opcionales;

     /*
    Se declaran las relaciones con otras entidades acompañadas de su respectiva multiplicidad.
     */

     @ManyToOne
     private Ciudad ciudad;

     @ManyToOne
     private Usuario usuario;

     @OneToMany(mappedBy = "domicilio", cascade = CascadeType.ALL)
     @ToString.Exclude
     private List<Compra> comprasEntregadas;

     @OneToMany(mappedBy = "domicilioOrigen", cascade = CascadeType.ALL)
     @ToString.Exclude
     private List<Favor> favoresRecogidos;

     @OneToMany(mappedBy = "domicilioDestino", cascade = CascadeType.ALL)
     @ToString.Exclude
     private List<Favor> favoresEntregados;

     /*
    Constructores de la entidad.
     */

     public Domicilio(Ciudad ciudad, String barrio, String calle, String numero1, String numero2, Usuario usuario) {
          this.ciudad = ciudad;
          this.barrio = barrio;
          this.calle = calle;
          this.numero1 = numero1;
          this.numero2 = numero2;
          this.usuario = usuario;
     }

     public Domicilio(Ciudad ciudad, String barrio, String calle, String numero1, String numero2) {
          this.ciudad = ciudad;
          this.barrio = barrio;
          this.calle = calle;
          this.numero1 = numero1;
          this.numero2 = numero2;
     }

     public Domicilio(Ciudad ciudad, String barrio, String calle, String numero1, String numero2, String datos_opcionales, Usuario usuario) {
          this.ciudad = ciudad;
          this.barrio = barrio;
          this.calle = calle;
          this.numero1 = numero1;
          this.numero2 = numero2;
          this.datos_opcionales = datos_opcionales;
          this.usuario = usuario;
     }

     public Domicilio(Ciudad ciudad, String barrio, String calle, String numero1, String numero2, String datos_opcionales) {
          this.ciudad = ciudad;
          this.barrio = barrio;
          this.calle = calle;
          this.numero1 = numero1;
          this.numero2 = numero2;
          this.datos_opcionales = datos_opcionales;
     }
}
