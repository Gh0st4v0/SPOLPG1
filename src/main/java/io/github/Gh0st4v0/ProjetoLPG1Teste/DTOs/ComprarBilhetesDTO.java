package io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs;

import jakarta.validation.constraints.DecimalMin;

public record ComprarBilhetesDTO(
        @DecimalMin(value = "1", message = "é necessário comprar no mínimo 1 bilhete")
        int bilhetes
    ) {
}
