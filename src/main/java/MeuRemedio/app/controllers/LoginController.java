package MeuRemedio.app.controllers;
import MeuRemedio.app.utils.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    AuthenticationFacade authenticationFacade;

    @RequestMapping(value = "/login")
    public String login() {
         ;

        if (authenticationFacade.getAuthentication() == null
                || authenticationFacade.getAuthentication() instanceof AnonymousAuthenticationToken){

            return "Login";
        }

        return "redirect:/home";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "Login";
    }


}
