package io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record RifaRegisterDTO(
        @NotBlank(message = "o campo não pode estar vazio")
        @Size(min = 3, max = 255, message = "o nome deve ter entre 3 e 255 caracteres")
        String nome,
        @NotBlank(message = "o campo não pode estar vazio")
        @Size(min = 3, max = 255, message = "a descrição deve ter entre 3 e 255 caracteres")
        String descricao,
        @Future(message = "a data do sorteio deve ser uma data futura")
        Date dataSorteio) {
}
