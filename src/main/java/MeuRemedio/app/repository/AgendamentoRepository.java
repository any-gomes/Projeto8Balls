package MeuRemedio.app.repository;

import MeuRemedio.app.models.agendamentos.Agendamento;
import MeuRemedio.app.models.remedios.Remedio;
import MeuRemedio.app.models.usuarios.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {
    Agendamento findById (long id);
    List<Agendamento> findAllByUsuarioID(Long usuarioID);
}
