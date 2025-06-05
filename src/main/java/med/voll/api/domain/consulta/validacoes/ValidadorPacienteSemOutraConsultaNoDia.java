package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.dto.AgendamentoConsultaDTO;
import med.voll.api.domain.consulta.repository.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoExcepption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta  {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(AgendamentoConsultaDTO dados) {
        var primeiroHoraio = dados.data().withHour(7);
        var utimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHoraio, utimoHorario);
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoExcepption("Paciente ja possui uma consulta agendada nesse dia");
        }
    }
}
