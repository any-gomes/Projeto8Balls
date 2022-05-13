package MeuRemedio.app.models.remedios;

import MeuRemedio.app.models.remedios.MedidaDosagem;
import MeuRemedio.app.models.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
}
