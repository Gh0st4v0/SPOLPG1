package io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateNameDTO(
        @NotBlank(message = "o campo não pode estar vazio")
        @Size(min = 2, max = 255, message = "o nome deve ter entre 2 e 255 caracteres")
        String nome) {
}
