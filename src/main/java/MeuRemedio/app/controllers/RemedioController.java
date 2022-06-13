package MeuRemedio.app.controllers;


import MeuRemedio.app.models.remedios.Remedio;
import MeuRemedio.app.models.usuarios.Usuario;
import MeuRemedio.app.repository.RemedioRepository;
import MeuRemedio.app.repository.UsuarioRepository;
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

import java.sql.SQLException;

@Controller
public class RemedioController {
    private String username;



    @Autowired
    EnvioEmailController emailController;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RemedioRepository remedioRepository;


    @Autowired
    ValidateAuthentication validateAuthentication;

    @Autowired
    UserSessionService userSessionService;

    @RequestMapping(value = "/remedios_cadastro")
    public String telaCadastroRemedio() {
        if (!validateAuthentication.auth()) {
            return "Login";
        }
        return "CadastroRemedios";
    }

    @RequestMapping(value = "/remedios_cadastro", method = RequestMethod.POST)
    public String CadastroRemedio(@RequestParam("RM_Nome") String RM_Nome, @RequestParam("RM_Dosagem") String RM_Dosagem,
                                  @RequestParam("RM_UnidadeDosagem") String RM_UnidadeDosagem, @RequestParam("RM_RetiradoSus") String RM_RetiradoSus) throws SQLException {

        boolean auxRetiradoSUS;

        Usuario usuarioID = new Usuario();
        usuarioID.setId(userSessionService.returnIdUsuarioLogado());

        if (usuarioID.getId() <= 0) {
            throw new SQLException("Erro ao retornar ID do usuário ");
        }

        if (RM_RetiradoSus.equals("Sim")) {
            auxRetiradoSUS = true;
        } else {
            auxRetiradoSUS = false;
        }
        Remedio remedio = new Remedio(RM_Nome, RM_Dosagem, RM_UnidadeDosagem, auxRetiradoSUS, usuarioID);

        remedioRepository.save(remedio);
        Usuario us = usuarioRepository.findByEmail(userSessionService.returnUsernameUsuario());
        emailController.emailCadastroRemedio(us, remedio);

        return "redirect:/remedios";
    }

    @RequestMapping(value = "/remedios")
    public String listaRemedios(ModelMap model) {

        if (!validateAuthentication.auth()) {
            return "Login";
        }
        Usuario usuarioID = new Usuario();
        usuarioID.setId(userSessionService.returnIdUsuarioLogado());

        Iterable<Remedio> remedio = remedioRepository.findAllByUsuario(usuarioID);
        model.addAttribute("remedio", remedio);

        return "ListaRemedios";
    }

    @RequestMapping(value = "/deletar_remedio/{id}")
    public String deletarRemedio(@PathVariable("id") long id) {
        //  Remedio remedio = remedioRepository.findById(id);
        remedioRepository.deleteById(id);

        return "redirect:/remedios";
    }

    @RequestMapping(value = "/atualizar_remedio/{id}", method = RequestMethod.GET)
    public String atualizarRemedio(@PathVariable("id") long id, Model model) {
        if (!verificarPorId(id)) {
            return templateError();
        } else {
            Remedio remedio = remedioRepository.findById(id);
            model.addAttribute("remedio", remedio);
            return "AtualizarRemedios";
        }
    }

    @RequestMapping(value = "/atualizar_remedio/{id}", method = RequestMethod.POST)
    public String atualizarDadosRemedio(@PathVariable("id") long id,  @RequestParam("RM_Nome") String RM_Nome, @RequestParam("RM_Dosagem") String RM_Dosagem,
                                       @RequestParam("RM_UnidadeDosagem") String RM_UnidadeDosagem, @RequestParam("RM_RetiradoSus") String RM_RetiradoSus)  {
        boolean auxRetiradoSUS;

        if (RM_RetiradoSus.equals("Sim")) {
            auxRetiradoSUS = true;
        } else {
            auxRetiradoSUS = false;
        }

        if (!verificarPorId(id)) {
             return templateError();
        } else {
            Remedio remedio = remedioRepository.findById(id);
            remedio.setRM_Nome(RM_Nome);
            remedio.setRM_Dosagem(RM_Dosagem);
            remedio.setRM_UnidadeDosagem(RM_UnidadeDosagem);
            remedio.setRM_RetiradoSus(auxRetiradoSUS);
            remedioRepository.save(remedio);

            return "redirect:/remedios";
        }
    }
    //função responsável por achar um id dentro do banco. Retorna true se encontrar
    public boolean verificarPorId (long id ) {
        return remedioRepository.existsById(id);
    }

    //Essa função deve retornar uma tela customizada de erro.
    public String templateError(){
        return "TemplateError";
    }
}
