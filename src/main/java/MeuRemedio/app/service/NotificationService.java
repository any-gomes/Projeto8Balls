package MeuRemedio.app.service;

import MeuRemedio.app.models.agendamentos.Agendamento;
import MeuRemedio.app.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationService {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Scheduled(fixedRate = 60000)
    public void verificarDataAtual(){

        LocalTime horaAgora = LocalTime.parse(LocalTime.now(ZoneId.of("America/Sao_Paulo")).format(horaFormatada()));
        LocalDate dataAgora = LocalDate.parse(LocalTime.now(ZoneId.of("America/Sao_Paulo")).format(dataFormatada()));
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
        LocalDateTime instanteAgora =
                LocalDateTime.parse(LocalTime.now(ZoneId.of("America/Sao_Paulo")).format(horaDataFormatada()));

        List<LocalDateTime> horasRemedio = new ArrayList<>();
        horasRemedio.add(instanteInicio);

        while(instanteInicio.isBefore(instanteFinal)){
            instanteInicio = instanteInicio.plusHours(agendamento.getPeriodicidade());
            horasRemedio.add(instanteInicio);
        }
        for (int i = 0; i < horasRemedio.size(); i++){
            if (instanteAgora.isEqual(horasRemedio.get(i))){
                //send email
                break;
            }
        }
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

