package MeuRemedio.app.controllers;

import MeuRemedio.app.models.usuarios.Usuario;
import MeuRemedio.app.repository.UsuarioRepository;
import MeuRemedio.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class CadastroUsuarioController {
    @Autowired
    EmailController emailCadastro;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    EmailService emailService;


    @RequestMapping(value = "/cadastro")
    public String telaCadasUsuario() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "Cadastro";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String CadastrarUsuario(@RequestParam("US_Nome") String nome, @RequestParam("US_Sobrenome") String sobrenome,
                                   @RequestParam("US_Email") String email, @RequestParam("US_Senha") String senha,
                                   @RequestParam("US_DataNascimento") String dataNascimento, @RequestParam("US_Sexo") String sexo) {

        if (usuarioRepository.findByEmail(email) != null) {
            return "redirect:/cadastro?emailExistente";
        }

        Usuario usuarioCadastro = new Usuario(nome, sobrenome, email,
                new BCryptPasswordEncoder().encode(senha), dataNascimento, sexo);

        usuarioRepository.save(usuarioCadastro);
        emailCadastro.emailConfirmCadastro(usuarioCadastro);

        return "redirect:/";

    }
}
