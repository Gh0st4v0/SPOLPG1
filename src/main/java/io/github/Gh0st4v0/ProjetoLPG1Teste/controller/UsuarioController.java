package io.github.Gh0st4v0.ProjetoLPG1Teste.controller;

import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.*;
import io.github.Gh0st4v0.ProjetoLPG1Teste.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsers(){
        List<UsuarioDTO> users = this.service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody RegisterDTO usuario){
        UsuarioDTO novoUsuario = this.service.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioDTO> alterarNome(@PathVariable String id, @RequestBody UpdateNameDTO usuario){
        return ResponseEntity.ok(this.service.alterarNome(id, usuario.nome()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarUsuario(@PathVariable String id, @RequestBody AuthenticationDTO user){
        this.service.deletarUsuario(id, user.senha());
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/rifas")
    public ResponseEntity<RifaDTO> criarRifa(@PathVariable String id, @RequestBody RifaRegisterDTO rifa){
        RifaDTO novaRifa = this.service.criarRifa(rifa.nome(), id);
        return ResponseEntity.ok( novaRifa );
    }
}
