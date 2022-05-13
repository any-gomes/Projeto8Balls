package MeuRemedio.app.models.remedios.agendamentos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "Intervalo_Dias")
public class IntervaloDias implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "IT_ID")
    private long id;

    @Column(name = "IT_Intervalo_Dias")
    private long IT_IntervaloDias;

    @OneToOne
    @JoinColumn(name = "fk_AG_ID")
    private Agendamento FK_AG_ID;

}
