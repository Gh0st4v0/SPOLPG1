package io.github.Gh0st4v0.ProjetoLPG1Teste.controller;

import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.RifaDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.service.RifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rifas")
public class RifaController {
    private final RifaService service;

    @Autowired
    public RifaController(RifaService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RifaDTO>> verRifas(){
        List<RifaDTO> rifas = this.service.verRifas();
        return ResponseEntity.ok(rifas);
    }

}
