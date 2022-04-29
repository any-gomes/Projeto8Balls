package MeuRemedio.app.controllers;

import MeuRemedio.app.model.EmailModel;
import MeuRemedio.app.model.Usuario;
import MeuRemedio.app.repository.UsuarioRepository;
import MeuRemedio.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class CadastroUsuarioController {

    @Autowired
    private UsuarioRepository UsuarioRepository;

    @Autowired
    EmailService emailService;


  @RequestMapping(value = "/Cadastro")
    public String telaCadasUsuario(){
        return "/Cadastro";
    }


   @RequestMapping(value = "/Cadastro", method = RequestMethod.POST)
    public String CadastrarUsuario(@RequestParam("US_Nome") String US_Nome, @RequestParam("US_Sobrenome") String US_Sobrenome,
                                   @RequestParam("US_Email") String US_Email, @RequestParam("US_Senha") String US_Senha,
                                   @RequestParam("US_DataNascimento") String US_DataNascimento, @RequestParam("US_Sexo") String US_Sexo) {

        Usuario usuarioCadastro  = new Usuario (US_Nome,US_Sobrenome,US_Email,
        new BCryptPasswordEncoder().encode(US_Senha), US_DataNascimento, US_Sexo);

        UsuarioRepository.save(usuarioCadastro);
        emailCadastro(usuarioCadastro);

        return "redirect:/";
    }

    public void emailCadastro(Usuario usuario){
        String link = "www.youtube.com.br";
        String nomeCompleto = usuario.getUS_Nome() + " " + usuario.getUS_Sobrenome();

        EmailModel emailModel = new EmailModel();
        emailModel.setEM_Destinatario(usuario.getUS_Email());
        emailModel.setEM_Assunto("Cadastro Realizado");
        emailModel.setText("Olá " + nomeCompleto + ". Queremos agradecer por ter se registrado na plataforma Meu remedio. Acesse a platafor em  " + link);

        emailService.sendEmail(emailModel);
    }

}
