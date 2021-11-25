package co.edu.uniquindio.proyecto.serviciosimpl;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepo;

    private final UsuarioRepo usuarioRepo;

    public CompraServicioImpl(CompraRepo compraRepo, UsuarioRepo usuarioRepo) {
        this.compraRepo = compraRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Compra hacerCompra(Compra compra) throws Exception {
            return compraRepo.save(compra);
    }

    @Override
    public List<Compra> obtenerComprasUsuario(String codigoUsuario) throws Exception{
        if (usuarioRepo.findById(codigoUsuario).isEmpty()) {
            throw new Exception("El codigo del producto no existe");
        }
        return compraRepo.listarComprasUsuario(codigoUsuario);
    }
}
