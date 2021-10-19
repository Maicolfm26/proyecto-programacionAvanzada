package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
/*
Esta clase permite la realización de pruebas sobre las funcionalidades CRUD establecidas
para la entidad Usuario
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetalleCompraTest {

    /*Variables que hacen referencia a los repositorios que son de ayuda para buscar por id,
    eliminar por id entre otras que le agreguemos*/
    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private ProductoRepo productoRepo;


    /*Se crea el test de registrar un detalle de compra en este caso buscamos en el archivo .sql
     * mediante el repositorio las entidades, las enviamos al constructor lo guardamos
     * y verificamos que no este en null*/
    @Test
    @Sql("classpath:data.sql")
    public void registrarTest() {

        Compra compra = compraRepo.findById(1).orElse(null);
        Producto producto = productoRepo.findById(1).orElse(null);

        DetalleCompra detalleCompra = new DetalleCompra(compra, producto, 10, 20000.00);

        DetalleCompra detalleCompraGuardado = detalleCompraRepo.save(detalleCompra);
        System.out.println(detalleCompraGuardado);
        Assertions.assertNotNull(detalleCompraGuardado);

    }

    /*Creamos el test para elminar,en este caso eliminamos con la ayuda del repositorio el que tenga
    * id igual a 1, luego la buscamos y nos debera aparecer en null ya que estaria eliminada*/
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        detalleCompraRepo.deleteById(1);
        DetalleCompra detalleCompraConsultada = detalleCompraRepo.findById(1).orElse(null);
        Assertions.assertNull(detalleCompraConsultada);

    }


    /*Se crea el test de actualizar,en este caso buscamos en el archivo sql, luego le seteamos un nuevo dato en
    alguno de sus atributos, seguidamente lo guardamos y luego volvemos a buscar el mismo registro
    * y con la ayuda del asser deberemos indicar que esperamos el dato que hemos actualizado en el registro modificado*/
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest()
    {
        DetalleCompra detalleCompraGuardado = detalleCompraRepo.findById(1).orElse(null);
        detalleCompraGuardado.setPrecio_producto(50000.00);

        detalleCompraRepo.save(detalleCompraGuardado);

        DetalleCompra detalleCompraBuscada = detalleCompraRepo.findById(1).orElse(null);
        Assertions.assertEquals(50000.00,detalleCompraBuscada.getPrecio_producto());
    }


    /*Se crea el test de listar los registros, en este casi creamos una lista de la entidad y le decimos
    * al repositorio que nos devuelta todos los registros de esa entidad que se encuentren en el archivo.sql
    * luego con la ayuda de un ciclo las imprimimos*/
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<DetalleCompra> listaDetalles =  detalleCompraRepo.findAll();
        listaDetalles.forEach(u -> System.out.println(u));
    }
}
