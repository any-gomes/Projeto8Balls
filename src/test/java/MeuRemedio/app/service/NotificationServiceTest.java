package MeuRemedio.app.service;

import MeuRemedio.app.models.agendamentos.Agendamento;
import MeuRemedio.app.repository.AgendamentoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
class NotificationServiceTest {

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @InjectMocks
    private NotificationService notificationService;

    @DisplayName("Deve validar envio de notificações")
    @Test
    public void verificarDataAtualTest(){
        Agendamento agendamentoMock = agendamentoMock();
        Mockito.when(agendamentoRepository.save(any(Agendamento.class))).thenReturn(agendamentoMock);
        Mockito.verify(agendamentoRepository, times(1)).save(agendamentoMock);
        notificationService.enviarNotificacao();
    }

    @Test
    public void getDadosUsuarioTest(){
        notificationService.getDadosUsuario(agendamentoMock());
    }

    public Agendamento agendamentoMock(){
        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);
        agendamento.setDataInicio("2022-05-21");
        agendamento.setDataFinal("2022-05-25");
        agendamento.setPeriodicidade(8);
        agendamento.setHoraInicio("10:00");
        agendamento.setAG_Criado_em(LocalDate.now());
        agendamento.setRemedio(new ArrayList<>());
        return agendamento;
    }

}