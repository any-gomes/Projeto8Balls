package MeuRemedio.app.model;


import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Collection;
import java.util.Date;


@Getter
@Setter
@Entity
public class Usuario implements UserDetails {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long US_ID;

    private String US_Nome;
    private String US_Sobrenome;
    @Column(name = "US_Email")
    private String email;
    private String US_Senha;
    private String US_DataNascimento;
    private String US_Sexo;

    public Usuario(){

    }

    public Usuario(String US_Nome, String US_Sobrenome, String US_Email, String US_Senha, String US_DataNascimento, String US_Sexo) {
        this.US_ID = US_ID;
        this.US_Nome = US_Nome;
        this.US_Sobrenome = US_Sobrenome;
        this.email = US_Email;
        this.US_Senha = US_Senha;
        this.US_DataNascimento = US_DataNascimento;
        this.US_Sexo = US_Sexo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.getUS_Senha();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
