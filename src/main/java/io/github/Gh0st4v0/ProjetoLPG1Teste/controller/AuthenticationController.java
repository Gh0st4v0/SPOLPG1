package io.github.Gh0st4v0.ProjetoLPG1Teste.controller;

import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.AuthenticationDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.LoginResponseDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.infra.TokenService;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Usuario;
import io.github.Gh0st4v0.ProjetoLPG1Teste.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sessoes")
public class AuthenticationController {

    private AuthenticationManager manager;
    private TokenService service;

    public AuthenticationController (AuthenticationManager manager, TokenService service){
        this.manager = manager;
        this.service = service;
    }



    @PostMapping
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.manager.authenticate(usernamePassword);
        var token = this.service.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    };

}
