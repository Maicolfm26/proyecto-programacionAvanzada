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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdminTest {

    @Autowired
    private AdminRepo adminRepo;

    @Test
    public void registrarTest(){

        Admin admin = new Admin("123","Juan","juan@email.com","juan123");

        Admin adminGuardado = adminRepo.save(admin);
        System.out.println(adminGuardado);
        Assertions.assertNotNull(adminGuardado);

    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest()
    {
        adminRepo.deleteById("123");
        Admin adminConsultado = adminRepo.findById("123").orElse(null);
        Assertions.assertNull(adminConsultado);

    }

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

    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Admin> listaAdmins =  adminRepo.findAll();
        Assertions.assertEquals(3, listaAdmins.size());

        listaAdmins.forEach(u -> System.out.println(u));
    }
}
