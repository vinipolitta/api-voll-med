package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.dto.AgendamentoConsultaDTO;
import med.voll.api.infra.exception.ValidacaoExcepption;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void validar(AgendamentoConsultaDTO dados) {

        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();
        if (diferencaEmMinutos < 30) {
            throw new ValidacaoExcepption("Consulta deve ser agendada com antecedencia minia de 30 min");
        }

    }
}
