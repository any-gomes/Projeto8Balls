package MeuRemedio.app.models.agendamentos;


import MeuRemedio.app.models.remedios.Remedio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter

@PrimaryKeyJoinColumn(referencedColumnName = "AG_ID")
@Table(name = "Intervalo_Dias")
public class IntervaloDias extends Agendamento implements Serializable {
    private static long serialVersionUID = 1L;

    @Column(name = "IT_IntervaloDias")
    private long IT_IntervaloDias;

    @NotBlank
    private LocalDate Criado_em;

    public IntervaloDias(String AG_DataInicio, String AG_horaInicio, String AG_DataFinal, long AG_Periodicidade, long IT_IntervaloDias) {
        super(AG_DataInicio, AG_horaInicio, AG_DataFinal, AG_Periodicidade);
        this.IT_IntervaloDias = IT_IntervaloDias;
        this.Criado_em = LocalDate.now();
    }


    public IntervaloDias(String AG_DataInicio, String AG_horaInicio, String AG_DataFinal, long AG_Periodicidade, List<Remedio> remedio, long IT_IntervaloDias) {
        super(AG_DataInicio, AG_horaInicio, AG_DataFinal, AG_Periodicidade, remedio);
        this.IT_IntervaloDias = IT_IntervaloDias;
        this.Criado_em = LocalDate.now();
    }

    public IntervaloDias(long IT_IntervaloDias) {
        this.IT_IntervaloDias = IT_IntervaloDias;
        this.Criado_em = LocalDate.now();
    }

    public  IntervaloDias(){

    }
}
