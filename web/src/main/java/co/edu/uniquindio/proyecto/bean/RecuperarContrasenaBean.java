package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.Email.EmailSenderService;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@ViewScoped
@Component
public class RecuperarContrasenaBean {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private EmailSenderService senderService;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private Usuario usuario;

    private String codigo;

    public void enviarCodigo(){
        try {
            usuario = usuarioServicio.buscarPorEmail(email);
            codigo = generateRandomString(8);
            senderService.sendEmail(email, "Recuperación de contraseña", "El código para recuperar la contraseña es: "+
                    codigo);
        }catch (Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
    }

    public static String generateRandomString(int length) {
        // Puede personalizar los personajes que desea agregar a
        // las cadenas al azar
        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        String NUMBER = "0123456789";

        String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
        SecureRandom random = new SecureRandom();

        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            // 0-62 (exclusive), retornos aleatorios 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);
        }

        return sb.toString();
    }

}
