package MeuRemedio.app.model;

import MeuRemedio.app.enums.StatusEmail;
import lombok.Data;
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
    private String emailFrom = "fernandes.lima@aluno.ifsp.edu.br" ;
    private String emailTo ;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDate sendDateEmail;
    private StatusEmail statusEmail;
}
