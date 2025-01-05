package io.github.Gh0st4v0.ProjetoLPG1Teste.service;

import io.github.Gh0st4v0.ProjetoLPG1Teste.exceptions.DatabaseOperationException;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Bilhete;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Rifa;
import io.github.Gh0st4v0.ProjetoLPG1Teste.repository.BilheteRepository;
import io.github.Gh0st4v0.ProjetoLPG1Teste.repository.RifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RifaService {
    private final RifaRepository repository;
    private final BilheteRepository bilheteRepository;

    @Autowired
    public RifaService (RifaRepository repository, BilheteRepository bilheteRepository){
        this.repository = repository;
        this.bilheteRepository = bilheteRepository;
    }

    public List<Rifa> verRifas(){
        try{
            return repository.findAll();
        } catch (Exception e){
            throw new DatabaseOperationException("Não foi possivel encontrar os usuarios");
        }
    }

    public List<Bilhete> criarBilhetes(String usuarioId, String rifaId, Integer bilhetes){
        try{
            List<Bilhete> listaBilhetes = new ArrayList<>();
            for (int i = 0; i < bilhetes; i++){
                Bilhete novoBilhete = new Bilhete();
            }
            return listaBilhetes;
        } catch (Exception e){
            throw new DatabaseOperationException("Não foi possivel criar os bilhetes");
        }
    }

}
