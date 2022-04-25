package MeuRemedio.app.service;

import MeuRemedio.app.enums.StatusEmail;
import MeuRemedio.app.model.EmailModel;
import MeuRemedio.app.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmailService {
    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setEM_DataEnvioEmail(LocalDate.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEM_Remetente());
            message.setTo(emailModel.getEM_Destinatario());
            message.setSubject(emailModel.getEM_Assunto());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setEM_statusEmail(StatusEmail.SENT);

        }catch (MailAuthenticationException e){
            emailModel.setEM_statusEmail(StatusEmail.ERROR); ;
            throw new MailAuthenticationException(e);

        }finally {
            return emailRepository.save(emailModel);
        }
    }
}
