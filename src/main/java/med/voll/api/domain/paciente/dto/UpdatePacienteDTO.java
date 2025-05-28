package med.voll.api.domain.paciente.dto;

import med.voll.api.domain.endereco.dto.EnderecoDTO;

public record UpdatePacienteDTO(
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {
}
