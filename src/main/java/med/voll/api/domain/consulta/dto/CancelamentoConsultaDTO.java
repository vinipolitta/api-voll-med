package med.voll.api.domain.consulta.dto;

import jakarta.validation.constraints.NotNull;

public record CancelamentoConsultaDTO(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo
) {

    public enum MotivoCancelamento {

        PACIENTE_DESISTIU,
        MEDICO_CANCELOU,
        OUTROS;

    }
}

