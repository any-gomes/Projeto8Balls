package MeuRemedio.app.models.remedios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @NotBlank @NotNull
    private String descricao;

    @NotBlank
    private LocalDate Criado_em = LocalDate.now();

    public Categoria (String descricao) {
      //  this.CT_ID = CT_ID;
        this.descricao = descricao;
        this.Criado_em = LocalDate.now();
    }

    public Categoria (){

    }
}
