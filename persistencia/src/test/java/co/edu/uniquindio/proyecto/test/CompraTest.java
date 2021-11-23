package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.time.LocalDate;
import java.util.List;

/*
Esta clase permite la realización de pruebas sobre las funcionalidades CRUD establecidas
para la entidad Compra
*/

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

     /*
     Se declaran las variables que ayudarán a gestionar las funcionalidades crud a través del uso del
     respositorio correspondiente a cada entidad.
      */

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private DomicilioRepo domicilioRepo;

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    /*
    Método mediante el que se desarrolla el test de la realización de un registro para una compra,
    este método además de crear datos en su interior también extrae algunos de ellos del archivo
    data.sql.
     */

    @Test
    @Sql("classpath:data.sql")
    public void registrarTest() {

        Domicilio domicilio = domicilioRepo.findById(1).orElse(null);
        Usuario usuario = usuarioRepo.findById("42785998").orElse(null);
        List<DetalleCompra> detalleCompras = detalleCompraRepo.findAll();

        Compra compra = new Compra(LocalDate.now(),MedioPago.EFECTIVO,usuario, detalleCompras, domicilio, 5500.0);

        Compra compraGuardada= compraRepo.save(compra);
        Assertions.assertNotNull(compraGuardada);
    }

    /*
    Método mediante el que se desarrolla el test para la eliminación de una compra, este método extrae
    algunos datos del archivo data.sql y realiza consultas a la base de datos.
     */

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        compraRepo.deleteById(2);
        Compra compraConsultada = compraRepo.findById(2).orElse(null);
        Assertions.assertNull(compraConsultada);

    }

    /*
    Método mediante el que se desarrolla el test para la actualización de la información de una compra,
    este método extrae algunos datos del archivo data.sql y realiza consultas a la base de datos.
     */

    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest()
    {
        Compra compraGuardada = compraRepo.findById(1).orElse(null);
        compraGuardada.setMedioPago(MedioPago.DAVIPLATA);

        compraRepo.save(compraGuardada);

        Compra compraBuscada = compraRepo.findById(1).orElse(null);
        Assertions.assertEquals(MedioPago.NEQUI ,compraBuscada.getMedioPago());
    }

    /*
    Método mediante el que se desarrolla el test para la obtención de una lista que contenga todos las
    compras registradas hasta el momento en la base de datos, este método extrae dichos datos del
    archivo data.sql.
     */

    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Compra> listaCompras =  compraRepo.findAll();
        Assertions.assertEquals(3, listaCompras.size());

        listaCompras.forEach(c -> System.out.println(c));
    }
}
