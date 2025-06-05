package med.voll.api.domain.consulta.repository;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

    Boolean existsByPacienteIdAndDataBetween(
            @NotNull
            Long aLong,
            LocalDateTime primeiroHoraio,
            LocalDateTime utimoHorario);
}
