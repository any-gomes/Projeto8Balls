package MeuRemedio.app.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping(value = "/Login")
    public String login() {
        return "Login";
    }
    @RequestMapping(value = "/")
    public String loginA() {
        return "Login";
    }

    @RequestMapping(value = "/Logout")
    public String logout() {
        return "Login";
    }

}
