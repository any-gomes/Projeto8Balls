package MeuRemedio.app.controllers;

import MeuRemedio.app.models.agendamentos.Agendamento;
import MeuRemedio.app.models.remedios.Remedio;
import MeuRemedio.app.repository.AgendamentoRepository;
import MeuRemedio.app.service.utils.ValidateAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class AgendamentoController {
    @Autowired
    ValidateAuthentication validateAuthentication;

    @Autowired
    AgendamentoRepository agendamentoRepository;


    /* Por hora, esse metodo retorna todos os agendamento
    * Estamos encontrando uma maneira de retornar um agendamento
    * que foi realizado pelo usuario em questão
    */
    @RequestMapping(value = "/agendamentos")
    public String TelaAgendarRemedio(ModelMap model) {
        if (!validateAuthentication.auth()) {
            return "Login";
        }
        Iterable <Agendamento> agendamentos = agendamentoRepository.findAll();
        model.addAttribute("agendamento", agendamentos);

        return "Agendamento";
    }

    @RequestMapping(value = "/agendamentos", method = RequestMethod.POST)
    public String cadastrarAgendamento(@RequestParam("AG_Data_Inicio_Agendamento") String AG_DataInicio,
                                       @RequestParam("AG_Hora_Inicio_Agendamento") String AG_horaInicio,
                                       @RequestParam("AG_Data_Final_Agendamento")  String AG_DataFinal ,
                                       @RequestParam("AG_Periodicidade") long AG_Periodicidade){

        Agendamento agendamento = new Agendamento(AG_DataInicio,AG_horaInicio,AG_DataFinal,AG_Periodicidade);
        agendamentoRepository.save(agendamento);

        return "redirect:/agendamentos";
    }

    @RequestMapping(value="/deletar_agendamento")
    public String deletarAgendamento(long id){
        Agendamento agendamento = agendamentoRepository.findById(id);
        agendamentoRepository.delete(agendamento);

        return "redirect:/agendamentos";
    }
}
