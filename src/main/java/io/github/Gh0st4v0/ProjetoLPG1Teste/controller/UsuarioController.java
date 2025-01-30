package io.github.Gh0st4v0.ProjetoLPG1Teste.controller;

import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.*;
import io.github.Gh0st4v0.ProjetoLPG1Teste.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;


    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> verUsuarios(){
        List<UsuarioDTO> users = this.service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}/rifas")
    public ResponseEntity<List<RifaDTO>> obterRifasUsuario(@PathVariable String id, @RequestParam String tipo){
        if (tipo.equals("criadas")) {
            List<RifaDTO> rifas = this.service.obterRifasCriadas(id);
            return ResponseEntity.ok(rifas);
        }
        else if (tipo.equals("participando")) {
            List<RifaDTO> rifas = this.service.obterRifasParticipando(id);
            return ResponseEntity.ok(rifas);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody @Valid RegisterDTO usuario){
        UsuarioDTO novoUsuario = this.service.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioDTO> alterarNomeUsuario(@PathVariable String id, @RequestBody @Valid UpdateNameDTO usuario){
        return ResponseEntity.ok(this.service.alterarNome(id, usuario.nome()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarUsuario(@PathVariable String id, @RequestBody @Valid AuthenticationDTO user){
        this.service.deletarUsuario(id, user.senha());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("{id}/rifas")
    public ResponseEntity<RifaDTO> criarRifa(@PathVariable String id, @RequestBody @Valid RifaRegisterDTO rifa){
        RifaDTO novaRifa = this.service.criarRifa(rifa.nome(),rifa.descricao(), rifa.dataSorteio(), id);
        return ResponseEntity.ok( novaRifa );
    }

    @PostMapping("{id}/rifas/{rifaId}")
    public ResponseEntity<String> comprarBilhetesRifa(@PathVariable String id, @PathVariable String rifaId, @Valid @RequestBody ComprarBilhetesDTO bilhetes){
        this.service.comprarBilhetes(id, rifaId, bilhetes.bilhetes());
        return ResponseEntity.status(HttpStatus.CREATED).body("Bilhetes comprados com sucesso");
    }
}
