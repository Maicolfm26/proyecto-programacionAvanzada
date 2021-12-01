package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.entidades.Domicilio;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomicilioRepo extends JpaRepository<Domicilio, Integer> {

    @Query("select d from Domicilio d where d.usuario.codigo = :codigo")
    List<Domicilio> obtenerDomiciliosUsuario(String codigo);

}

