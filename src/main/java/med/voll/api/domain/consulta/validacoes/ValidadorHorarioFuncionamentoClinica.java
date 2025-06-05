package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.dto.AgendamentoConsultaDTO;
import med.voll.api.infra.exception.ValidacaoExcepption;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta  {


    public void validar(AgendamentoConsultaDTO dados){
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClina = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

        if (domingo || antesDaAberturaDaClina || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoExcepption("Consulta Fora do horario do funcionamento da clinica");
        }
    }
}
