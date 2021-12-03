package co.edu.uniquindio.proyecto.serviciosimpl;

import co.edu.uniquindio.proyecto.entidades.Favor;
import co.edu.uniquindio.proyecto.repositorios.FavorRepo;
import co.edu.uniquindio.proyecto.servicios.FavorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavorServicioImpl implements FavorServicio {

    @Autowired
    private FavorRepo favorRepo;

    @Override
    public Favor crearFavor(Favor favor) throws Exception {
        return favorRepo.save(favor);
    }
}
