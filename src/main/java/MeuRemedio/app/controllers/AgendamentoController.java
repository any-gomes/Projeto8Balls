package MeuRemedio.app.controllers;

<<<<<<< HEAD
=======
import MeuRemedio.app.controllers.RemedioController;
>>>>>>> 837c82d1776530e9c6a76d05cae479574b1e0d3a
import MeuRemedio.app.models.agendamentos.Agendamento;
import MeuRemedio.app.models.agendamentos.IntervaloDias;
import MeuRemedio.app.models.remedios.Remedio;
import MeuRemedio.app.models.usuarios.Usuario;
import MeuRemedio.app.repository.AgendamentoRepository;
import MeuRemedio.app.repository.IntervaloDiasRepository;
import MeuRemedio.app.repository.RemedioRepository;
import MeuRemedio.app.service.UserSessionService;
import MeuRemedio.app.service.utils.ValidateAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    UserSessionService userSessionService;


    @RequestMapping(value = "/agendamentos")
    public String viewAgendamentos(ModelMap model) {
        if (!validateAuthentication.auth()) {
            return "Login";
        }

        List <Agendamento> agendamentos = agendamentoRepository.findAllByUsuarioID(userSessionService.returnIdUsuarioLogado());
        model.addAttribute("agendamento", agendamentos);
        return "ListaAgendamentos";
    }

    @RequestMapping(value = "/cadastro_agendamentos")
    public String viewCadastroAgendamento(ModelMap model) {
        if (!validateAuthentication.auth()) {
            return "Login";
        }

        Usuario usuarioID = new Usuario();
        usuarioID.setId(userSessionService.returnIdUsuarioLogado());

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
                    remedios, userSessionService.returnIdUsuarioLogado(), intervaloDias);
            intervaloDiasRepository.save(intervalo);
        } else {
            Agendamento agendamento = new Agendamento(AG_DataInicio, AG_horaInicio, AG_DataFinal, AG_Periodicidade,
                    remedios, userSessionService.returnIdUsuarioLogado());

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

    @RequestMapping(value = "/atualizar_agendamento/{id}", method = RequestMethod.GET)
    public String atualizarRemedio(@PathVariable("id") long id, Model model) {
        if (!verificarPorId(id)) {
            return templateError();
        } else {
            Usuario usuarioID = new Usuario();
            usuarioID.setId(userSessionService.returnIdUsuarioLogado());
            Iterable <Remedio> remedio = remedioRepository.findAllByUsuario(usuarioID);
            model.addAttribute("remedio", remedio);
            Agendamento agendamento = agendamentoRepository.findById(id);
            model.addAttribute("agendamento", agendamento);
            return "AtualizarAgendamento";
        }
    }

    @RequestMapping(value = "/atualizar_agendamento/{id}", method = RequestMethod.POST)
    public  String atualizarDadosAgendamento(@PathVariable("id") long id,
                                            @RequestParam("AG_Remedios") List<Remedio> remedios,
                                            @RequestParam("AG_DataInicio") String AG_DataInicio,
                                            @RequestParam("AG_HoraInicio") String AG_horaInicio,
                                            @RequestParam("AG_DataFinal")  String AG_DataFinal ,
                                            @RequestParam("AG_Periodicidade") long AG_Periodicidade){
        if (!verificarPorId(id)) {
            return remedioController.
                    templateError();
        }
            Agendamento agendamento = agendamentoRepository.findById(id);
            agendamento.setRemedio(remedios);
            agendamento.setDataInicio(AG_DataInicio);
            agendamento.setHoraInicio(AG_horaInicio);
            agendamento.setDataFinal(AG_DataFinal);
            agendamento.setPeriodicidade(AG_Periodicidade);

            agendamentoRepository.save(agendamento);
            return "redirect:/agendamentos";
        }
    public boolean verificarPorId (long id ) {
        return agendamentoRepository.existsById(id); //Falso se não achar o ID do remédio
    }

    public String templateError(){
        return "TemplateError";
    }
}
