package io.github.Gh0st4v0.ProjetoLPG1Teste.controller;

import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.*;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Rifa;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Usuario;
import io.github.Gh0st4v0.ProjetoLPG1Teste.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("all")
    public ResponseEntity<List<UsuarioDTO>> getAllUsers(){
        List<UsuarioDTO> users = this.service.getAllUsers();
        return ResponseEntity.ok(users);
    }

   @PutMapping("update/name/{id}")
   public ResponseEntity<UsuarioDTO> alterarNome(@PathVariable String id, @RequestBody UpdateNameDTO usuario){
        return ResponseEntity.ok(this.service.alterarNome(id, usuario.nome()));
   }

   @DeleteMapping("delete/{id}")
    public ResponseEntity deletarUsuario(@PathVariable String id, @RequestBody AuthenticationDTO user){
        this.service.deletarUsuario(id, user.senha());
        return ResponseEntity.ok().build();
   }

   @PostMapping("/rifas/new/{id}")
    public ResponseEntity<RifaDTO> criarRifa(@PathVariable String id, @RequestBody RifaRegisterDTO rifa){
        RifaDTO novaRifa = this.service.criarRifa(rifa.nome(), id);
        return ResponseEntity.ok( novaRifa );
   }
}
