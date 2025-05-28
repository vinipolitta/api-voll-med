package med.voll.api.domain.paciente.dto;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.paciente.Paciente;

public record DetalhamentoPacienteDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        Endereco endereco
) {

    public DetalhamentoPacienteDTO(Paciente medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getEndereco());
    }
}
