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
        emailModel.setSendDateEmail(LocalDate.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);

        }catch (MailAuthenticationException e){
            emailModel.setStatusEmail(StatusEmail.ERRO ) ;
            throw new MailAuthenticationException(e);

        }finally {
            return emailRepository.save(emailModel);
        }
    }
}
