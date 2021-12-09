package co.edu.uniquindio.proyecto.serviciosImpl;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadServicioImpl implements CiudadServicio {

    private final CiudadRepo ciudadRepo;

    public CiudadServicioImpl(CiudadRepo ciudadRepo) {
        this.ciudadRepo = ciudadRepo;
    }


    @Override
    public List<Ciudad> obtenerCiudades() {
        return ciudadRepo.findAll();
    }

    @Override
    public Ciudad obtenerCiudad(Integer codigo) throws Exception {
        Optional<Ciudad> buscado = ciudadRepo.findById(codigo);
        if (buscado.isEmpty()){
            throw new Exception("El usuario no existe");
        }
        return buscado.get();
    }

    @Override
    public List<Object[]> listarProductosCiudades() {
        return ciudadRepo.listarCiudadesProductos();
    }
}
