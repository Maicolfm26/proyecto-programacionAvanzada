package co.edu.uniquindio.proyecto.serviciosImpl;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Departamento;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.DepartamentoRepo;
import co.edu.uniquindio.proyecto.servicios.DepartamentoServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoServicioImpl implements DepartamentoServicio {

    private final DepartamentoRepo departamentoRepo;
    private final CiudadRepo ciudadRepo;

    public DepartamentoServicioImpl(DepartamentoRepo departamentoRepo, CiudadRepo ciudadRepo) {
        this.departamentoRepo = departamentoRepo;
        this.ciudadRepo = ciudadRepo;
    }

    @Override
    public List<Departamento> obtenerDepartamentos() {
        return departamentoRepo.findAll();
    }

    @Override
    public List<Ciudad> obtenerCiudadesPorDepartamento(Departamento departamento) {
        return ciudadRepo.findByDepartamento(departamento);
    }

    @Override
    public Departamento obtenerDepartamento(Integer codigo) throws Exception {
        return  departamentoRepo.findById(codigo).orElseThrow(() -> new Exception("No se encontro el departamento"));
    }
}
