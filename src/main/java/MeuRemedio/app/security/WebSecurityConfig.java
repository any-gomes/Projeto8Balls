/*
package MeuRemedio.app.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure (HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/Login", "/Cadastro").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/Login") //Passar dentro do método o arquivo customizado dá página de login e descomentar
                    .defaultSuccessUrl("/Home")
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("Logout"))
                    .logoutSuccessUrl("/Logout");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                    .withUser("ADMIN")
                    .password("{noop}ADMIN")
                    .roles("ADMIN");
    }
    @Override //Método para não bloquear as páginas Staticas
    public void configure (WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/template/**");

    }
}
*/
