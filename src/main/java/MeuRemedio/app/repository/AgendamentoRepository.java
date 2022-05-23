package MeuRemedio.app.repository;

import MeuRemedio.app.models.agendamentos.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
