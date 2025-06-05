package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.dto.AgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsulta {

    void validar(AgendamentoConsultaDTO dados);

}
