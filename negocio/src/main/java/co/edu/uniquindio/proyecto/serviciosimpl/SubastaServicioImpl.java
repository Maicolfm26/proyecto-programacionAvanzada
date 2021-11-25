package co.edu.uniquindio.proyecto.serviciosimpl;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.servicios.SubastaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubastaServicioImpl implements SubastaServicio {

    private final SubastaRepo subastaRepo;

    public SubastaServicioImpl(SubastaRepo subastaRepo) {
        this.subastaRepo = subastaRepo;
    }

    @Override
    public Subasta crearSubasta(Subasta subasta) throws Exception {
        return subastaRepo.save(subasta);
    }
}
