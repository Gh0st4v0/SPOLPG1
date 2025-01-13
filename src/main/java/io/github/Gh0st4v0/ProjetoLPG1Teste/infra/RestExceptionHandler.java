package io.github.Gh0st4v0.ProjetoLPG1Teste.infra;

import io.github.Gh0st4v0.ProjetoLPG1Teste.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundHandler(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado: " + exception.getMessage());
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> invalidInputHandler(InvalidInputException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Entrada inválida: " + exception.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> authenticationHandler(AuthenticationException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro de autenticação: " + exception.getMessage());
    }

    @ExceptionHandler(DatabaseOperationException.class)
    public ResponseEntity<String> databaseOperationHandler(DatabaseOperationException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no banco de dados: " + exception.getMessage());
    }

    @ExceptionHandler(RifaNotFoundException.class)
    public ResponseEntity<String> rifaNotFoundHandler(RifaNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rifa não encontrada: " + exception.getMessage());
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithmeticException(ArithmeticException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro aritmético: " + exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> genericExceptionHandler(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado: " + exception.getMessage());
    }
}

