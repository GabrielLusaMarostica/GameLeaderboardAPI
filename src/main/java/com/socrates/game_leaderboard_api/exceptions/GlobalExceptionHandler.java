package com.socrates.game_leaderboard_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//anotacao serve para dizer: essa classe vai interceptar exceções lançadas por qualquer Controller da aplicação
@RestControllerAdvice
public class GlobalExceptionHandler {
    //anotacao diz: "quando essa exceção específica for lançada em qualquer lugar da aplicação, executa esse métod aqui pra gerar a resposta".
    @ExceptionHandler(NicknameDuplicadoException.class)
    public ResponseEntity<Map<String, Object>> handleNicknameDuplicado(NicknameDuplicadoException ex){
        Map<String, Object> erro = new HashMap<>();
        erro.put("timestamp", LocalDateTime.now());
        erro.put("status", HttpStatus.CONFLICT.value());
        erro.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT). body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidacao(MethodArgumentNotValidException ex){
        Map<String, Object> erro = new HashMap<>();
        erro.put("timestamp", LocalDateTime.now());
        erro.put("status", HttpStatus.BAD_REQUEST.value());

        Map<String, String> camposInvalidos = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(campo ->
                camposInvalidos.put(campo.getField(), campo.getDefaultMessage())
        );
        erro.put("erros", camposInvalidos);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
