package med.voll.api.paciente.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.Endereco;
import med.voll.api.endereco.dto.EnderecoDTO;

public record CadastroPacienteDTO(
        @NotBlank
        String nome,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
        String cpf,

        @NotNull
        @Valid
        EnderecoDTO endereco,

        Boolean ativo
) {
}
