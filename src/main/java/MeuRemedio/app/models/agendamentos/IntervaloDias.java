package MeuRemedio.app.models.agendamentos;


import MeuRemedio.app.models.remedios.Remedio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter

@PrimaryKeyJoinColumn(referencedColumnName = "AG_ID")
@Table(name = "Intervalo_Dias")
public class IntervaloDias extends Agendamento implements Serializable {
    private static long serialVersionUID = 1L;

    @Column(name = "IT_IntervaloDias")
    private long intervaloDias;

    public IntervaloDias(String AG_DataInicio, String AG_horaInicio, String AG_DataFinal, long AG_Periodicidade, List<Remedio> remedios, long IT_IntervaloDias) {
        super(AG_DataInicio, AG_horaInicio, AG_DataFinal, AG_Periodicidade);
        this.intervaloDias = IT_IntervaloDias;
    }


    public IntervaloDias(String AG_DataInicio, String AG_horaInicio, String AG_DataFinal, long AG_Periodicidade,
                         List<Remedio> remedio, Long usuarioID, long IT_IntervaloDias) {
        super(AG_DataInicio, AG_horaInicio, AG_DataFinal, AG_Periodicidade, remedio, usuarioID);
        this.intervaloDias = IT_IntervaloDias;
    }

    public IntervaloDias(Agendamento agendamento, long IT_IntervaloDias) {
        this.intervaloDias = IT_IntervaloDias;
    }



    public  IntervaloDias(){

    }
}
