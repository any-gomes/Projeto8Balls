package MeuRemedio.app.controllers;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErroController implements ErrorController {

    @RequestMapping(value = "/error")
    public String handleError(){
        return "Erro";
    }

//    @Override
//    public String getErrorPath(){
//        return "/error";
//    }
}
