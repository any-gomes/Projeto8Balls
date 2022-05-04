package MeuRemedio.app.service;

import MeuRemedio.app.enums.StatusEmail;
import MeuRemedio.app.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;
    public StatusEmail sendEmail(Usuario usuario, String assunto, String mensagem) {
        String emailRemetente = "8balls.integratedproject@gmail.com";

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailRemetente);
            message.setTo(usuario.getEmail());
            message.setSubject(assunto);
            message.setText(mensagem);
            emailSender.send(message);

            return StatusEmail.SENT;

        }catch (MailAuthenticationException e){
            throw new MailAuthenticationException(e);
        }
    }
}
