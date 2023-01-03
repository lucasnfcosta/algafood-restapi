package com.algaworks.algafood.api.exceptionhandler;

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

        ProblemaType problemaType = ProblemaType.ENTIDADE_NAO_EXISTE;
        HttpStatus status = HttpStatus.NOT_FOUND;
        String details = ex.getMessage();

        Problema problema = createProblemBuilder(problemaType, status.value(), details).build();

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> trataNegocioException(NegocioException ex, WebRequest request) {

        ProblemaType problemaType = ProblemaType.NEGOCIO;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String details = ex.getMessage();

        Problema problema = createProblemBuilder(problemaType, status.value(), details).build();

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> trataEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {

        ProblemaType problemaType = ProblemaType.ENTIDADE_EM_USO;
        HttpStatus status = HttpStatus.CONFLICT;
        String details = ex.getMessage();

        Problema problema = createProblemBuilder(problemaType, status.value(), details).build();

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
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
