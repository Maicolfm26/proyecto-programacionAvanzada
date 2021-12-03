package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/*
Etiquetas para uso de métodos con el fin de acortar la cantidad de lineas de código
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
/*
Clase para la entidad compra la cual tendrá su tabla correspondiente en Mysql.
 */

public class Compra implements Serializable {

    /*
    Se declaran los atributos de la entidad con sus respectivas restricciones.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private LocalDate fechaCompra;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MedioPago medioPago;

    /*
    Se declaran las relaciones con otras entidades acompañadas de su respectiva multiplicidad.
     */

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "compra", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @Size(min=1)
    private List<DetalleCompra> detalleCompras;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Domicilio domicilio;

    @Getter @Setter
    private Double total;


    /*
    Constructor de la entidad.
     */

    public Compra(LocalDate fechaCompra, MedioPago medioPago, Usuario usuario, List<DetalleCompra> detalleCompras, Domicilio domicilio) {
        this.fechaCompra = fechaCompra;
        this.medioPago = medioPago;
        this.usuario = usuario;
        this.detalleCompras = detalleCompras;
        this.domicilio = domicilio;
    }

    /*public Double getTotal(){
        Double total = 0.0;
        for(DetalleCompra dc : detalleCompras){
            total += dc.getPrecio_producto() * dc.getUnidades();
        }
        return total;
    }*/

    public String getMensaje(){
        String mensaje = "";
        for(DetalleCompra dc : detalleCompras){
         mensaje += "Producto: " + dc.getProducto().getNombre() +"       Unidades: "+
                 dc.getUnidades()+ "    Precio: "+dc.getProducto().getPrecio()*dc.getUnidades() +"\n";
        }
        return mensaje;
    }
}
