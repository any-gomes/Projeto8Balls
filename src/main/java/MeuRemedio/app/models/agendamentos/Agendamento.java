package MeuRemedio.app.models.agendamentos;

import MeuRemedio.app.models.remedios.Remedio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "Agendamento")
@Inheritance(strategy = InheritanceType.JOINED)
public class Agendamento implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "AG_ID")
    private long id;

    @NotNull @NotBlank
    @Column(name = "AG_Data_Inicio_Agendamento")
    private String dataInicio;

    @NotNull @NotBlank
    @Column(name = "AG_Hora_Inicio_Agendamento")
    private String horaInicio;

    @NotNull @NotBlank
    @Column(name = "AG_Data_Final_Agendamento")
    private String dataFinal;

    @NotNull
    @Column(name = "AG_Periodicidade")
    private long periodicidade;

    @NotNull
    private LocalDate AG_Criado_em;

    @Column(name = "AG_UsuarioID")
    private Long usuarioID;

    @ManyToMany()
    @JoinTable(name="AgendamentoRemedio",
            joinColumns= {@JoinColumn(name="agendamento_ag_id")},
            inverseJoinColumns = {@JoinColumn(name="remedio_rm_id")})
    private List <Remedio> remedio = new ArrayList <> ();

    public Agendamento (String AG_DataInicio, String AG_horaInicio,
                        String AG_DataFinal, long AG_Periodicidade) {

        this.dataInicio = AG_DataInicio;
        this.horaInicio = AG_horaInicio;
        this.dataFinal = AG_DataFinal;
        this.periodicidade = AG_Periodicidade;
        this.AG_Criado_em = LocalDate.now();
    }

    public Agendamento (String AG_DataInicio, String AG_horaInicio,
                        String AG_DataFinal, long AG_Periodicidade, List<Remedio> remedio, Long usuarioID) {

        this.dataInicio = AG_DataInicio;
        this.horaInicio = AG_horaInicio;
        this.dataFinal = AG_DataFinal;
        this.periodicidade = AG_Periodicidade;
        this.AG_Criado_em = LocalDate.now();
        this.remedio = remedio;
        this.usuarioID = usuarioID;
    }

    public Agendamento() {

    }
}
