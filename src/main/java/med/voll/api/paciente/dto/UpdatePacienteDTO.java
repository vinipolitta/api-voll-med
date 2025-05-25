package med.voll.api.paciente.dto;

import med.voll.api.endereco.dto.EnderecoDTO;

public record UpdatePacienteDTO(
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {
}
