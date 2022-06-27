package MeuRemedio.app.service;

import MeuRemedio.app.controllers.EnvioEmailController;
import MeuRemedio.app.models.agendamentos.Agendamento;
import MeuRemedio.app.models.agendamentos.IntervaloDias;
import MeuRemedio.app.models.agendamentos.Recorrencia;
import MeuRemedio.app.models.remedios.Remedio;
import MeuRemedio.app.models.usuarios.Usuario;
import MeuRemedio.app.repository.AgendamentoRepository;
import MeuRemedio.app.repository.IntervaloDiasRepository;
import MeuRemedio.app.repository.RecorrenciaRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@Transactional
public class NotificationService {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Autowired
    IntervaloDiasRepository intervaloDiasRepository;

    @Autowired
    RecorrenciaRepository recorrenciaRepository;

    @Autowired
    EnvioEmailController envioEmailController;


    final String ZONEID = "America/Sao_Paulo";

    @Scheduled(fixedRate = 60000)
    public void enviarNotificacao(){

        final var horaAgora = LocalTime.parse(LocalTime.now(ZoneId.of(ZONEID)).format(horaFormatada()));
        final var dataAgora = LocalDate.parse(LocalDate.now(ZoneId.of(ZONEID)).format(dataFormatada()));
        Iterable<Agendamento> agendamentosAgora = agendamentoRepository.findAll();

        // Percorre todos os agendamentos registrados no banco de dados
        for (Agendamento agendamento : agendamentosAgora) {
            boolean intervalo = verificarDataAtualDentroIntervalo(agendamento, dataAgora);
            if (intervalo) {
                verificarHoraRemedio(agendamento, horaAgora);
            }
        }
    }

    //Verificando se data atual está no intervalo de data início e data fim
    public boolean verificarDataAtualDentroIntervalo(Agendamento agendamento, LocalDate dataAgora){
        LocalDate dataInicio = getDataInicio(agendamento);
        LocalDate dataFinal = getDataFinal(agendamento);
        return dataAgora.compareTo(dataInicio) >= 0 &&
                dataAgora.compareTo(dataFinal) <= 0;
    }

    public void verificarHoraRemedio(Agendamento agendamento, LocalTime horaAgora){

        var instanteInicio =
                getDataInicio(agendamento).atTime(LocalTime.parse(agendamento.getHoraInicio(), horaFormatada()));
        final var instanteFinal
                = getDataFinal(agendamento).atTime(23, 59);
        final var instanteAgora = LocalDate.now(ZoneId.of("America/Sao_Paulo")).atTime(horaAgora);

        //Montar lista de todas as horas para tomar o remédio
        List<LocalDateTime> horasRemedio = new ArrayList<>();
        horasRemedio.add(instanteInicio);

        boolean isIntervaloDias = verificarseIntervaloDias(agendamento);
        if (isIntervaloDias){
            IntervaloDias intervalo = intervaloDiasRepository.getById(agendamento.getId());

            //Monta lista de horários com intervalos
            while (instanteInicio.isBefore(instanteFinal)){
                int dia = instanteInicio.getDayOfMonth();
                instanteInicio = instanteInicio.plusHours(agendamento.getPeriodicidade());
                if(instanteInicio.getDayOfMonth() == dia){
                    horasRemedio.add(instanteInicio);
                } else {
                    instanteInicio = instanteInicio.plusDays(intervalo.getIntervaloDias());
                }
            }
        } else {
            //Monta lista de horários sem intervalo
            while (instanteInicio.isBefore(instanteFinal)) {
                instanteInicio = instanteInicio.plusHours(agendamento.getPeriodicidade());
                horasRemedio.add(instanteInicio);
            }
        }

//        boolean isRecorrenciaVazio = verificarSeRecorrencia(agendamento);
//        if (!isRecorrenciaVazio) {
//            boolean recorrenciaHoje = verificarSeRecorrenciaHoje(agendamento, instanteAgora);
//            if (!recorrenciaHoje) return;
//        }

        for (LocalDateTime localDateTime : horasRemedio) {
            if (instanteAgora.isEqual(localDateTime)) {
                getDadosUsuario(agendamento, instanteAgora);
                break;
            }
        }
    }

    //Chama a classe email controller
    public void getDadosUsuario(Agendamento agendamento, LocalDateTime instanteAgora){
        List<Remedio> remedios = agendamento.getRemedio();   // Recebe todos os remédios associados ao agendamento
        Usuario usuario = remedios.get(0).getUsuario();    // Recebe dados do usuário associado ao primeiro remédio da lista
        envioEmailController.emailNotificacaoRemedio(usuario, remedios, instanteAgora);
    }

    //Verifica se o agendamento em questão possui intervalo de dias na tabela intervalo_dias
    public boolean verificarseIntervaloDias(Agendamento agendamento){
        Optional<IntervaloDias> intervaloDias = intervaloDiasRepository.findById(agendamento.getId());
        return intervaloDias.isPresent();
    }
//
//    //Verifica se o agendamento em questão possui uma recorrência associada na tabela recorrencia
//    public boolean verificarSeRecorrencia(Agendamento agendamento){
//     List<Recorrencia> recorrencia = recorrenciaRepository.findAllById(Collections.singleton(agendamento.getId()));
//     return recorrencia.isEmpty();
//    }
//
//    //Método que verifica se o dia da semana de hoje é igual à alguma recorrência do Agendamento
//    public boolean verificarSeRecorrenciaHoje(Agendamento agendamento, LocalDateTime instanteAgora){
//        boolean diaRemedio = false;
//        List<Recorrencia> recorrencia = recorrenciaRepository.findAllById
//                (Collections.singleton(agendamento.getId())); //Recebe todas as recorrências para este ID
//
//        Date hojeDate = Date.from(instanteAgora.atZone(ZoneId.of("America/Sao_Paulo")).toInstant());
//        Calendar c = Calendar.getInstance();
//        c.setTime(hojeDate);
//        int diaSemanaHoje = c.get(Calendar.DAY_OF_WEEK); //Recebe o dia de hoje em semana
//
//        //Verifica se dia da semana hoje é igual a algum dia de semana cadastrado em recorrencia para este ID
//        for (Recorrencia value : recorrencia) {
//            if (value.getDiaSemana() == diaSemanaHoje) {
//                diaRemedio = true;
//                break;
//            }
//        }
//        return diaRemedio;
//    }

    //Converte a data inicío para LocalDate no formato adequado
    public LocalDate getDataInicio(Agendamento agendamento){
        DateTimeFormatter formatter = dataFormatada();
        return LocalDate.parse(agendamento.getDataInicio(), formatter);
    }

    //Converte a data fianl para LocalDate no formato adequado
    public LocalDate getDataFinal(Agendamento agendamento){
        DateTimeFormatter formatter = dataFormatada();
        return LocalDate.parse(agendamento.getDataFinal(), formatter);
    }

    //Formata data
    public DateTimeFormatter dataFormatada(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    //Formata hora
    public DateTimeFormatter horaFormatada(){
        return DateTimeFormatter.ofPattern("HH:mm");
    }
}

