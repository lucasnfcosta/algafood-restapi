package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntidadeNaoExisteException extends NegocioException{
    
    public EntidadeNaoExisteException(String mensagem) {
        super(mensagem);
    }
}
