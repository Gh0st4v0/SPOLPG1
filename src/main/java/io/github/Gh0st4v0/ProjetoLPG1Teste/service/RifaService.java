package io.github.Gh0st4v0.ProjetoLPG1Teste.service;

import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.RifaDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.UsuarioDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.exceptions.DatabaseOperationException;
import io.github.Gh0st4v0.ProjetoLPG1Teste.exceptions.RifaNotFoundException;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Bilhete;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Rifa;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Usuario;
import io.github.Gh0st4v0.ProjetoLPG1Teste.repository.RifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RifaService {
    private final RifaRepository repository;

    @Autowired
    public RifaService (RifaRepository repository){
        this.repository = repository;
    }

    public List<RifaDTO> verRifas(){
        return repository.findAll()
                    .stream()
                    .map(rifa -> new RifaDTO(rifa.getId(), rifa.getNome(), rifa.getDescricao(), rifa.getCriador().getNome(), rifa.getDataSorteio()))
                    .toList();
    }

    public List<UsuarioDTO> obterParticipantes(String id){
        Rifa rifa = repository.findById(id).orElseThrow(() -> new RifaNotFoundException("Rifa não encontrada"));
        return rifa.getParticipantes()
                .stream()
                .map(participante -> new UsuarioDTO(participante.getId(), participante.getNome(), participante.getEmail()))
                .toList();
    }

}
