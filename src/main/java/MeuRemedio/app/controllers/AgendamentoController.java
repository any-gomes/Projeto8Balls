package MeuRemedio.app.controllers;

import MeuRemedio.app.controllers.cadastro.RemedioController;
import MeuRemedio.app.models.agendamentos.Agendamento;
import MeuRemedio.app.models.agendamentos.IntervaloDias;
import MeuRemedio.app.models.remedios.Remedio;
import MeuRemedio.app.models.usuarios.Usuario;
import MeuRemedio.app.repository.AgendamentoRepository;
import MeuRemedio.app.repository.IntervaloDiasRepository;
import MeuRemedio.app.repository.RemedioRepository;
import MeuRemedio.app.service.utils.UserSession;
import MeuRemedio.app.service.utils.ValidateAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AgendamentoController {
    @Autowired
    ValidateAuthentication validateAuthentication;

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Autowired
    IntervaloDiasRepository intervaloDiasRepository;

    @Autowired
    RemedioRepository remedioRepository;

    @Autowired
    RemedioController remedioController;

    @Autowired
    UserSession userSession;


    @RequestMapping(value = "/agendamentos")
    public String viewAgendamentos(ModelMap model) {
        if (!validateAuthentication.auth()) {
            return "Login";
        }

        List <Agendamento> agendamentos = agendamentoRepository.findAllByUsuarioID(userSession.returnIdUsuarioLogado());
        model.addAttribute("agendamento", agendamentos);
        return "Agendamento";
    }

    @RequestMapping(value = "/cadastro_agendamentos")
    public String viewCadastroAgendamento(ModelMap model) {
        if (!validateAuthentication.auth()) {
            return "Login";
        }

        Usuario usuarioID = new Usuario();
        usuarioID.setId(userSession.returnIdUsuarioLogado());

        Iterable <Remedio> remedio = remedioRepository.findAllByUsuario(usuarioID);
        model.addAttribute("remedio", remedio);

        return "CadastroAgendamento";
    }

    @RequestMapping(value = "/cadastro_agendamentos", method = RequestMethod.POST)
    public String cadastrarAgendamento(@RequestParam("AG_Remedios") List<Remedio> remedios,
                                       @RequestParam("AG_DataInicio") String AG_DataInicio,
                                       @RequestParam("AG_HoraInicio") String AG_horaInicio,
                                       @RequestParam("AG_DataFinal")  String AG_DataFinal ,
                                       @RequestParam("AG_Periodicidade") long AG_Periodicidade,
                                       @RequestParam(value = "intervaloDias", required = false) Long intervaloDias){

        if(intervaloDias != null){
            IntervaloDias intervalo = new IntervaloDias(AG_DataInicio,AG_horaInicio,AG_DataFinal,AG_Periodicidade,
                    remedios, userSession.returnIdUsuarioLogado(), intervaloDias);
            intervaloDiasRepository.save(intervalo);
        } else {
            Agendamento agendamento = new Agendamento(AG_DataInicio, AG_horaInicio, AG_DataFinal, AG_Periodicidade,
                    remedios, userSession.returnIdUsuarioLogado());

            agendamentoRepository.save(agendamento);
        }
        return "redirect:/agendamentos";
    }


    @RequestMapping(value="/deletar_agendamento/{id}")
    public String deletarAgendamento(@PathVariable("id") long id){
        Agendamento agendamento = agendamentoRepository.findById(id);
        agendamentoRepository.delete(agendamento);

        return "redirect:/agendamentos";
    }
    @RequestMapping(value = "/agendamentos/atualizar/{id}", method = RequestMethod.PUT)
    public  String atualizarDadosAgendamento(@PathVariable("id") long id,
                                            @RequestParam("AG_Remedios") List<Remedio> remedios,
                                            @RequestParam("AG_DataInicio") String AG_DataInicio,
                                            @RequestParam("AG_HoraInicio") String AG_horaInicio,
                                            @RequestParam("AG_DataFinal")  String AG_DataFinal ,
                                            @RequestParam("AG_Periodicidade") long AG_Periodicidade){

        Agendamento agendamento = agendamentoRepository.findById(id);
        agendamento.setRemedio(remedios);
        agendamento.setDataInicio(AG_DataInicio);
        agendamento.setHoraInicio(AG_horaInicio);
        agendamento.setDataFinal(AG_DataFinal);
        agendamento.setPeriodicidade(AG_Periodicidade);

        agendamentoRepository.save(agendamento);
        return "redirect/agendamentos";

    }
}
