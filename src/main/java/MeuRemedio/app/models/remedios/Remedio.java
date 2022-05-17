package MeuRemedio.app.models.remedios;

import MeuRemedio.app.models.remedios.agendamentos.Agendamento;
import MeuRemedio.app.models.usuarios.Usuario;
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
@Table(name = "REMEDIO")
public class Remedio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RM_ID", nullable = false)
    private Long RM_ID;

    private String RM_Nome;

    private String RM_Dosagem;

    private String RM_UnidadeDosagem;

    private Boolean RM_RetiradoSus;

    @ManyToMany(cascade = CascadeType.ALL) @NotNull @NotBlank
    @JoinTable(name="RemedioCategoria",
                joinColumns= {@JoinColumn(name="remedio_rm_id")},
                inverseJoinColumns = {@JoinColumn(name="categoria_ct_id")})

    private List<Categoria> categoria = new ArrayList <Categoria> () ;

/*    @ManyToOne @NotNull @NotBlank
    @JoinColumn(name = "Usuario_FK_Usuario")
    private Usuario usuario;*/

    private LocalDate Cadastrado_em = LocalDate.now();

    public Remedio ( String RM_Nome, String RM_Dosagem, String RM_UnidadeDosagem, Boolean RM_RetiradoSus, List<Categoria> FK_CT_ID,
                   /*Usuario FK_US_ID,*/ LocalDate cadastrado_em) {

        this.RM_Nome = RM_Nome;
        this.RM_Dosagem = RM_Dosagem;
        this.RM_RetiradoSus = RM_RetiradoSus;
        this.RM_UnidadeDosagem = RM_UnidadeDosagem;
        this.categoria = FK_CT_ID;
        /*this.usuario = FK_US_ID;*/
        this.Cadastrado_em = cadastrado_em;
    }

    public Remedio (String RM_Nome, String RM_Dosagem, String RM_UnidadeDosagem, Boolean RM_RetiradoSus,
                    /*Usuario FK_US_ID,*/ LocalDate cadastrado_em) {

        this.RM_Nome = RM_Nome;
        this.RM_Dosagem = RM_Dosagem;
        this.RM_RetiradoSus = RM_RetiradoSus;
        this.RM_UnidadeDosagem = RM_UnidadeDosagem;
        /*this.usuario = FK_US_ID;*/
        this.Cadastrado_em = cadastrado_em;
    }

    public Remedio (){

    }
}
