package med.voll.api.medico.dto;

import med.voll.api.medico.Especialidade;
import med.voll.api.medico.Medico;

public record ListagemMedicoDTO(String nome, String crm, String email, Especialidade especialidade) {
    public ListagemMedicoDTO(Medico medico) {
        this(medico.getNome(), medico.getCrm(), medico.getEmail(), medico.getEspecialidade());
    }
}
