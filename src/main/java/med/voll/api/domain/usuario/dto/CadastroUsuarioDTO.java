package med.voll.api.domain.usuario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import med.voll.api.domain.usuario.Usuario;

public record CadastroUsuarioDTO(String login,  @JsonProperty("password")  String senha) {
    public CadastroUsuarioDTO(Usuario usuario) {
        this(usuario.getLogin(), usuario.getSenha());
    }
}
