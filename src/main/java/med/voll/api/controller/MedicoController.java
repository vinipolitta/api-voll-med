package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.endereco.dto.EnderecoDTO;
import med.voll.api.medico.Medico;
import med.voll.api.medico.dto.CadastroMedicoDTO;
import med.voll.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping()
    @Transactional
    public Medico cadastrarMedico(@RequestBody @Valid CadastroMedicoDTO dados){
        return repository.save(new Medico(dados));
    }
}
