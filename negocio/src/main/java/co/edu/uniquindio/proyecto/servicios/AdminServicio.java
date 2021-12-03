package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Admin;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface AdminServicio {

    Admin iniciarSesion(String email, String password) throws Exception;

    Admin buscarPorEmail(String email) throws Exception;

    Admin actualizarAdmin(String codigo, String email, Admin adminActualizado) throws Exception;
}
