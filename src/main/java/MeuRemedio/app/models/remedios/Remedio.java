package MeuRemedio.app.models.remedios;

import MeuRemedio.app.models.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RM_ID", nullable = false)
    private Long RM_ID;

    @Column(nullable = false)
    @NotNull @NotEmpty
    @NotBlank
    private String RM_Nome;

    @Column(nullable = false)
    @NotNull @NotEmpty
    @NotBlank
    private String RM_Dosagem;

    @Column(nullable = false)
    @NotNull @NotEmpty
    @NotBlank
    private String RM_UnidadeDosagem;


    private Boolean RM_RetiradoSus = null;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="RemedioCategoria",
                joinColumns= {@JoinColumn(name="remedio_rm_id")},
                inverseJoinColumns = {@JoinColumn(name="categoria_ct_id")})

    private List<Categoria> categoria = new ArrayList <Categoria> () ;


    @ManyToOne @NotNull
    @JoinColumn(name = "Usuario_FK_Usuario")
    private Usuario usuario;

    @NotNull
    private LocalDate Criado_em = LocalDate.now();

    public Remedio(String RM_Nome, String RM_Dosagem, String RM_UnidadeDosagem, Boolean RM_RetiradoSus,
                    Usuario usuario) {
        this.RM_Nome = RM_Nome;
        this.RM_Dosagem = RM_Dosagem;
        this.RM_UnidadeDosagem = RM_UnidadeDosagem;
        this.RM_RetiradoSus = RM_RetiradoSus;
        this.categoria = categoria;
       this.usuario = usuario;
    }

    public Remedio (String RM_Nome, String RM_Dosagem, String RM_UnidadeDosagem, Boolean RM_RetiradoSus
                    /*Usuario FK_US_ID,*/) {

        this.RM_Nome = RM_Nome;
        this.RM_Dosagem = RM_Dosagem;
        this.RM_RetiradoSus = RM_RetiradoSus;
        this.RM_UnidadeDosagem = RM_UnidadeDosagem;
        /*this.usuario = FK_US_ID;*/
        this.Criado_em = LocalDate.now();
    }

    public Remedio (){

    }
}
