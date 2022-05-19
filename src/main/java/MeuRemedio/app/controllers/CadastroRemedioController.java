package MeuRemedio.app.controllers;


import MeuRemedio.app.models.remedios.Remedio;
import MeuRemedio.app.models.usuarios.Usuario;
import MeuRemedio.app.repository.RemedioRepository;
import MeuRemedio.app.repository.UsuarioRepository;
import MeuRemedio.app.service.EmailService;
import MeuRemedio.app.utils.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class CadastroRemedioController {
    private String username;

    @Autowired
    EmailService emailService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RemedioRepository remedioRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    EnvioEmailController emailCadastro;

    public String returnUsernameUsuario(){
        Authentication authentication = authenticationFacade.getAuthentication();
        username = authentication.getName();

        return  username;
    }

    public long returnIdUsuarioLogado () {
        long idUserLogado;
        Usuario usuarioLogados = usuarioRepository.findByEmail(returnUsernameUsuario());
        idUserLogado = usuarioLogados.getId();

        return idUserLogado ;
    }

    @RequestMapping(value = "/cadastro_Remedio", method = RequestMethod.POST)
    public String CadastroRemedio (@RequestParam("RM_Nome") String RM_Nome, @RequestParam("RM_Dosagem") String RM_Dosagem,
                                   @RequestParam("RM_UnidadeDosagem") String RM_UnidadeDosagem, @RequestParam("RM_RetiradoSus") String RM_RetiradoSus) throws SQLException {

        Usuario usuarioID = new Usuario();
        usuarioID.setId(returnIdUsuarioLogado());

        if (usuarioID.getId() <= 0) {
            throw new SQLException("Erro ao retornar ID do usuário ");
        }

        Remedio remedio = new Remedio(RM_Nome, RM_Dosagem, RM_UnidadeDosagem,
                false, usuarioID);

        remedioRepository.save(remedio);
        return "redirect:/cadastro_remedio";
    }


    /*Metodo de teste*/
    @RequestMapping(value = "/cadastro_remedio", method = RequestMethod.GET)
    public String CadastroRemedio () throws SQLException {
        Usuario usuarioID = new Usuario();
        usuarioID.setId(returnIdUsuarioLogado());

        if (usuarioID.getId() <= 0) {
            throw new SQLException("Erro ao retornar ID do usuário ");
        }
        Remedio remedio = new Remedio("Amoxilina", "10", "miligrmas",
                false, usuarioID);

        remedioRepository.save(remedio);

        return "redirect:/home";
    }
}
