package med.voll.api.paciente.dto;

import med.voll.api.paciente.Paciente;

public record ListagemPacienteDTO(String nome, String email, String cpf) {

    public ListagemPacienteDTO(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
