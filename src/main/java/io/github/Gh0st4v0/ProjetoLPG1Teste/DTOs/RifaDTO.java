package io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class RifaDTO {
    String id;
    String nome;
    String descricao;
    String criadorNome;
    Date dataSorteio;
}
