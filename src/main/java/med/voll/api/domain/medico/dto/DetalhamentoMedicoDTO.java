package med.voll.api.domain.medico.dto;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;

public record DetalhamentoMedicoDTO(
            Long id,
            String nome,
            String email,
            String crm,
            String telefone,
            Especialidade especialidade,
            Boolean ativo,
            Endereco endereco
) {

    public DetalhamentoMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getAtivo(), medico.getEndereco() );
    }
}
