package MeuRemedio.app.model;

import MeuRemedio.app.enums.StatusEmail;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name="TB_EMAIL")
public class EmailModel implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long EM_ID;

    //Validar com o usuario em email. Ligação.
    @Column(name = "US_ID")
    private long ownerRef;

    private String EM_Remetente = "8balls.integratedproject@gmail.com" ;

    private String EM_Destinatario;
    private String EM_Assunto;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDate EM_DataEnvioEmail;
    private StatusEmail EM_statusEmail;
}
