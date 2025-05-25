package med.voll.api.paciente.dto;

import med.voll.api.endereco.Endereco;
import med.voll.api.medico.Especialidade;
import med.voll.api.medico.Medico;
import med.voll.api.paciente.Paciente;

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
