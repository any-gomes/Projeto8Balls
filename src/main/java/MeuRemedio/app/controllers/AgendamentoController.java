package MeuRemedio.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AgendamentoController {

    @RequestMapping(value = "/agendamentos")
    public String AgendarRemedio(){

        return "Agendamento";
    }
}
