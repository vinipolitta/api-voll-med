package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.dto.CadastroPacienteDTO;
import med.voll.api.domain.paciente.dto.DetalhamentoPacienteDTO;
import med.voll.api.domain.paciente.dto.ListagemPacienteDTO;
import med.voll.api.domain.paciente.dto.UpdatePacienteDTO;
import med.voll.api.domain.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping()
    @Transactional
    public ResponseEntity<DetalhamentoPacienteDTO> cadastrarPaciente(@RequestBody @Valid CadastroPacienteDTO dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoPacienteDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPacienteDTO>> getAllPacientesAtivo(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(ListagemPacienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoPacienteDTO> updatePaciente(@RequestBody @Valid UpdatePacienteDTO dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.updateInfos(dados);

        return ResponseEntity.ok(new DetalhamentoPacienteDTO(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePaciente(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhamentoPacienteDTO> GetPacienteById(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoPacienteDTO(paciente));
    }
}
