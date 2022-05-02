package MeuRemedio.app.controllers;

import MeuRemedio.app.model.EmailModel;
import MeuRemedio.app.model.Usuario;
import MeuRemedio.app.repository.UsuarioRepository;
import MeuRemedio.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class CadastroUsuarioController {
    EmailController emailCadastro = new EmailController();

    @Autowired
    private UsuarioRepository UsuarioRepository;

    @Autowired
    EmailService emailService;


  @RequestMapping(value = "/cadastro")
    public String telaCadasUsuario(){
        return "Cadastro";
    }


   @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String CadastrarUsuario(@RequestParam("US_Nome") String US_Nome, @RequestParam("US_Sobrenome") String US_Sobrenome,
                                   @RequestParam("US_Email") String US_Email, @RequestParam("US_Senha") String US_Senha,
                                   @RequestParam("US_DataNascimento") String US_DataNascimento, @RequestParam("US_Sexo") String US_Sexo) {

        Usuario usuarioCadastro  = new Usuario (US_Nome,US_Sobrenome,US_Email,
        new BCryptPasswordEncoder().encode(US_Senha), US_DataNascimento, US_Sexo);

        UsuarioRepository.save(usuarioCadastro);
        emailCadastro.emailCadastro(usuarioCadastro);

        return "redirect:/";
    }



}
