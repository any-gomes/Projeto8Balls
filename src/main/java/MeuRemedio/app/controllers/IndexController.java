package MeuRemedio.app.controllers;


import MeuRemedio.app.models.usuarios.Usuario;
import MeuRemedio.app.repository.UsuarioRepository;
import MeuRemedio.app.utils.AuthenticationFacade;
import MeuRemedio.app.utils.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @Autowired
    AuthenticationFacade authenticationFacade;

    @RequestMapping(value = "/home")
        public String home(){
            return "Home";
        }


    @RequestMapping(value = "/")
    public String Index(){
        if (authenticationFacade.getAuthentication() == null
                || authenticationFacade.getAuthentication() instanceof AnonymousAuthenticationToken){

            return "Index";
        }

        return "redirect:/home";
    }
}
