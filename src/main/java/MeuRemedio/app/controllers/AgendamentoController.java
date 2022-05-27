package MeuRemedio.app.controllers;

import MeuRemedio.app.models.agendamentos.Agendamento;
import MeuRemedio.app.repository.AgendamentoRepository;
import MeuRemedio.app.service.utils.ValidateAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/agendamentos")
    public String TelaAgendarRemedio() {
        if (validateAuthentication.auth() != true) {
            return "Login";
        }
        return "Agendamento";
    }

    @RequestMapping(value = "/agendamentos", method = RequestMethod.POST)
    public String cadastrarAgendamento(@RequestParam("AG_Data_Inicio_Agendamento") Date AG_DataInicio,
                                       @RequestParam("AG_Hora_Inicio_Agendamento") Time AG_horaInicio,
                                       @RequestParam("AG_Data_Final_Agendamento")  Date AG_DataFinal ,
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

/*    método de teste*/
    @RequestMapping(value = "/agendamentos", method = RequestMethod.GET)
    public String cadastrarAgendamento(){

        Date data = new Date();
        Time time = new Time(1);

        Agendamento agendamento = new Agendamento(data,time,data,2); //Passar dentro do construtor os dados para teste do método
        agendamentoRepository.save(agendamento);

        return "redirect:/agendamentos";
    }
}
