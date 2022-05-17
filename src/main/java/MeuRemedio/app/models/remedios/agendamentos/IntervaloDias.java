package MeuRemedio.app.models.remedios.agendamentos;


import MeuRemedio.app.models.remedios.Remedio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    public IntervaloDias(Date AG_DataInicio, Time AG_horaInicio, Date AG_DataFinal, long AG_Periodicidade, long IT_IntervaloDias) {
        super(AG_DataInicio, AG_horaInicio, AG_DataFinal, AG_Periodicidade);
        this.IT_IntervaloDias = IT_IntervaloDias;
    }

    public IntervaloDias(long AG_ID, Date AG_DataInicio, Time AG_horaInicio, Date AG_DataFinal, long AG_Periodicidade, LocalDate cadastrado_em, List<Remedio> remedio, long IT_IntervaloDias) {
        super(AG_ID, AG_DataInicio, AG_horaInicio, AG_DataFinal, AG_Periodicidade, cadastrado_em, remedio);
        this.IT_IntervaloDias = IT_IntervaloDias;
    }

    public IntervaloDias(long AG_ID, Date AG_DataInicio, Time AG_horaInicio, Date AG_DataFinal, long AG_Periodicidade, LocalDate cadastrado_em, long IT_IntervaloDias) {
        super(AG_ID, AG_DataInicio, AG_horaInicio, AG_DataFinal, AG_Periodicidade, cadastrado_em);
        this.IT_IntervaloDias = IT_IntervaloDias;
    }

    public IntervaloDias(long IT_IntervaloDias) {
        this.IT_IntervaloDias = IT_IntervaloDias;
    }

    public  IntervaloDias(){

    }
}
