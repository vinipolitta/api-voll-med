package med.voll.api.domain.consulta.service;

import med.voll.api.domain.consulta.dto.DetalhamentoConsultaDTO;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voll.api.infra.exception.ValidacaoExcepption;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.dto.AgendamentoConsultaDTO;
import med.voll.api.domain.consulta.dto.CancelamentoConsultaDTO;
import med.voll.api.domain.consulta.repository.ConsultaRepository;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.repository.MedicoRepository;
import med.voll.api.domain.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;


    public DetalhamentoConsultaDTO agendar(AgendamentoConsultaDTO dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoExcepption("Id do paciente informado nao existe");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoExcepption("Id do medico informado nao existe");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        repository.save(consulta);

        return new DetalhamentoConsultaDTO(consulta);
    }

    private Medico escolherMedico(AgendamentoConsultaDTO dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoExcepption("Especialidade é obrigatorio quando o medico nao for escolhido");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(CancelamentoConsultaDTO dados) {
        if (!repository.existsById(dados.idConsulta())) {
            throw new ValidacaoExcepption("Id da consulta informado não existe!");
        }

        var consulta = repository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
