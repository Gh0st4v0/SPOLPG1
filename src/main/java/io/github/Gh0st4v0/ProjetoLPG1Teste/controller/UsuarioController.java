package io.github.Gh0st4v0.ProjetoLPG1Teste.controller;

import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Rifa;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Usuario;
import io.github.Gh0st4v0.ProjetoLPG1Teste.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Usuario> getAllUsers(){
        return this.service.getAllUsers();
    }

   @PutMapping("update/name/{id}")
   public Usuario alterarNome(@PathVariable String id, @RequestBody Usuario user){
        return this.service.alterarNome(id, user);
   }

   @DeleteMapping("delete/{id}")
    public void deletarUsuario(@PathVariable String id, @RequestBody Usuario user){
        this.service.deletarUsuario(id, user);
   }

   @PostMapping("/rifas/new/{id}")
    public Rifa criarRifa(@PathVariable String id, @RequestBody Rifa rifa){
        return this.service.criarRifa(rifa, id);
   }
}
