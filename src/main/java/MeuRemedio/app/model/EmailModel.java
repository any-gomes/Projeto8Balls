package MeuRemedio.app.model;

import MeuRemedio.app.enums.StatusEmail;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name="TB_EMAIL")
public class EmailModel implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long emailID;
    private String ownerRef;
    private String emailFrom = "**********";
    private String emailTo= "**********";
    private String subject= "Teste de envio de e-mail";

    @Column(columnDefinition = "TEXT")
    private String text= "Teste do serviço de envio de email para recuperação de senha e outras notificações";
    private LocalDate sendDateEmail;
    private StatusEmail statusEmail;
}