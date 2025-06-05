package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.dto.AgendamentoConsultaDTO;
import med.voll.api.domain.medico.repository.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoExcepption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta  {


    @Autowired
    private MedicoRepository repository;

    public void validar(AgendamentoConsultaDTO dados) {
        if (dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());

        if (!medicoEstaAtivo) {
            throw new ValidacaoExcepption("Consulta nao pode ser agendada com medico que nao esta ativo");
        }
    }
}
