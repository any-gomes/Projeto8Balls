package MeuRemedio.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementsUserDetailsService implementsUserDetailsService;

    @Override
    public void configure (HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/cadastro", "//").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") //Passar dentro do método o arquivo customizado dá página de login e descomentar
                .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
                .and().rememberMe();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(implementsUserDetailsService)
                .passwordEncoder(passwordEncoder());
//                .inMemoryAuthentication()
//                    .withUser("ADMIN")
//                    .password(passwordEncoder().encode("{noop}ADMIN"))
//                    .roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override //Método para não bloquear as páginas Staticas
    public void configure (WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/template/**","/style/**","/assets/**","/script/**", "/images/**");

    }
}