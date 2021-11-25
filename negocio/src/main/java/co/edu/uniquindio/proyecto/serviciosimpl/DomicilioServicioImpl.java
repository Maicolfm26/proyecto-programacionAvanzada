package co.edu.uniquindio.proyecto.serviciosimpl;

import co.edu.uniquindio.proyecto.entidades.Domicilio;
import co.edu.uniquindio.proyecto.repositorios.DomicilioRepo;
import co.edu.uniquindio.proyecto.servicios.DomicilioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomicilioServicioImpl implements DomicilioServicio {

    @Autowired
    private DomicilioRepo domicilioRepo;
    @Override
    public Domicilio obtenerDomicilio(Integer codigo) throws Exception {
        return domicilioRepo.findById(codigo).orElseThrow(() -> new Exception("La compra no fue encontrada"));
    }
}
