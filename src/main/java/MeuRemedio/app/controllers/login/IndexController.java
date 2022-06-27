package MeuRemedio.app.controllers.login;


import MeuRemedio.app.service.utils.Authentication;
import MeuRemedio.app.service.utils.ValidateAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @Autowired
    ValidateAuthentication validateAuthentication;
    @RequestMapping(value = "/home")
        public String home(){
            return "Home";
        }

    @RequestMapping(value = "/")
    public String Index(){
        if (validateAuthentication.auth() != true){
            return "Index";
        }
        return "redirect:/home";
    }
}
