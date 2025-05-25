package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.endereco.dto.EnderecoDTO;
import med.voll.api.medico.Medico;
import med.voll.api.medico.dto.CadastroMedicoDTO;
import med.voll.api.medico.dto.DetalhamentoMedicoDTO;
import med.voll.api.medico.dto.ListagemMedicoDTO;
import med.voll.api.medico.dto.UpdateMedicoDTO;
import med.voll.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping()
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid CadastroMedicoDTO dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoMedicoDTO(medico));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemMedicoDTO>> getAllMedicosAtivo(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(ListagemMedicoDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateMedico(@RequestBody @Valid UpdateMedicoDTO dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.updateInfos(dados);


        return ResponseEntity.ok(new DetalhamentoMedicoDTO(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity GetMedicoById(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoMedicoDTO(medico));
    }


}
