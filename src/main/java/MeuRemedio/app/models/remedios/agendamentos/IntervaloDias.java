package MeuRemedio.app.models.remedios.agendamentos;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Entity
@Getter
@Setter

@PrimaryKeyJoinColumn(referencedColumnName = "AG_ID")
@Table(name = "Intervalo_Dias")
public class IntervaloDias extends Agendamento implements Serializable {
    private static long serialVersionUID = 1L;

    @Column(name = "IT_Intervalo_Dias")
    private long IT_IntervaloDias;

    public IntervaloDias (long id, Date AG_DataInicio, Time AG_horaInicio, Date AG_DataFinal, long AG_Periodicidade, long IT_IntervaloDias) {
        super(id, AG_DataInicio, AG_horaInicio, AG_DataFinal, AG_Periodicidade);
        this.IT_IntervaloDias = IT_IntervaloDias;
    }

    public IntervaloDias() {

    }
}
