package MeuRemedio.app.repository;

import MeuRemedio.app.models.agendamentos.Agendamento;
import MeuRemedio.app.models.agendamentos.IntervaloDias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IntervaloDiasRepository extends JpaRepository<IntervaloDias, Long> {

    List<IntervaloDias> findAllByUsuarioID(Long usuarioID);
}
