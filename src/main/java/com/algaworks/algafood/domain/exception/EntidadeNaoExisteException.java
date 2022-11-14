package com.algaworks.algafood.domain.exception;

public class EntidadeNaoExisteException extends RuntimeException{
    public EntidadeNaoExisteException(String mensagem){
        super(mensagem);
    }
}
