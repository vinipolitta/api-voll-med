package med.voll.api.medico.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.dto.EnderecoDTO;

public record UpdateMedicoDTO(

        @NotNull
        Long id,
        String telefone,
        String nome,
        EnderecoDTO endereco) {

}
