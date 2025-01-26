package io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
public class RifaDTO {
    String id;
    String nome;
    String descricao;
    String criadorNome;
    Date dataSorteio;
}
