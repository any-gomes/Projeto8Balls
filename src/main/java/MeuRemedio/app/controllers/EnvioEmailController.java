package MeuRemedio.app.controllers;

import MeuRemedio.app.enums.MensagemEmail;
import MeuRemedio.app.models.remedios.Remedio;
import MeuRemedio.app.models.usuarios.Usuario;
import MeuRemedio.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class EnvioEmailController {
    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/recuperar_senha", method = RequestMethod.POST)
    public void emailRecuperarSenha (Usuario usuario){
        String link = "https://meuremedioapp.herokuapp.com/cadastro";
        String msgRecuperacao = usuario.getNome() + " " + usuario.getSobrenome();
        String assunto = MensagemEmail.RECUPERACAO_SENHA.getDescricao();
        String mensagem = msgRecuperacao + MensagemEmail.RECUPERACAO_MENSAGEM.getDescricao() + link;

        emailService.sendEmail(usuario, assunto, mensagem );
    }

    public void emailConfirmCadastro (Usuario usuario){
        String link = "https://meuremedioapp.herokuapp.com/login";
        String msgBoasVindas = "Olá, " + usuario.getNome() + " " + usuario.getSobrenome();
        String assunto = MensagemEmail.CADASTRO_REALIZADO.getDescricao();
        String mensagem = msgBoasVindas + MensagemEmail.CADASTRO_MENSAGEM.getDescricao() + link;

        emailService.sendEmail(usuario, assunto, mensagem );
    }

    public void emailCadastroRemedio(Usuario usuario, Remedio remedios){
        String assunto = MensagemEmail.REMEDIO_CADASTRADO.getDescricao();
        String msg = MensagemEmail.CADASTRO_REMEDIO.getDescricao();

        emailService.sendEmail(usuario, assunto, msg);
    }

    public void emailNotificacaoRemedio(Usuario usuario, List<Remedio> remedios, LocalDateTime instanteAgora){
        String assunto = MensagemEmail.NOTIFICACAO_REMEDIO.getDescricao();

        String horaFormatada = instanteAgora.getHour() + ":" + instanteAgora.getMinute();
        StringBuilder remediosString = new StringBuilder("");
        for (Remedio remedio : remedios) {
            remediosString.append(remedio.getRM_Nome()    + "-" +
                                  remedio.getRM_Dosagem() + "" +
                                  remedio.getRM_UnidadeDosagem())
            .append("\n");
        }
        String msg = "Olá, " + usuario.getNome() + " " + usuario.getSobrenome() +
                "! Agora são " + horaFormatada + " e já está na hora de tomar os seus remédios: \n" + remediosString;

        emailService.sendEmail(usuario, assunto, msg);
    }
}
