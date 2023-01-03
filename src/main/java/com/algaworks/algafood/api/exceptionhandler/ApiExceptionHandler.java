package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoExisteException;
import com.algaworks.algafood.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(EntidadeNaoExisteException.class)
    public ResponseEntity<?> trataEntidadeNaoExisteException(EntidadeNaoExisteException e) {
        Problema problema = Problema.builder()
            .dataHora(LocalDateTime.now())
            .mensagem(e.getMessage())
            .build();

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(problema);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> trataNegocioException(NegocioException e) {
        Problema problema = Problema.builder()
            .dataHora(LocalDateTime.now())
            .mensagem(e.getMessage())
            .build();

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(problema);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> trataHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        Problema problema = Problema.builder()
            .dataHora(LocalDateTime.now())
            .mensagem("O tipo de mídia não é aceito.")
            .build();

        return ResponseEntity
            .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
            .body(problema);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> trataEntidadeEmUsoException(EntidadeEmUsoException e) {
        Problema problema = Problema.builder()
            .dataHora(LocalDateTime.now())
            .mensagem(e.getMessage())
            .build();

        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(problema);
    }
}
