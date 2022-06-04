package MeuRemedio.app.controllers.cadastro;

import MeuRemedio.app.controllers.EnvioEmailController;
import MeuRemedio.app.models.usuarios.Usuario;
import MeuRemedio.app.repository.UsuarioRepository;
import MeuRemedio.app.service.utils.UserSession;
import MeuRemedio.app.service.utils.ValidateAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UsuarioController {
    @Autowired
    EnvioEmailController emailCadastro;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    ValidateAuthentication validateAuthentication;

    @Autowired
    UserSession userSession;

    @RequestMapping(value = "/cadastro")
    public String telaCadasUsuario() {

        if (!validateAuthentication.auth()){
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
    @RequestMapping(value = "/atualizar-usuario", method = RequestMethod.GET)
    public String viewAtualizarUsuario(HttpServletRequest request, Model model){
        String EmailUsuarioLogado = userSession.returnUsernameUsuario();
        Usuario usuarioLogado = usuarioRepository.findByEmail(EmailUsuarioLogado);
        model.addAttribute("usuario",usuarioLogado);
        return "AtualizarUsuario";
    }
    @RequestMapping(value = "/atualizar-usuario", method = RequestMethod.POST)
    public String atualizarUsuario(@RequestParam("US_Nome") String nome, @RequestParam("US_Sobrenome") String sobrenome,
                                   @RequestParam("US_Email") String email, @RequestParam("US_Senha") String senha,
                                   @RequestParam("US_DataNascimento") String dataNascimento, @RequestParam("US_Sexo") String sexo){

        String EmailUsuarioLogado = userSession.returnUsernameUsuario();
        Usuario usuarioLogado = usuarioRepository.findByEmail(EmailUsuarioLogado);

        usuarioLogado.setNome(nome);
        usuarioLogado.setSobrenome(sobrenome);
        usuarioLogado.setEmail(email);
        usuarioLogado.setSenha(senha);
        usuarioLogado.setDataNascimento(dataNascimento);
        usuarioLogado.setSexo(sexo);
        usuarioLogado.setSenha(new BCryptPasswordEncoder().encode(senha));

        usuarioRepository.save(usuarioLogado);

        return "redirect:/atualizar-usuario";
    }

}
