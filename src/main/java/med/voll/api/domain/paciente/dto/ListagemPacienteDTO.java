package med.voll.api.domain.paciente.dto;

import med.voll.api.domain.paciente.Paciente;

public record ListagemPacienteDTO(Long id, String nome, String email, String cpf, Boolean ativo) {

    public ListagemPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getAtivo());
    }
}
