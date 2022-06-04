package MeuRemedio.app.service.utils;

import MeuRemedio.app.models.usuarios.Usuario;
import MeuRemedio.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserSession {
    private String username;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private IAuthentication authenticationFacade;

    @Autowired
    ValidateAuthentication validateAuthentication;

    public String returnUsernameUsuario(){
        Authentication authentication = authenticationFacade.getAuthentication();
        username = authentication.getName();
        return username;
    }

    public long returnIdUsuarioLogado () {
        long idUserLogado;
        Usuario usuarioLogado = usuarioRepository.findByEmail(returnUsernameUsuario());
        idUserLogado = usuarioLogado.getId();

        return idUserLogado ;
    }
}
