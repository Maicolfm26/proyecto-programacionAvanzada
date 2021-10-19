package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Admin;
import co.edu.uniquindio.proyecto.repositorios.AdminRepo;
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
public class AdminTest {

    /*Variables que hacen referencia a los repositorios que son de ayuda para buscar por id,
    eliminar por id entre otras que le agreguemos*/
    @Autowired
    private AdminRepo adminRepo;


    /*Se crea el test de registrar un admin,le enviamos al constructor los atributos
    guardamos el admin y verificamos que no este en null*/
    @Test
    public void registrarTest(){

        Admin admin = new Admin("123","Juan","juan@email.com","juan123");

        Admin adminGuardado = adminRepo.save(admin);
        System.out.println(adminGuardado);
        Assertions.assertNotNull(adminGuardado);

    }

    /*Creamos el test para elminar,en este caso eliminamos con la ayuda del repositorio el que tenga
     * id igual a 123, luego la buscamos y nos debera aparecer en null ya que estaria eliminada*/
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        adminRepo.deleteById("123");
        Admin adminConsultado = adminRepo.findById("123").orElse(null);
        Assertions.assertNull(adminConsultado);

    }

    /*Se crea el test de actualizar,en este caso buscamos en el archivo sql, luego le seteamos un nuevo dato en
    alguno de sus atributos, seguidamente lo guardamos y luego volvemos a buscar el mismo registro
    * y con la ayuda del asser deberemos indicar que esperamos el dato que hemos actualizado en el registro modificado*/
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest()
    {
        Admin adminGuardado = adminRepo.findById("123").orElse(null);
        adminGuardado.setEmail("jcjdlt@email.com");

        adminRepo.save(adminGuardado);

        Admin adminBuscado = adminRepo.findById("123").orElse(null);
        Assertions.assertEquals("jcjdlt@email.com",adminBuscado.getEmail());
    }


    /*Se crea el test de listar los registros, en este casi creamos una lista de la entidad y le decimos
     * al repositorio que nos devuelta todos los registros de esa entidad que se encuentren en el archivo.sql
     * luego con la ayuda de un ciclo las imprimimos*/
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Admin> listaAdmins =  adminRepo.findAll();
        Assertions.assertEquals(3, listaAdmins.size());

        listaAdmins.forEach(u -> System.out.println(u));
    }
}
