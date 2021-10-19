package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Subasta_Usuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaUsuarioRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
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
para la entidad Subasta_Usuario
*/

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class Subasta_UsuarioTest {

    /*
    Se declaran las variables que ayudarán a gestionar las funcionalidades crud a través del uso del
    repositorio correspondiente a cada entidad.
     */

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private SubastaRepo subastaRepo;
    @Autowired
    private SubastaUsuarioRepo subastaUsuarioRepo;

    /*
    En este metodo se verifica el metodo de guardar para la entidad subasta_usuario, se extrae una subasta
    y usuario para crear una nueva subasta_usuario esta se guarda con el repositorio y se verifica que la
    subasta_usuario retornada no sea null esto quiere decir que se guardo.
     */

    @Test
    @Sql("classpath:data.sql")
    public void registrarTest() {
        Subasta subasta = subastaRepo.findById(1).orElse(null);
        Usuario usuario = usuarioRepo.findById("100765489").orElse(null);

        Subasta_Usuario subasta_usuario = new Subasta_Usuario(subasta, usuario, 10000.0, LocalDate.now());

        Subasta_Usuario subasta_usuarioGuardada = subastaUsuarioRepo.save(subasta_usuario);

        Assertions.assertNotNull(subasta_usuario);
    }

    /*
    Método mediante el que se desarrolla el test para la eliminación de una subasta_usuario, este método
    elimina una subasta_usuario con el repositorio y despues consultamos con el repositorio la subasta_usuario
    previamente elimanada si nos devuelve null es que se borro correctamente.
     */

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        subastaUsuarioRepo.deleteById(1);
        Subasta_Usuario subasta_usuarioConsultada = subastaUsuarioRepo.findById(1).orElse(null);
        Assertions.assertNull(subasta_usuarioConsultada);

    }

     /*
    Método mediante el que se desarrolla el test para la actualización de la información de una subasta_usuario,
    este método extrae una subasta_usuario con el repositorio le cambiamos la fecha de subasta_usuario y
    actualizamos la subasta_usuario con el repositorio, luego se vuelve a consultar la subasta_usuario y
    se mira  si tiene la fecha que le actualizamos.
     */

    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest()
    {
        Subasta_Usuario subasta_usuarioGuardada = subastaUsuarioRepo.findById(1).orElse(null);
        subasta_usuarioGuardada.setFecha_subasta(LocalDate.of(2021, 11, 20));

        subastaUsuarioRepo.save(subasta_usuarioGuardada);

        Subasta_Usuario subastaUsuarioBuscada = subastaUsuarioRepo.findById(1).orElse(null);
        Assertions.assertEquals(LocalDate.of(2021, 11, 20), subastaUsuarioBuscada.getFecha_subasta());
    }

      /*
    Método mediante el que se desarrolla el test para la obtención de una lista que contenga todos las
    subastas_usuarios registrados hasta el momento en la base de datos, este método extrae dichos datos del
    archivo data.sql y verificamos que el tamaño de la lista obtenida sea igual a los datos que insertamos en
    data.sql.
     */

    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Subasta_Usuario> subastas_usuario = subastaUsuarioRepo.findAll();
        Assertions.assertEquals(3, subastas_usuario.size());

        subastas_usuario.forEach(c -> System.out.println(c));
    }
}
