package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.dto.CadastroMedicoDTO;
import med.voll.api.domain.medico.dto.UpdateMedicoDTO;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(CadastroMedicoDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
        this.especialidade = dados.especialidade();
        this.ativo = true;
    }

    public void updateInfos(UpdateMedicoDTO dados) {

        if (dados.nome() != null) {
            this.nome = dados.nome();

        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();

        }
        if (dados.endereco() != null) {
            this.endereco.updateInfos(dados.endereco());

        }

    }

    public void delete() {
        this.ativo = false;
    }
}