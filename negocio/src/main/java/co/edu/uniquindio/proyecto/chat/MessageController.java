package co.edu.uniquindio.proyecto.chat;

import co.edu.uniquindio.proyecto.dto.MensajeChat;
import co.edu.uniquindio.proyecto.dto.MensajeRespuesta;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public MensajeRespuesta getMessage(final MensajeChat message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new MensajeRespuesta(HtmlUtils.htmlEscape(message.getContenido()));
    }
}
