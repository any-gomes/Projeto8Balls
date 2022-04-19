package MeuRemedio.app.controllers;

import MeuRemedio.app.model.Usuario;
import MeuRemedio.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CadastroUsuarioController {
    @Autowired
    private UsuarioRepository UsuarioRepository;


    @RequestMapping(value = "/Cadastro")
    public String telaCadasUsuario(){

        return "cadastro";
    }


   /* Método de teste de inserção
        @RequestMapping(value = "/Cadastro")
        public String formCadastrarUsuario() throws ParseException {
        String str = "22/05/2006";
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formatador.parse(str);


        Usuario usuario = new Usuario("Guilherme", "Lima",
                "Guilherme@teste", "teste",
                "2021/12/12", "Masculino");

        UsuarioRepository.save(usuario);
        return "redirect:/";
    }*/

    @RequestMapping(value = "/Cadastro", method = RequestMethod.POST)
    public String CadastrarUsuario(@RequestParam("US_Nome") String US_Nome, @RequestParam("US_Sobrenome") String US_Sobrenome,
            @RequestParam("US_Email") String US_Email,@RequestParam("US_Senha") String US_Senha,
            @RequestParam("US_DataNascimento") String US_DataNascimento, @RequestParam("US_Sexo") String US_Sexo){

        Usuario usuarioCadastro  = new Usuario(US_Nome,US_Sobrenome,US_Email,
        US_Senha,US_DataNascimento, US_Sexo);

        UsuarioRepository.save(usuarioCadastro);

        return "login";
    }

}
