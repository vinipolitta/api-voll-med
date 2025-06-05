package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.dto.CancelamentoConsultaDTO;
import med.voll.api.domain.consulta.dto.DetalhamentoConsultaDTO;
import med.voll.api.domain.consulta.dto.AgendamentoConsultaDTO;
import med.voll.api.domain.consulta.service.AgendaDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {


    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid AgendamentoConsultaDTO dados) {
        var dto = agendaDeConsultas.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid CancelamentoConsultaDTO dados) {
        agendaDeConsultas.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
