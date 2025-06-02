package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.dto.AuthDTO;
import med.voll.api.domain.usuario.dto.CadastroUsuarioDTO;
import med.voll.api.domain.usuario.repository.UsuarioRepository;
import med.voll.api.infra.dto.TokenJWTDTO;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AuthDTO dados) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

            return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/criar")
    public ResponseEntity cadastrarUsuario(@RequestBody CadastroUsuarioDTO usuarioDTO, UriComponentsBuilder uriBuilder) {
        var usuario = new Usuario(usuarioDTO);
        repository.save(usuario);
        var uri = uriBuilder.path("/login").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new CadastroUsuarioDTO(usuario));
    }

    @GetMapping
    public ResponseEntity getUsuarios(@PageableDefault(size = 10, sort = {"login"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(CadastroUsuarioDTO::new);
        return ResponseEntity.ok(page);
    }

}
