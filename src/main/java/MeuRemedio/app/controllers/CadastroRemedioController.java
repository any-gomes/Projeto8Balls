package MeuRemedio.app.controllers;


import MeuRemedio.app.models.remedios.Remedio;
import MeuRemedio.app.models.usuarios.Usuario;
import MeuRemedio.app.repository.RemedioRepository;
import MeuRemedio.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CadastroRemedioController {
    @Autowired
    EmailService emailService;

    @Autowired
    RemedioRepository remedioRepository;

    @Autowired
    EnvioEmailController emailCadastro;

    @RequestMapping(value = "/cadastro_Remedio", method = RequestMethod.POST)
    public String CadastroRemedio (@RequestParam("RM_Nome") String RM_Nome, @RequestParam("RM_Dosagem") String RM_Dosagem,
                                   @RequestParam("RM_UnidadeDosagem") String RM_UnidadeDosagem, @RequestParam("RM_RetiradoSus") String RM_RetiradoSus){



        /*Remedio remedio = new Remedio(RM_Nome, RM_Dosagem, RM_UnidadeDosagem,null);*/
        Remedio remedio = new Remedio("Amoxilina", "10", "miligrmas",null);
        remedioRepository.save(remedio);


        return "Redirect:/cadastro_remedio";
    }

    @RequestMapping(value = "/cadastro_remedio", method = RequestMethod.GET)
    public String CadastroRemedio (){


        /*Remedio remedio = new Remedio(RM_Nome, RM_Dosagem, RM_UnidadeDosagem, null);*/
        Remedio remedio = new Remedio("Amoxilina", "10", "miligrmas",null);
        remedioRepository.save(remedio);


        return "redirect:/home";
    }

}
