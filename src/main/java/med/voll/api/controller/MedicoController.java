package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.endereco.dto.EnderecoDTO;
import med.voll.api.medico.Medico;
import med.voll.api.medico.dto.CadastroMedicoDTO;
import med.voll.api.medico.dto.ListagemMedicoDTO;
import med.voll.api.medico.dto.UpdateMedicoDTO;
import med.voll.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping()
    @Transactional
    public Medico cadastrarMedico(@RequestBody @Valid CadastroMedicoDTO dados) {
        return repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<ListagemMedicoDTO> getAllMedicosAtivo(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(ListagemMedicoDTO::new);
    }

    @PutMapping
    @Transactional
    public void updateMedico(@RequestBody @Valid UpdateMedicoDTO dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.updateInfos(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.delete();
    }

}
