package io.github.Gh0st4v0.ProjetoLPG1Teste.controller;

import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.AuthenticationDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.LoginResponseDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.RegisterDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.infra.TokenService;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Usuario;
import io.github.Gh0st4v0.ProjetoLPG1Teste.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    };

    @PostMapping("register")
    public ResponseEntity criarUsuario(@RequestBody RegisterDTO data){
        if(this.repository.findByEmail(data.email()).isPresent()) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario usuario = new Usuario(data.nome(), data.email(), encryptedPassword);
        this.repository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

}
