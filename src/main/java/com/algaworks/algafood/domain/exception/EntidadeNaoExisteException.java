package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entidade não foi encontrada.")
public class EntidadeNaoExisteException extends ResponseStatusException{
    
    
    public EntidadeNaoExisteException(HttpStatus status, @Nullable String mensagem) {
        super(status, mensagem);
    }

    public EntidadeNaoExisteException(String mensagem){
        this(HttpStatus.NOT_FOUND ,mensagem);
    }
}
