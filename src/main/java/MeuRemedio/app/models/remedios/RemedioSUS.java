package MeuRemedio.app.models.remedios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "Remedio_SUS")
public class RemedioSUS implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RS_ID", nullable = false)
    private long RS_ID;

    @NotNull @NotBlank
    private String RS_Nome;
    @NotNull @NotBlank
    private String RS_FormaFarmaceutica;
    @NotNull @NotBlank
    private String RS_CodigoATC;
    @NotNull @NotBlank
    private String RS_Componente;

    @NotBlank
    private LocalDate Criado_em = LocalDate.now();


    public RemedioSUS (){

    }

    public RemedioSUS (long RS_ID, String RS_Nome, String RS_FormaFarmaceutica, String RS_CodigoATC, String RS_Componente) {
        this.RS_ID = RS_ID;
        this.RS_Nome = RS_Nome;
        this.RS_FormaFarmaceutica = RS_FormaFarmaceutica;
        this.RS_CodigoATC = RS_CodigoATC;
        this.RS_Componente = RS_Componente;
        this.Criado_em = LocalDate.now();
    }
}
