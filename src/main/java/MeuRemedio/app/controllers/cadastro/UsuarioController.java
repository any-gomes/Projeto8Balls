package MeuRemedio.app.controllers.cadastro;

import MeuRemedio.app.controllers.EnvioEmailController;
import MeuRemedio.app.models.usuarios.Usuario;
import MeuRemedio.app.repository.UsuarioRepository;
import MeuRemedio.app.service.utils.ValidateAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {
    @Autowired
    EnvioEmailController emailCadastro;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    ValidateAuthentication validateAuthentication;

    @RequestMapping(value = "/cadastro")
    public String telaCadasUsuario() {

        if (validateAuthentication.auth() != true){
            return "CadastroUsuario";
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
