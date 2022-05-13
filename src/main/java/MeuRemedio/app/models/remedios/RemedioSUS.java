package MeuRemedio.app.models.remedios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "Remedio_SUS")
public class RemedioSUS implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RS_ID", nullable = false)
    private long RS_ID;

    @NotNull
    private String RS_Nome;
    @NotNull
    private String RS_FormaFarmaceutica;
    @NotNull
    private String RS_CodigoATC;
    @NotNull
    private String RS_Componente;

    private LocalDate Cadastrado_em = LocalDate.now();


    public RemedioSUS (){

    }

    public RemedioSUS(long RS_ID, String RS_Nome, String RS_FormaFarmaceutica, String RS_CodigoATC, String RS_Componente) {
        this.RS_ID = RS_ID;
        this.RS_Nome = RS_Nome;
        this.RS_FormaFarmaceutica = RS_FormaFarmaceutica;
        this.RS_CodigoATC = RS_CodigoATC;
        this.RS_Componente = RS_Componente;
    }
}
