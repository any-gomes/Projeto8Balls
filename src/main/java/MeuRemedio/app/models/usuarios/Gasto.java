package MeuRemedio.app.models.usuarios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Gasto implements Serializable {
    private static long serialVersionUID = 1L;
    @Id
    @Column(name = "GA_ID", nullable = false)
    private Long GA_ID;

    @NotNull @NotBlank
    private Date GA_Data;
    @NotNull @NotBlank
    private double GA_Valor;
    @NotNull @NotBlank
    private long GA_Qtd_Parcela;

    @NotBlank
    private LocalDate Criado_em;

    public Gasto (Date GA_Data, double GA_Valor, long GA_Qtd_Parcela) {
        this.GA_Data = GA_Data;
        this.GA_Valor = GA_Valor;
        this.GA_Qtd_Parcela = GA_Qtd_Parcela;
        this.Criado_em = LocalDate.now();
    }
    public Gasto (){

    }
}
