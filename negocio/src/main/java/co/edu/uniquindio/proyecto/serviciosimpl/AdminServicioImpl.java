package co.edu.uniquindio.proyecto.serviciosimpl;

import co.edu.uniquindio.proyecto.entidades.Admin;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.AdminRepo;
import co.edu.uniquindio.proyecto.servicios.AdminServicio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServicioImpl implements AdminServicio {

    private final AdminRepo adminRepo;

    public AdminServicioImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public Admin iniciarSesion(String email, String password) throws Exception {
        return adminRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new Exception("Los datos de autenticacion son incorrectos"));
    }

    @Override
    public Admin buscarPorEmail(String email) throws Exception {
        return adminRepo.findByEmail(email).orElseThrow(() -> new Exception("El admin con el email especificado no existe."));
    }

    @Override
    public Admin actualizarAdmin(String codigo, String email, Admin adminActualizado) throws Exception {
        Optional<Admin> buscado;
        if (!codigo.equals(adminActualizado.getCodigo())) {
            buscado = adminRepo.findById(adminActualizado.getCodigo());
            if (buscado.isPresent()) {
                throw new Exception("El código del usuario ya existe");
            }
        }

        if(!email.equals(adminActualizado.getEmail())) {
            buscado = adminRepo.findByEmail(adminActualizado.getEmail());
            if (buscado.isPresent()) {
                throw new Exception("El email del usuario ya existe");
            }
        }

        return adminRepo.save(adminActualizado);
    }
}
