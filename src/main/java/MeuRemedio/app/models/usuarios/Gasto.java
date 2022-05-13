package MeuRemedio.app.models.usuarios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Gasto implements Serializable {
    @Id
    @Column(name = "GA_ID", nullable = false)
    private Long GA_ID;

    private Date GA_Data;
    private double GA_Valor;
    private long GA_Qtd_Parcela;

    @ManyToOne
    @JoinColumn(name = "US_ID")
    private Usuario FK_US_ID;

    private LocalDate Cadastrado_em = LocalDate.now();

    public Gasto(Long GA_ID, Date GA_Data, double GA_Valor, long GA_Qtd_Parcela, Usuario FK_US_ID) {
        this.GA_ID = GA_ID;
        this.GA_Data = GA_Data;
        this.GA_Valor = GA_Valor;
        this.GA_Qtd_Parcela = GA_Qtd_Parcela;
        this.FK_US_ID = FK_US_ID;
    }
    public Gasto (){

    }
}
