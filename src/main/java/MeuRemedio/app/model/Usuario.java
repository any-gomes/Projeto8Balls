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
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;


@Getter
@Setter
@Entity
public class Usuario implements UserDetails {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "US_ID")
    private long id;

    @Column(name = "US_Nome") @NotNull
    private String nome;
    @Column(name = "US_Sobrenome")  @NotNull
    private String sobrenome;
    @Column(name = "US_Email") @NotNull
    private String email;
    @Column(name = "US_Senha") @NotNull
    private String senha;
    @Column(name = "US_DataNascimento") @NotNull
    private String dataNascimento;
    @Column(name = "US_Sexo") @NotNull
    private String sexo;

    public Usuario(){

    }

    public Usuario(String nome, String sobrenome, String email, String senha, String dataNascimento, String sexo) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.getSenha();
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
