package io.github.Gh0st4v0.ProjetoLPG1Teste.service;

import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.RifaDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.exceptions.DatabaseOperationException;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Bilhete;
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
        try{
            return repository.findAll()
                    .stream()
                    .map(rifa -> new RifaDTO(rifa.getId(), rifa.getNome(), rifa.getDescricao(), rifa.getCriador().getNome(), rifa.getDataSorteio()))
                    .toList();
        } catch (Exception e){
            throw new DatabaseOperationException("Não foi possivel encontrar os usuarios");
        }
    }

}
