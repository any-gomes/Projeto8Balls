package MeuRemedio.app.models.usuarios;


import MeuRemedio.app.models.remedios.Remedio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter

@Entity
public class Usuario implements UserDetails {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "US_ID")
    private long id;

    @Column(name = "US_Nome") @NotNull @NotBlank
    private String nome;
    @Column(name = "US_Sobrenome") @NotNull @NotBlank
    private String sobrenome;
    @Column(name = "US_Email") @NotNull @NotBlank
    private String email;
    @Column(name = "US_Senha") @NotNull @NotBlank
    private String senha;
    @Column(name = "US_DataNascimento") @NotNull @NotBlank
    private String dataNascimento;
    @Column(name = "US_Sexo") @NotNull @NotBlank
    private String sexo;

    @Column(name = "GA_Cadastrado_Em")
    private LocalDate dataCadastro;

    @OneToMany @NotNull @NotBlank
    @JoinColumn(name = "USUARIO_FK_US_ID")
    private List <Gasto> gasto;

    @OneToMany @NotNull @NotBlank
    @JoinColumn(name = "USUARIO_FK_US_ID")
    private List <Remedio> remedio;


    public Usuario (String nome, String sobrenome, String email, String senha, String dataNascimento,
                   String sexo, LocalDate dataCadastro, List<Remedio> remedios, List<Gasto> gasto) {


        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.dataCadastro = dataCadastro;
       // this.remedios = remedios;
        this.gasto = gasto;
    }


    /*Esse contrutor está sendo usado para não quebrar o progrma, NÂO APAGAR o método*/

    public Usuario (String nome, String sobrenome, String email,
                    String senha, String dataNascimento, String sexo /*List<Remedio> remedios*/) {

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        /*this.remedios = remedios;*/
    }

    public Usuario() {

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
