package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class CiudadServicioTest {

    @Autowired
    private CiudadServicio ciudadServicio;

    @Test
    @Sql("classpath:data.sql")
    public void listarProductosCiudad(){
        List<Object[]> lista = ciudadServicio.listarProductosCiudades();
        Assertions.assertEquals(5,lista.size());
    }
}
