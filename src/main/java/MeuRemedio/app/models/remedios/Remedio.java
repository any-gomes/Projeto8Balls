package MeuRemedio.app.models.remedios;

import MeuRemedio.app.models.remedios.MedidaDosagem;
import MeuRemedio.app.models.remedios.agendamentos.Agendamento;
import MeuRemedio.app.models.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "REMEDIO")
public class Remedio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RM_ID", nullable = false)
    private Long RM_ID;

    @NotNull
    private String RM_Nome;

    @NotNull
    private String RM_Dosagem;

    @NotNull
    private Boolean RM_RetiradoSus;

    @ManyToOne
    @JoinColumn(name = "FK_MedidaDosagem")
    private MedidaDosagem FK_MD_ID;

    @ManyToOne
    @JoinColumn(name = "FK_Categoria")
    private Categoria FK_CT_ID;

    @ManyToOne
    @JoinColumn(name = "FK_Usuario")
    private Usuario FK_US_ID;

    @ManyToOne
    @JoinColumn(name = "AG_ID")
    private Agendamento FK_Agendamento;

    private LocalDate Cadastrado_em = LocalDate.now();

    public Remedio (Long RM_ID, String RM_Nome, String RM_Dosagem, Boolean RM_RetiradoSus,
                   MedidaDosagem FK_MD_ID, Categoria FK_CT_ID, Usuario FK_US_ID, Agendamento FK_Agendamento) {
        this.RM_ID = RM_ID;
        this.RM_Nome = RM_Nome;
        this.RM_Dosagem = RM_Dosagem;
        this.RM_RetiradoSus = RM_RetiradoSus;
        this.FK_MD_ID = FK_MD_ID;
        this.FK_CT_ID = FK_CT_ID;
        this.FK_US_ID = FK_US_ID;
        this.FK_Agendamento = FK_Agendamento;
    }
    public Remedio (){

    }
}
