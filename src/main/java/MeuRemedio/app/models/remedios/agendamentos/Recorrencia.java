package MeuRemedio.app.models.remedios.agendamentos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name = "Recorrencia")
public class Recorrencia implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RE_ID")
    private long RE_ID;

    @Column(name = "RE_Dias_Semana")
    private long RE_DiasSemana;

    @OneToOne
    private Agendamento FK_AG_ID;
}
