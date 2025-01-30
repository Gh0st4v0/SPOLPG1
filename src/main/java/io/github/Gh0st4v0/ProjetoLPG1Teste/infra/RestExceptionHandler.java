package io.github.Gh0st4v0.ProjetoLPG1Teste.infra;

import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.ErroCampo;
import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.ErroResposta;
import io.github.Gh0st4v0.ProjetoLPG1Teste.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErroResposta> userNotFoundHandler(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErroResposta.naoEncontrado(exception.getMessage()));
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErroResposta> invalidInputHandler(InvalidInputException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErroResposta.respostaPadrao(exception.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErroResposta> authenticationHandler(AuthenticationException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErroResposta.naoAutorizado(exception.getMessage()));
    }

    @ExceptionHandler(DatabaseOperationException.class)
    public ResponseEntity<ErroResposta> databaseOperationHandler(DatabaseOperationException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErroResposta.erroServidor(exception.getMessage()));
    }

    @ExceptionHandler(RifaNotFoundException.class)
    public ResponseEntity<ErroResposta> rifaNotFoundHandler(RifaNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErroResposta.naoEncontrado(exception.getMessage()));
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErroResposta> handleArithmeticException(ArithmeticException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErroResposta.respostaPadrao(exception.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        List<FieldError> fieldErrors = exception.getFieldErrors();
        List<ErroCampo> listaErros =fieldErrors
                .stream()
                .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(),"erro de validação.", listaErros));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> genericExceptionHandler(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErroResposta.erroServidor(exception.getMessage()));
    }
}

