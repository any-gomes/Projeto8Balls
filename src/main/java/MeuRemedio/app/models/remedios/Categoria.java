package MeuRemedio.app.models.remedios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Categoria implements Serializable {

    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CT_ID", nullable = false)
    private long CT_ID;

    private String descricao;

    @OneToMany
    private List<Remedio> remedio;

    public Categoria(long CT_ID, String descricao, List<Remedio> remedio) {
        this.CT_ID = CT_ID;
        this.descricao = descricao;
        this.remedio = remedio;
    }
    public Categoria (){

    }
}
