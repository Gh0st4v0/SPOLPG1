package io.github.Gh0st4v0.ProjetoLPG1Teste.controller;

import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.RifaDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.UsuarioDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.service.RifaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("rifas")
@RequiredArgsConstructor
public class RifaController {
    private final RifaService service;

    @GetMapping
    public ResponseEntity<List<RifaDTO>> verRifas(){
        List<RifaDTO> rifas = this.service.verRifas();
        return ResponseEntity.ok(rifas);
    }

    @GetMapping("{id}/usuarios")
    public ResponseEntity<List<UsuarioDTO>> obterParticipantes(@PathVariable String id){
        List<UsuarioDTO> participantes = this.service.obterParticipantes(id);
        return ResponseEntity.ok(participantes);
    }

}
