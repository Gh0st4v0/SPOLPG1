package io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO {
    private String id;
    private String nome;
    private String email;
}
