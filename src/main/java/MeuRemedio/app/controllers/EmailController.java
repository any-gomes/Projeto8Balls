package MeuRemedio.app.controllers;


import MeuRemedio.app.dto.EmailDto;
import MeuRemedio.app.model.EmailModel;
import MeuRemedio.app.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class EmailController {
@Autowired
    EmailService emailService;

/*Setar as email model com os atributos que virão do front para envio de email, @RequistParam
* para pegar os dados*/
@RequestMapping(value = "/sending-email", method = RequestMethod.POST)
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody EmailModel emailModel){
   // EmailModel emailModel = new EmailModel();
   // BeanUtils.copyProperties(emaildto, emailModel);
    emailService.sendEmail(emailModel);

    return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
}

    @RequestMapping(value = "/recuperar_senha", method = RequestMethod.POST)
    public void RecuperarSenha(String email){
        String link = "https://localhost:8080/Cadastro";
        EmailModel emailModel = new EmailModel();
        emailModel.setEM_Destinatario(email);
        emailModel.setEM_Assunto("Recuperação de senha");
        emailModel.setText( "Olá, vimos que perdeu sua senha, acesse o link para criar uma nova"+ link);

        emailService.sendEmail(emailModel);
    }
}
