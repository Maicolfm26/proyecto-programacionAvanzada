package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    @Sql("classpath:data.sql")
    public void registrarTest() {

        Ciudad ciudad = ciudadRepo.findById("123").orElse(null);

        List<String> telefonosUsuario = new ArrayList<String>();
        telefonosUsuario.add("3128280008");
        telefonosUsuario.add("3223631932");


        Usuario usuario = new Usuario("1010","Laura", "laura@email.com","123", telefonosUsuario, ciudad);
        usuarioRepo.save(usuario);

        Compra compra = new Compra(LocalDate.now(),"Efectivo",usuario);

        Compra compraGuardada= compraRepo.save(compra);
        Assertions.assertNotNull(compraGuardada);
    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        compraRepo.deleteById(2);
        Compra compraConsultada = compraRepo.findById(2).orElse(null);
        Assertions.assertNull(compraConsultada);

    }

    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest()
    {
        Compra compraGuardada = compraRepo.findById(1).orElse(null);
        compraGuardada.setMedioPago("Transferencia");

        compraRepo.save(compraGuardada);

        Compra compraBuscada = compraRepo.findById(1).orElse(null);
        Assertions.assertEquals("Transferencia",compraBuscada.getMedioPago());
    }


    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Compra> listaCompras =  compraRepo.findAll();
        listaCompras.forEach(c -> System.out.println(c));
    }
}
