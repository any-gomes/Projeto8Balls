package MeuRemedio.app.service;

import MeuRemedio.app.controllers.EnvioEmailController;
import MeuRemedio.app.models.agendamentos.Agendamento;
import MeuRemedio.app.models.remedios.Remedio;
import MeuRemedio.app.models.usuarios.Usuario;
import MeuRemedio.app.repository.AgendamentoRepository;
import MeuRemedio.app.repository.RemedioRepository;
import MeuRemedio.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class NotificationService {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Autowired
    RemedioRepository remedioRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EnvioEmailController envioEmailController;

    @Scheduled(fixedRate = 60000)
    public void verificarDataAtual(){

        LocalTime horaAgora = LocalTime.parse(LocalTime.now(ZoneId.of("America/Sao_Paulo")).format(horaFormatada()));
        LocalDate dataAgora = LocalDate.parse(LocalDate.now(ZoneId.of("America/Sao_Paulo")).format(dataFormatada()));
        List<Agendamento> agendamentosAgora = agendamentoRepository.findAll();

        for (Agendamento agendamento : agendamentosAgora) {
            boolean intervalo = verificarDataAtualDentroIntervalo(agendamento, dataAgora);
            if (intervalo) {
                verificarHoraRemedio(agendamento, horaAgora);
            }
        }
    }

    public void verificarHoraRemedio(Agendamento agendamento, LocalTime horaAgora){

        LocalDateTime instanteInicio =
                getDataInicio(agendamento).atTime(LocalTime.parse(agendamento.getHoraInicio(), horaFormatada()));
        LocalDateTime instanteFinal
                = getDataFinal(agendamento).atTime(23, 59);
        LocalDateTime instanteAgora = LocalDate.now(ZoneId.of("America/Sao_Paulo")).atTime(horaAgora);

        List<LocalDateTime> horasRemedio = new ArrayList<>();
        horasRemedio.add(instanteInicio);

        while(instanteInicio.isBefore(instanteFinal)){
            instanteInicio = instanteInicio.plusHours(agendamento.getPeriodicidade());
            horasRemedio.add(instanteInicio);
        }
        for (LocalDateTime localDateTime : horasRemedio) {
            if (instanteAgora.isEqual(localDateTime)) {
                getDadosUsuario(agendamento);
                break;
            }
        }
    }

    public void getDadosUsuario(Agendamento agendamento){
        List<Remedio> remedios = agendamento.getRemedio();
        Usuario usuario = remedios.get(0).getUsuario();
        envioEmailController.emailNotificacaoRemedio(usuario, remedios);
    }


    public boolean verificarDataAtualDentroIntervalo(Agendamento agendamento, LocalDate dataAgora){
        LocalDate dataInicio = getDataInicio(agendamento);
        LocalDate dataFinal = getDataFinal(agendamento);
        return dataAgora.compareTo(dataInicio) >= 0 &&
                dataAgora.compareTo(dataFinal) <= 0;
    }

    public LocalDate getDataInicio(Agendamento agendamento){
        DateTimeFormatter formatter = dataFormatada();
        return LocalDate.parse(agendamento.getDataInicio(), formatter);
    }

    public LocalDate getDataFinal(Agendamento agendamento){
        DateTimeFormatter formatter = dataFormatada();
        return LocalDate.parse(agendamento.getDataFinal(), formatter);
    }

    public DateTimeFormatter dataFormatada(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public DateTimeFormatter horaFormatada(){
        return DateTimeFormatter.ofPattern("HH:mm");
    }

    public DateTimeFormatter horaDataFormatada(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}

