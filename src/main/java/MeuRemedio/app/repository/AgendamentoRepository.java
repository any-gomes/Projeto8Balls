package MeuRemedio.app.repository;

import MeuRemedio.app.models.agendamentos.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, String> {
    Agendamento findByDataInicio(String dataInicio);
    Agendamento findByHoraInicio(String horaInicio);
    Agendamento findByDataFinal(String dataFinal);

//    Agendamento findAllByDataInicio();
//    Agendamento findAllByHoraInicio();
//    Agendamento findAllByDataFinal();
}
