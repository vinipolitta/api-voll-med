package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.Medico;
import med.voll.api.medico.dto.CadastroMedicoDTO;
import med.voll.api.medico.dto.ListagemMedicoDTO;
import med.voll.api.medico.repository.MedicoRepository;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.dto.CadastroPacienteDTO;
import med.voll.api.paciente.dto.ListagemPacienteDTO;
import med.voll.api.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping()
    @Transactional
    public Paciente cadastrarPaciente(@RequestBody @Valid CadastroPacienteDTO dados) {
        return repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<ListagemPacienteDTO> getAllPaciente(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(ListagemPacienteDTO::new);
    }
}
