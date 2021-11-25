package co.edu.uniquindio.proyecto.serviciosimpl;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.servicios.DetalleCompraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleCompraImpl implements DetalleCompraServicio {

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;
    @Override
    public DetalleCompra obtenerDetalleCompra(Integer codigo) throws Exception {
        return detalleCompraRepo.findById(codigo).orElseThrow(() -> new Exception("El detalle compra no fue encontrado"));
    }
}
