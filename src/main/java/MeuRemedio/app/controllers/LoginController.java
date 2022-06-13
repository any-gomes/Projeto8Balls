package MeuRemedio.app.controllers;
import MeuRemedio.app.service.utils.ValidateAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

@Autowired
ValidateAuthentication validateAuthentication;

    @RequestMapping(value = "/login")
    public String login() {
         if (validateAuthentication.auth() != true){
            return "Login";
        }

        return "redirect:/home";
    }
    @RequestMapping(value = "/logout")
    public String logout() {
        return "Login";
    }


}
