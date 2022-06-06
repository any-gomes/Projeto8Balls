package MeuRemedio.app.controllers.cadastro;


import MeuRemedio.app.models.remedios.Remedio;
import MeuRemedio.app.models.usuarios.Usuario;
import MeuRemedio.app.repository.RemedioRepository;
import MeuRemedio.app.repository.UsuarioRepository;
import MeuRemedio.app.service.EmailService;
import MeuRemedio.app.service.utils.IAuthentication;
import MeuRemedio.app.service.utils.UserSession;
import MeuRemedio.app.service.utils.ValidateAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class RemedioController {
    private String username;

    @Autowired
    EmailService emailService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RemedioRepository remedioRepository;

    @Autowired
    private IAuthentication authenticationFacade;

    @Autowired
    ValidateAuthentication validateAuthentication;

    @Autowired
    UserSession userSession;

    @RequestMapping(value = "/remedios_cadastro")
    public String telaCadastroRemedio(){
        if (!validateAuthentication.auth()){
            return "Login";
        }
        return "CadastroRemedios";
    }

    @RequestMapping(value = "/remedios_cadastro", method = RequestMethod.POST)
    public String CadastroRemedio (@RequestParam("RM_Nome") String RM_Nome, @RequestParam("RM_Dosagem") String RM_Dosagem,
                                   @RequestParam("RM_UnidadeDosagem") String RM_UnidadeDosagem , @RequestParam("RM_RetiradoSus") String RM_RetiradoSus) throws SQLException {

        boolean auxRetiradoSUS;

        Usuario usuarioID = new Usuario();
        usuarioID.setId(userSession.returnIdUsuarioLogado());

        if (usuarioID.getId() <= 0) {
            throw new SQLException("Erro ao retornar ID do usuário ");
        }

        if (RM_RetiradoSus.equals("Sim")){
            auxRetiradoSUS = true;
        } else {
            auxRetiradoSUS = false;
        }
        Remedio remedio = new Remedio(RM_Nome, RM_Dosagem, RM_UnidadeDosagem ,auxRetiradoSUS, usuarioID);
        remedioRepository.save(remedio);

        return "redirect:/remedios";
    }

    @RequestMapping(value = "/remedios" )
    public String listaRemedios(ModelMap model){

        if (!validateAuthentication.auth()){
            return "Login";
        }
        Usuario usuarioID = new Usuario();
        usuarioID.setId(userSession.returnIdUsuarioLogado());

        Iterable<Remedio> remedio = remedioRepository.findAllByUsuario(usuarioID);
        model.addAttribute("remedio", remedio);

        return "Remedios";
    }

/*
    //Metodo de teste, para validar colocar um id valido na URL do navegador
    @RequestMapping(value = "/{id}")
    public String deletarRemedio (@PathVariable("id") long id){
      //  Remedio remedio = remedioRepository.findById(id);
        remedioRepository.deleteById(id);
        return "redirect:/remedios";
    } */

    @RequestMapping(value = "/deletar_remedio{id}")
        public String deletarRemedio (@PathVariable("id") long id){
            //  Remedio remedio = remedioRepository.findById(id);
            remedioRepository.deleteById(id);

        return "redirect:/remedios";
    }

}