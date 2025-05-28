package med.voll.api.domain.medico.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.dto.EnderecoDTO;

public record UpdateMedicoDTO(

        @NotNull
        Long id,
        String telefone,
        String nome,
        EnderecoDTO endereco) {

}
