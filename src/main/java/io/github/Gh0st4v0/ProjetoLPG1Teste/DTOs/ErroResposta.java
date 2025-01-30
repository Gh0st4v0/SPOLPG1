package io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros) {

    public static ErroResposta respostaPadrao(String mensagem){
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
    }

    public static ErroResposta naoEncontrado(String mensagem){
        return new ErroResposta(HttpStatus.NOT_FOUND.value(), mensagem, List.of());
    }

    public static ErroResposta naoAutorizado(String mensagem){
        return new ErroResposta((HttpStatus.UNAUTHORIZED.value()), mensagem, List.of());
    }

    public static ErroResposta entidadeInprcessavel(String mensagem){
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), mensagem, List.of());
    }

    public static ErroResposta erroServidor(String mensagem){
        return new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(), mensagem, List.of());
    }
}
