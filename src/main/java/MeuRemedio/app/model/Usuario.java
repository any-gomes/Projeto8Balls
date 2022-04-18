package MeuRemedio.app.model;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;


@Getter
@Setter
@Entity
public class Usuario {
    private static long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long US_ID;

    private String US_Nome;
    private String US_Sobrenome;
    private String US_Email;
    private String US_Senha;
    private Date US_DataNascimento;
    private char US_Sexo;

    public Usuario(){

    }

    public Usuario(long US_ID, String US_Nome, String US_Sobrenome, String US_Email, String US_Senha, Date US_DataNascimento, char US_Sexo) {
        this.US_ID = US_ID;
        this.US_Nome = US_Nome;
        this.US_Sobrenome = US_Sobrenome;
        this.US_Email = US_Email;
        this.US_Senha = US_Senha;
        this.US_DataNascimento = US_DataNascimento;
        this.US_Sexo = US_Sexo;
    }
}
