package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoExisteException;
import com.algaworks.algafood.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(EntidadeNaoExisteException.class)
    public ResponseEntity<?> trataEntidadeNaoExisteException(EntidadeNaoExisteException ex, WebRequest request) {

        Problema problema = createProblemBuilder(ProblemaType.ENTIDADE_NAO_EXISTE, HttpStatus.NOT_FOUND.value(), ex.getMessage()).build();

        return handleExceptionInternal(ex, problema, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> trataNegocioException(NegocioException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> trataEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        
                if (body == null) {
                    body = Problema.builder()
                        .title(status.getReasonPhrase())
                        .status(status.value())
                        .build();
                } else if (body instanceof String) {
                    body = Problema.builder()
                        .status(status.value())
                        .title((String) body)
                        .build();
                }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problema.ProblemaBuilder createProblemBuilder(ProblemaType problemaType, Integer status, String details) {
        return Problema.builder()
            .type(problemaType.getType())
            .title(problemaType.getTitle())
            .status(status)
            .details(details);
    }
}
