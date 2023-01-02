package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entidade não foi encontrada.")
public class EntidadeNaoExisteException extends RuntimeException{
    public EntidadeNaoExisteException(String mensagem){
        super(mensagem);
    }
}
