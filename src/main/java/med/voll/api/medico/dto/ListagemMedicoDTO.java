package med.voll.api.medico.dto;

import med.voll.api.medico.Especialidade;
import med.voll.api.medico.Medico;

public record ListagemMedicoDTO(Long id, String nome, String crm, String email, Especialidade especialidade, Boolean ativo) {
    public ListagemMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEmail(), medico.getEspecialidade(), medico.getAtivo());
    }
}
