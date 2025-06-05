package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.dto.AgendamentoConsultaDTO;
import med.voll.api.domain.paciente.repository.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoExcepption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta  {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(AgendamentoConsultaDTO dados) {
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());

        if (!pacienteEstaAtivo) {
            throw new ValidacaoExcepption("Consulta nao pode ser agendada com paciente excluido");
        }
    }
}
