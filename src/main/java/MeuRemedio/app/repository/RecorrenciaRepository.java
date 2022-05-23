package MeuRemedio.app.repository;

import MeuRemedio.app.models.agendamentos.Recorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecorrenciaRepository extends JpaRepository<Recorrencia, Long> {

}
