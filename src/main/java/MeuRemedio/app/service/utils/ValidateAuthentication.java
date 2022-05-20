package MeuRemedio.app.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class ValidateAuthentication {

    @Autowired
    Authentication authentication;

    @Bean
    public boolean auth () {
        if (authentication.getAuthentication() == null
                || authentication.getAuthentication() instanceof AnonymousAuthenticationToken) {

            return  false;
        }

        return true;
    }
}
