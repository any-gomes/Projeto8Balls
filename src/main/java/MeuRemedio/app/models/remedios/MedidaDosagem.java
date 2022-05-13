package MeuRemedio.app.models.remedios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
public class MedidaDosagem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "MD_ID", nullable = false)
    private Long MD_ID;

    @NotNull
    private String MD_Medida;

    @OneToMany
    private List<Remedio> remedio;

    public MedidaDosagem(Long MD_ID, String MD_Medida, List<Remedio> remedio) {
        this.MD_ID = MD_ID;
        this.MD_Medida = MD_Medida;
        this.remedio = remedio;
    }

    public MedidaDosagem() {

    }

}
