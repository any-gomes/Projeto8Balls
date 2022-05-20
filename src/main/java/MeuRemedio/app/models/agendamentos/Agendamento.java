package MeuRemedio.app.models.agendamentos;

import MeuRemedio.app.models.remedios.Categoria;
import MeuRemedio.app.models.remedios.Remedio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    private long AG_ID;

    @NotNull
    @Column(name = "AG_Data_Inicio_Agendamento")
    private Date AG_DataInicio;

    @NotNull
    @Column(name = "AG_Hora_Inicio_Agendamento")
    private Time AG_horaInicio;

    @NotNull
    @Column(name = "AG_Data_Final_Agendamento")
    private Date AG_DataFinal;

    @NotNull
    private long AG_Periodicidade;

    @NotNull
    private LocalDate AG_Criado_em ;

    @ManyToMany(cascade = CascadeType.ALL) @NotNull
    @JoinTable(name="AgendamentoRemedio",
            joinColumns= {@JoinColumn(name="agendamento_ag_id")},
            inverseJoinColumns = {@JoinColumn(name="remedio_rm_id")})
    private List <Remedio> remedio = new ArrayList <Remedio> ();

    public Agendamento (Date AG_DataInicio, Time AG_horaInicio,
                        Date AG_DataFinal, long AG_Periodicidade) {

        this.AG_DataInicio = AG_DataInicio;
        this.AG_horaInicio = AG_horaInicio;
        this.AG_DataFinal = AG_DataFinal;
        this.AG_Periodicidade = AG_Periodicidade;
        this.AG_Criado_em = LocalDate.now();
    }

    public Agendamento (Date AG_DataInicio, Time AG_horaInicio,
                        Date AG_DataFinal, long AG_Periodicidade, List<Remedio> remedio) {

        this.AG_DataInicio = AG_DataInicio;
        this.AG_horaInicio = AG_horaInicio;
        this.AG_DataFinal = AG_DataFinal;
        this.AG_Periodicidade = AG_Periodicidade;
        this.AG_Criado_em = LocalDate.now();
        this.remedio = remedio;
    }

    public Agendamento() {

    }
}
