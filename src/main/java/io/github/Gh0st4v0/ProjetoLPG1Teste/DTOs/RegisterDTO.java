package io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
        @NotBlank(message = "o campo não pode estar vazio")
        @Size(min = 2, max = 255, message = "o nome deve ter entre 2 e 255 caracteres")
        String nome,
        @NotBlank(message = "o campo não pode estar vazio")
        @Email(message = "o email deve estar em um formato valido")
        String email,
        @NotBlank(message = "o campo não pode estar vazio")
        @Size(min = 4, max = 40, message = "a senha deve ter entre 4 e 40 caracteres")
        String senha) {
}
